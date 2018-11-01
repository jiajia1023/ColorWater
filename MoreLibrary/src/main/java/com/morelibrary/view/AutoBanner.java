package com.morelibrary.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
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
    private int lastPosition = 0;
    private int count = 0;
    private ImageView.ScaleType mScaleType = ImageView.ScaleType.CENTER_CROP;

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
     * 设置banner的宽高
     *
     * @param width
     * @param height
     */
    public void setBannerWidthAndHeight(int width, int height) {
        mViewPager.setLayoutParams(new LayoutParams(width, height));
    }


    /**
     * 设置小圆点位于图片下面哪个方位（左、中、右）
     *
     * @param verb a layout verb, such as {@link #ALIGN_PARENT_LEFT}
     */
    public void onSetMode_Below(int verb) {
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.autoBanner_viewpager);
        params.addRule(verb);
        mGroup.setLayoutParams(params);
    }

    /**
     * 设置小圆点在父布局的下方
     *
     * @param verb a layout verb, such as {@link #ALIGN_PARENT_LEFT}
     */
    public void onSetMode_BottomParent(int verb) {
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.autoBanner_viewpager);
        params.addRule(verb);
        mGroup.setLayoutParams(params);
    }

    /**
     * 设置图片的显示模式
     *
     * @param mScaleType
     */
    public void setScaleType(ImageView.ScaleType mScaleType) {
        this.mScaleType = mScaleType;
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
        mGroup.removeAllViews();

        int index = 0;
        for (final String url : imgList) {
            ImageView imageView = new ImageView(mContext);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            imageView.setScaleType(mScaleType);
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
            radioButton.setPadding(15, 0, 0, 0);
            if (index == 0) {
                radioButton.setButtonDrawable(selectDra);
            } else {
                radioButton.setButtonDrawable(defaultDra);
            }
            mGroup.addView(radioButton);
            index++;
        }
        count = mViewList.size();
        mViewPager.setAdapter(new AutoBannerAdapter());
        if (index == 1) {
            mGroup.setVisibility(View.GONE);
        }
    }

    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }


        @Override
        public void onPageSelected(int position) {
//            Log.e("======", "position=" + position + "---lastP=" + lastPosition);
            ((RadioButton) mGroup.getChildAt(position)).setButtonDrawable(selectDra);
            ((RadioButton) mGroup.getChildAt(lastPosition)).setButtonDrawable(defaultDra);
            lastPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            //state 1-开始 2-滑动 0-停止
            currentPosition = mViewPager.getCurrentItem();
//            Log.e("=====2=", " mViewPager.getCurrentItem()=" + mViewPager.getCurrentItem() + "--count=" + count + "---state=" + state);
            switch (state) {
                case 0://No operation
                    if (currentPosition == 0 && mLastP == 0) {
                        mViewPager.setCurrentItem(count, false);
                    } else if (currentPosition == count - 1 && mLastP == count - 1) {
                        mViewPager.setCurrentItem(0, false);
                    }
                    mLastP = mViewPager.getCurrentItem();
                    break;
                case 1://start Sliding
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
