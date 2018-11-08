package com.colorwater.main

import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.colorwater.R
import com.morelibrary.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * app主页面
 * jjj
 */
class MainActivity : BaseActivity() {
    private var fragmentTransaction: FragmentTransaction? = null
    internal var currentFrag: Fragment? = null
    val TAB1 = "TAB1"
    val TAB2 = "TAB2"
    val TAB3 = "TAB3"
    var isFirst = true;
    var tag = ""

    override fun onInitView() {
        main_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onBindData() {
        initFragment()
    }

    private fun initFragment() {
        if (isFirst) {
            fragmentTransaction = supportFragmentManager.beginTransaction()
            if (tag == TAB2) {
                currentFrag = HobbyFragment()
            } else if (tag == TAB3) {
                currentFrag = MyFragment()
            } else {
                currentFrag = HomeFragment()
            }
            fragmentTransaction!!.add(R.id.main_frameLayout, currentFrag!!, tag).commit()
            isFirst = false

        }
    }

    /**
     * fragment跳转
     *
     * @param Tag
     */
    fun switchToFragment(Tag: String) {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        var findresult: Fragment?
        findresult = supportFragmentManager.findFragmentByTag(Tag)
        if (currentFrag != null && currentFrag!!.getTag().equals(Tag)) {
            // 判断为相同fragment不切换

        } else {

            if (findresult != null) {
                fragmentTransaction!!.hide(currentFrag!!).show(findresult).commit()
            } else {

                if (Tag == TAB1) {
                    findresult = HomeFragment()
                } else if (Tag == TAB2) {
                    findresult = HobbyFragment()
                } else if (Tag == TAB3) {
                    findresult = MyFragment()
                }
                fragmentTransaction!!.hide(currentFrag!!).add(R.id.main_frameLayout, findresult!!, Tag).commit()
            }

        }
        currentFrag = findresult

    }

    override fun onLayoutResId(): Int = R.layout.activity_main

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchToFragment(TAB1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                switchToFragment(TAB2)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                switchToFragment(TAB3)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun hasTitle(): Boolean = false

    companion object {
        fun inVoke(activity: BaseActivity) {
            var intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }

}

