package com.morelibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * author:jjj
 * time: 2018/12/11 15:10
 * TODO:标签视图
 */
public class LabelView extends ViewGroup {
    public LabelView(Context context) {
        super(context);
    }

    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            LayoutParams params = childView.getLayoutParams();

            int size = MeasureSpec.getSize(widthMeasureSpec);
            int mode = MeasureSpec.getMode(widthMeasureSpec);
            int childWith = 0;
            switch (params.width) {
                case LayoutParams.MATCH_PARENT:
                    if (mode == MeasureSpec.EXACTLY || mode == MeasureSpec.AT_MOST) {
                        childWith = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
                    } else {
                        childWith = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                    }
                    break;
                case LayoutParams.WRAP_CONTENT:
                    if (mode == MeasureSpec.EXACTLY || mode == MeasureSpec.AT_MOST) {
                        childWith = MeasureSpec.makeMeasureSpec(size, MeasureSpec.AT_MOST);
                    } else {
                        childWith = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                    }
                    break;
                default:
                    childWith = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
                    break;
            }

            setMeasuredDimension(childWith, 0);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
