package com.morelibrary.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.morelibrary.MApplication;
import com.morelibrary.R;

/**
 * author:jjj
 * time: 2018/10/15 15:13
 * TODO:基础的activity
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static final String TITLE = "activity_title";
    protected Toolbar baseToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MApplication.getInstance().addActivity(this);

        onSetBaseView();
        onInitView();
        onBindData();
    }


    private void onSetBaseView() {
        if (hasTitle()) {
            setContentView(R.layout.activity_base);
            FrameLayout baseContentFl = findViewById(R.id.base_framelayout);
            RelativeLayout baseLayout = findViewById(R.id.base_layout);
            //替换Action
            baseToolbar = findViewById(R.id.base_toolbar);
            setSupportActionBar(baseToolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayShowHomeEnabled(true);
            }
            LayoutInflater.from(this).inflate(onLayoutResId(), baseContentFl);
            if (getIntent().hasExtra(TITLE)) {
                setTitle(getIntent().getStringExtra(TITLE));
            }
        } else {
            setContentView(onLayoutResId());
        }
    }

    /**
     * 修改返回按钮
     *
     * @param backIcon
     * @param onClickListener
     */
    protected void setBackIcon(int backIcon, View.OnClickListener onClickListener) {
        if (backIcon > 0) {
            baseToolbar.setNavigationIcon(backIcon);
        } else {
            baseToolbar.setNavigationIcon(R.mipmap.ic_back_arrow_white);
        }
        if (onClickListener == null) {
            onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            };
        }
        baseToolbar.setNavigationOnClickListener(onClickListener);
    }

    /**
     * 设置title
     *
     * @param title
     */
    protected void onSetTitle(String title) {
        setTitle(title);
    }

    /**
     * 返回布局文件的ID
     *
     * @return
     */
    @LayoutRes
    protected abstract int onLayoutResId();

    /**
     * 处理视图
     */
    protected abstract void onInitView();

    /**
     * 处理逻辑
     */
    protected abstract void onBindData();


    /**
     * 是否有标题-此处作为是否引用相同的布局
     *
     * @return
     */
    protected boolean hasTitle() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MApplication.getInstance().removeActivity(this);
    }
}
