package com.morelibrary.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * author:jjj
 * time: 2018/10/15 15:02
 * TODO:图片类的管理类
 */
public class ImageManager {
    private static int errImg = android.R.mipmap.sym_def_app_icon;
    private static int emptyImg = android.R.mipmap.sym_def_app_icon;


    public static void onLoadImage(Context context, String imageUrl, ImageView imageView) {
        RequestOptions options = new RequestOptions().error(errImg).placeholder(emptyImg).dontAnimate();
        options.disallowHardwareConfig();
        Glide.with(context).applyDefaultRequestOptions(options).load(imageUrl).into(imageView);
    }
}
