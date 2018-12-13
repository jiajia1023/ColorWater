package com.colorwater.main

import android.view.View
import com.colorwater.R
import com.morelibrary.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_hobby.*

/**
 * author:jjj
 * time: 2018/10/15 16:42
 *TODO:爱好
 */
class HobbyFragment : BaseFragment() {
    override fun onLayoutResId(): Int = R.layout.fragment_hobby


    override fun onInitView(view: View?) {
    }

    override fun onBindData() {
        hobby_labelView!!.onSetData()
    }

}