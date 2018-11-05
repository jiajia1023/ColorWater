package com.morelibrary.common;

import android.util.Log;

/**
 * author:jjj
 * time: 2018/11/5 14:49
 * TODO:log日志优化
 */
public class LogUtil {
    private static final boolean isDebug = true;

    public static void e(String err) {
        if (isDebug) {
            Log.e("log.e：", err);
        }
    }
}
