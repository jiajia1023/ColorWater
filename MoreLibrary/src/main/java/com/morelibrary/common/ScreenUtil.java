package com.morelibrary.common;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * author:jjj
 * time: 2018/11/23 15:35
 * TODO:屏幕相关
 */
public class ScreenUtil {
    /**
     * 获取当前窗体宽度和高度
     *
     * @param mContext
     * @return
     */
    public static int[] getScreenWH(Activity mContext) {
        DisplayMetrics dm = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return new int[]{dm.widthPixels, dm.heightPixels};
    }

    /**
     * 获取屏幕宽度
     *
     * @param mContext
     * @return
     */
    public static int getScreenWidth(Activity mContext) {
        DisplayMetrics dm = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
