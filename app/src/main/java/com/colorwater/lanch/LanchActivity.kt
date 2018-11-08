package com.colorwater.lanch

import android.os.Handler
import com.colorwater.R
import com.colorwater.main.MainActivity
import com.morelibrary.ui.BaseActivity

/**
 * 启动页
 * jjj
 * 2018、11、8
 */

class LanchActivity : BaseActivity() {
    override fun onLayoutResId(): Int = R.layout.activity_lanch

    override fun onInitView() {
    }

    override fun onBindData() {
        var handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                MainActivity.inVoke(this@LanchActivity)
            }

        }, 3000)

    }
}
