package com.morelibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.morelibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author:jjj
 * time: 2018/12/11 15:10
 * TODO:标签视图
 */
public class LabelView extends LinearLayout {
    private Context mContext;

    public LabelView(Context context) {
        super(context);
        init(context);
    }

    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        setOrientation(HORIZONTAL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        //获取size和model
        int sizeW = MeasureSpec.getSize(widthMeasureSpec);
        int sizeH = MeasureSpec.getSize(heightMeasureSpec);
        int modelW = MeasureSpec.getMode(widthMeasureSpec);
        int modelH = MeasureSpec.getMode(heightMeasureSpec);
        //行宽
        int lineWidth = 0;
        //行高
        int lineHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //测量子view的可用空间
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            LayoutParams params = (LayoutParams) childView.getLayoutParams();

            //宽度
//            int childWidth = modelW == MeasureSpec.EXACTLY ? params.width + params.leftMargin + params.rightMargin : MeasureSpec.makeMeasureSpec(sizeW, modelW) + params.rightMargin + params.leftMargin;
            int childWidth = childView.getMeasuredWidth() + params.rightMargin + params.leftMargin;
            //高度
//            int childHeight = modelH == MeasureSpec.EXACTLY ? params.height + params.topMargin + params.bottomMargin : MeasureSpec.makeMeasureSpec(sizeH, modelH) + params.bottomMargin + params.topMargin;
            int childHeight = childView.getMeasuredHeight() + params.bottomMargin + params.topMargin;

            //如果当前子view的宽度加上原始行宽已经超出父容器给的可用空间~转成下一行 并且重置原始行宽,且行高增加并累计
            if (childWidth + lineWidth > sizeW) {

                lineWidth = 0;
                lineHeightList.add(lineHeight);
            }
            //行宽累计
            lineWidth = lineWidth + childWidth;
            //获取这行的最大行高
            lineHeight = Math.max(lineHeight, childHeight);
            //保存测量出来的宽和高
            setMeasuredDimension(modelW == MeasureSpec.EXACTLY ? sizeH:lineWidth, modelH == MeasureSpec.EXACTLY ? sizeH : lineHeight);
        }
    }

    //行高集合
    private List<Integer> lineHeightList = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        lineHeightList.clear();

        int childCount = getChildCount();
        //父容器的宽度
        int width = getWidth();
        //行宽
        int lineWidth = 0;
        //布局需要的左上右下
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        //当前行高的position
        int curHeightP = 0;
        //行高
        int lineHeight = lineHeightList.get(0);

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LayoutParams params = (LayoutParams) childView.getLayoutParams();

            //如果宽度超过了可用宽度立即换行
            if (params.width + params.leftMargin + params.rightMargin + lineWidth > width) {
                curHeightP++;

                lineWidth = params.width + params.leftMargin + params.rightMargin;
                left = params.leftMargin;
                right = params.width + left;

                top = lineHeight + params.topMargin;
                bottom = top + params.height;
                lineHeight = lineHeight + params.topMargin + params.bottomMargin + params.height;

            } else {
                lineWidth = lineWidth + params.width + params.leftMargin + params.rightMargin;
                left = lineWidth + params.leftMargin;
                right = lineWidth + params.leftMargin + params.width;

                top = lineHeight + params.topMargin;
                bottom = top + params.height;
            }


            childView.layout(left, top, right, bottom);
        }
        lineHeightList.clear();
    }

    public void onSetData() {
    }

    /**
     * 设置数据
     *
     * @param list
     * @param listener
     */
    public void onSetData(List<String> list, OnLabelListerner listener) {
        if (list == null) {
            return;
        }
        int s = list.size();
        for (int i = 0; i < s; i++) {
            addView(onCreatTextView(list.get(i), listener));
        }
    }

    /**
     * 创建textview
     *
     * @param text
     * @param listener
     * @return
     */
    private TextView onCreatTextView(String text, final OnLabelListerner listener) {
        final TextView textView = new TextView(mContext);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 0, 0);
        textView.setLayoutParams(params);
        textView.setText(text);
        textView.setTextColor(mContext.getResources().getColor(R.color.black));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setGravity(Gravity.CENTER);
        textView.setTag(text);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick((String) textView.getTag());
            }
        });
        return textView;
    }

    public interface OnLabelListerner {
        void onClick(String text);
    }
}
