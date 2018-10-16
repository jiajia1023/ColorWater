package com.morelibrary.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * author:jjj
 * time: 2018/10/15 16:34
 * TODO:基础的fragment
 */
public abstract class BaseFragment extends Fragment {
    protected View baseLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (baseLayout == null) {
            baseLayout = inflater.inflate(onLayoutResId(), container, false);
        } else {
            ViewGroup parent = (ViewGroup) baseLayout.getParent();
            if (parent != null) {
                parent.removeView(baseLayout);
            }
        }
        onInitView(baseLayout);
        return baseLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onBindData();
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
    protected abstract void onInitView(View view);

    /**
     * 处理逻辑
     */
    protected abstract void onBindData();

}
