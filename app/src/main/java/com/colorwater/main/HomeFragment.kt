package com.colorwater.main

import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import com.colorwater.R
import com.morelibrary.ui.BaseFragment
import com.morelibrary.view.AutoBanner
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * author:jjj
 * time: 2018/10/15 16:42
 *TODO:首页
 */
class HomeFragment : BaseFragment() {
    override fun onLayoutResId(): Int = R.layout.fragment_home


    override fun onInitView(view: View?) {

    }

    override fun onBindData() {
        fragmentHome_banner!!.setBannerWidthAndHeight(RelativeLayout.LayoutParams.MATCH_PARENT, 600)
        fragmentHome_banner!!.onSetMode_BottomParent(RelativeLayout.ALIGN_PARENT_RIGHT)
//        fragmentHome_banner!!.onSetRadioGroupLayouParams()
        var urlList = ArrayList<String>()
        urlList.add("http://img4.duitang.com/uploads/item/201601/12/20160112201847_dTscn.jpeg")
        urlList.add("http://pic36.photophoto.cn/20150728/0022005597823716_b.jpg")
        urlList.add("http://d.paper.i4.cn/max/2017/03/23/14/1490249222986_905517.jpg")
        fragmentHome_banner!!.onSetData(urlList)
        fragmentHome_banner!!.setAutoBannerListener(object : AutoBanner.AutoBannerListener {
            override fun onClick(position: Int, o: Any?) {
                Toast.makeText(activity, position.toString() + "--" + o.toString(), Toast.LENGTH_SHORT).show();
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentHome_banner!!.onTimerCancel()
    }

}