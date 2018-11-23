package com.morelibrary.image

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.morelibrary.R
import com.morelibrary.common.ScreenUtil
import com.morelibrary.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_select_image.*
import kotlinx.android.synthetic.main.item_select_image.view.*

/**
 * 图片选择器
 * jjj
 * 23/11/2018
 */
class SelectImageActivity : BaseActivity() {
    var mList: MutableList<ImageInfo>? = null
    var mWidth = 0
    var mHeight = 0
    var mImgList: MutableList<String>? = null
    var mAdapter: SelectImageAdapter? = null
    override fun onLayoutResId(): Int = R.layout.activity_select_image

    override fun onInitView() {
        baseToolbar.right = R.string.selectImage_right
    }

    override fun onBindData() {
        mWidth = (ScreenUtil.getScreenWidth(this) - 3 * resources.getDimensionPixelOffset(R.dimen.scpaing_size)) / 4
        mHeight = (mWidth * 1.5).toInt()

        mImgList = ArrayList<String>()
        mList = ArrayList<ImageInfo>()
        for (i in 0..9) {
            mList!!.add(ImageInfo("http://img3.imgtn.bdimg.com/it/u=3084697918,1386841677&fm=26&gp=0.jpg", false))
        }
        mAdapter = SelectImageAdapter()
        selectImage_gridView!!.adapter = mAdapter
        selectImage_gridView!!.setOnItemClickListener { parent, view, position, id ->
            mAdapter!!.getItem(position).isSelect = !mAdapter!!.getItem(position).isSelect
            mAdapter!!.notifyDataSetChanged()
        }
    }

    companion object {
        fun inVoke(activity: BaseActivity, requestCode: Int) {
            var intent = Intent(activity, SelectImageActivity::class.java)
            intent.putExtra(TITLE, "选择图片")
            activity.startActivityForResult(intent, requestCode)
        }
    }

    inner class SelectImageAdapter : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view = convertView
            var holder: ViewHolder? = null
            if (convertView == null) {
                view = LayoutInflater.from(this@SelectImageActivity).inflate(R.layout.item_select_image, null)
                holder = ViewHolder(view)
                view!!.setTag(holder)
            } else {
                holder = convertView.tag as ViewHolder?
            }
            var prama = holder!!.iv!!.layoutParams
            prama.height = mHeight
            prama.width = mWidth
            ImageManager.onLoadImage(this@SelectImageActivity, getItem(position).path, holder!!.iv)
            return view!!
        }

        override fun getItem(position: Int): ImageInfo = mList!!.get(position)
        override fun getItemId(position: Int): Long = position.toLong()

        override fun getCount(): Int = if (mList != null) {
            mList!!.size
        } else {
            0
        }

        inner class ViewHolder {
            var iv: ImageView? = null

            constructor(view: View) {
                iv = view!!.item_selectImage_iv
            }
        }

    }

    inner class ImageInfo {
        var path = ""
        var isSelect = false

        constructor(path: String, isSelect: Boolean) {
            this.path = path
            this.isSelect = isSelect
        }
    }
}
