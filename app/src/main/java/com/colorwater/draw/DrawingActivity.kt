package com.colorwater.draw

import android.content.Intent
import com.colorwater.R
import com.morelibrary.ui.BaseActivity

/**
 * Drawing
 */
class DrawingActivity : BaseActivity() {
    override fun onLayoutResId(): Int = R.layout.activity_drawing

    override fun onInitView() {
    }

    override fun onBindData() {
//        var  paint=Paint()
//        paint.color=Color.BLACK
//        paint.strokeWidth=10f
//        var canvas=Canvas()
//        var bitmap=BitmapFactory.decodeResource()
//        canvas.setBitmap()
//        drawing_view.d
    }

    companion object {
        fun inVoke(activity: BaseActivity) {
            var intent = Intent(activity, DrawingActivity::class.java)
            intent.putExtra(TITLE, "Drawing")
            activity.startActivity(intent)
        }
    }

}
