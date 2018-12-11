package com.morelibrary.view;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * author:jjj
 * time: 2018/12/5 10:12
 * TODO:方形imageview
 */
public class SuqareImageView extends ImageView {
    public SuqareImageView(Context context) {
        super(context);
    }

    public SuqareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
        int w1=resolveSize(1000,widthMeasureSpec);
        int h1=resolveSize(500,heightMeasureSpec);

//        Log.e("方形imageview：====", w + "---" + h+"----"+widthMeasureSpec+"==="+heightMeasureSpec);
        if (w1 > h1) {
            w1 = h1;
        } else {
            h1 = w1;
        }


        Log.e("方形imageview：", w1 + "---" + h1);
//
        setMeasuredDimension(w1, h1);
    }
}
