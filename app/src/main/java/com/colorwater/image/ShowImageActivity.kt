package com.colorwater.image

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.colorwater.R
import com.morelibrary.image.ImageManager
import com.morelibrary.image.SelectImageActivity
import com.morelibrary.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_show_image.*
import java.io.File

/**
 * 查看图片
 * jjj
 * 23/11/2018
 */
class ShowImageActivity : BaseActivity() {
    override fun onLayoutResId(): Int = R.layout.activity_show_image

    override fun onInitView() {
        showImage_selectTv!!.setOnClickListener {
            SelectImageActivity.inVoke(this, 1)
        }
        ImageManager.onLoadImage(this,"http://pic33.photophoto.cn/20141022/0019032438899352_b.jpg",showImage_iv)
    }

    override fun onBindData() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                1 -> {
                    if (data != null) {
                        var imgList = data.getStringArrayListExtra("IMG")
                        showImage_iv!!.setImageURI(Uri.fromFile(File(imgList.get(0))))
                    }
                }
            }
        }
    }

    companion object {
        fun inVoke(activity: BaseActivity) {
            var intent = Intent(activity, ShowImageActivity::class.java)
            intent.putExtra(TITLE, "查看图片")
            activity.startActivity(intent)
        }
    }


}
