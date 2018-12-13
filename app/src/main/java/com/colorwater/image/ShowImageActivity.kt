package com.colorwater.image

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.colorwater.R
import com.morelibrary.image.ImageManager
import com.morelibrary.image.SelectImageActivity
import com.morelibrary.ui.BaseActivity
import com.morelibrary.view.LabelView
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
        ImageManager.onLoadImage(this, "http://pic33.photophoto.cn/20141022/0019032438899352_b.jpg", showImage_iv)
    }

    override fun onBindData() {
        var list = ArrayList<String>()
        list.add("喜欢")
        list.add("踢足球")
        list.add("亚历山大")
        list.add("哈")
        list.add("爱已晚我")
        list.add("嘿嘿")
        list.add("爱我")
        list.add("爱已晚我1年的")
        list.add("写代码的")
        list.add("电话大家看的大大啊")
        list.add("电我说的啊")
        list.add("电啊都说了")
        showImage_labelView!!.onSetData(list, object : LabelView.OnLabelListerner {
            override fun onClick(text: String?) {
                Toast.makeText(this@ShowImageActivity, text, Toast.LENGTH_SHORT).show()
            }

        })
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
