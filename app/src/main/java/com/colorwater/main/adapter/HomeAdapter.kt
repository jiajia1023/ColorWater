package com.colorwater.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.colorwater.R
import com.colorwater.main.HomeFragment
import kotlinx.android.synthetic.main.item_home.view.*

/**
 * author:jjj
 * time: 2018/11/6 9:53
 *TODO:home的适配器
 */
class HomeAdapter() : BaseAdapter() {
    private var mList: List<HomeFragment.HomeInfo>? = null
    private var mContext: Context? = null


    constructor(context: Context, list: List<HomeFragment.HomeInfo>) : this() {
        this.mList = list
        this.mContext = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: ViewHolder? = null
        var view = convertView
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_home, null)
            holder = ViewHolder(view)
            view!!.tag = holder
        } else {
            holder = convertView!!.tag as ViewHolder
        }
        var info = getItem(position)

        holder.tv!!.setText(info.text)
        holder.tv!!.setBackgroundResource(info.resBg)
        return view!!
    }

    override fun getItem(position: Int): HomeFragment.HomeInfo = mList!!.get(position)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = mList!!.size

    class ViewHolder {
        var tv: TextView? = null

        constructor(view: View) {
            tv = view!!.item_home_tv
        }
    }

}