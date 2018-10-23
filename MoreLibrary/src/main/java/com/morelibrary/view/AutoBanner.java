package com.morelibrary.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.morelibrary.R;
import com.morelibrary.image.ImageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * author:jjj
 * time: 2018/10/23 14:55
 * TODO:常规banner
 */
public class AutoBanner extends RelativeLayout {
    private ViewPager mViewPager;
    private RadioGroup mGroup;
    private Context mContext;
    private int selectDra = R.drawable.banner_selected;
    private int defaultDra = R.drawable.banner_unselect;
    private List<ImageView> mViewList;
    private int mLastP = 0;
    private int currentPosition;
    private int mPageNum = 0;//总的页数

    public AutoBanner(Context context) {
        super(context);
        init(context);
    }

    public AutoBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AutoBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        View mView = LayoutInflater.from(context).inflate(R.layout.view_banner_auto, null);
        mViewPager = mView.findViewById(R.id.autoBanner_viewpager);
        mGroup = mView.findViewById(R.id.autoBanner_radioGroup);
        addView(mView);
        mViewList = new ArrayList<>();
        mViewPager.addOnPageChangeListener(pageChangeListener);
    }


    /**
     * 设置数据
     *
     * @param imgList
     */
    public void onSetData(List<String> imgList) {
        if (imgList == null || imgList.size() == 0) {
            imgList = new ArrayList<>();
            imgList.add("-1");
        }
        int index = 0;
        for (final String url : imgList) {
            ImageView imageView = new ImageView(mContext);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageManager.onLoadImage(mContext, url, imageView);
            final int finalIndex = index;
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mBannerListener != null) {
                        mBannerListener.onClick(finalIndex, url);
                    }
                }
            });
            mViewList.add(imageView);


            RadioButton radioButton = new RadioButton(mContext);
            radioButton.setPadding(R.dimen.padding_autobanner, 0, 0, 0);
            if (index == 0) {
                radioButton.setButtonDrawable(selectDra);
            } else {
                radioButton.setButtonDrawable(defaultDra);
            }
            mGroup.addView(radioButton);
            index++;
        }
        mPageNum = mViewList.size();
        mViewPager.setAdapter(new AutoBannerAdapter());
        if (index == 1) {
            mGroup.setVisibility(View.GONE);
        }
    }

    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
//            Log.e("onPageScrolled：", "onPageScrolled：" + i + "--" + v + "--" + i1);
        }

        @Override
        public void onPageSelected(int position) {
            Log.e("onPageSelected：", "onPageSelected：" + position + "");
            currentPosition = position;
            if (mLastP != position) {
                ((RadioButton) mGroup.getChildAt(mLastP)).setButtonDrawable(defaultDra);
                ((RadioButton) mGroup.getChildAt(position)).setButtonDrawable(selectDra);
                mLastP = position;
            }

//            if (position == 0 || position == mGroup.getChildCount() + 1) {
//                //最后一张其实是第一张
//                position = 0;
//                ((RadioButton) mGroup.getChildAt(position)).setButtonDrawable(selectDra);
//                mLastP = 0;
//            } else {
//                ((RadioButton) mGroup.getChildAt(position - 1)).setButtonDrawable(selectDra);
//                mLastP = position - 1;
//            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.e("ScrollStateChanged：", "onPageScrollStateChanged：" + state + "");
////            若viewpager滑动未停止，直接返回
//            if (state != ViewPager.SCROLL_STATE_IDLE) return;
////        若当前为第一张，设置页面为倒数第二张
//            if (currentPosition == 0) {
//                mViewPager.setCurrentItem(mViewList.size()-2,false);
//            } else if (currentPosition == mViewList.size()-1) {
////        若当前为倒数第一张，设置页面为第二张
//                mViewPager.setCurrentItem(1,false);
//            }
            //state 1-开始 2-滑动 0-停止
//            switch (state) {
//                case 1:
//if (currentPosition==0){
//
//}
//                    break;
//                case 2:
//                    break;
//                case 0:
//                    break;
//            }


            currentPosition = mViewPager.getCurrentItem();
            Log.e("currentPosition：", "---" + currentPosition + "--mPageNum="+mPageNum);

            switch (state) {
                case 0://No operation
                    if (currentPosition == 0) {
                        mViewPager.setCurrentItem(mPageNum-1, false);
                    } else if (currentPosition == mPageNum-1 ) {
                        mViewPager.setCurrentItem(0, false);
                    }
                    break;
                case 1://start Sliding
                    if (currentPosition == mPageNum -1) {
                        mViewPager.setCurrentItem(0, false);
                    } else if (currentPosition == 0) {
                        mViewPager.setCurrentItem(mPageNum-1, false);
                    }
                    break;
                case 2://end Sliding
                    break;
            }
        }
    };

    class AutoBannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = mViewList.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

            return view == o;
        }
    }

    AutoBannerListener mBannerListener;

    public void setAutoBannerListener(AutoBannerListener mBannerListener) {
        this.mBannerListener = mBannerListener;
    }

    public interface AutoBannerListener {
        void onClick(int position, Object o);
    }
}
