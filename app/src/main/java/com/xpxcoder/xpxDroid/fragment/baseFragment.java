package com.xpxcoder.xpxDroid.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;

import com.xpxcoder.xpxDroid.models.eventbus.xpxEvent;
import com.xpxcoder.xpxDroid.widget.ViewPagerCustomScroll;

/**
 * Created by XPSoft on 2018/2/9.
 */

public abstract class baseFragment extends Fragment {
    protected Context mContext;
    protected View mContainer;
    protected boolean mHasShow = false;
    protected LayoutInflater mInflater;
    protected FragmentManager mFragmentManager;
    protected boolean isVisibleToUserAfterSwitch;//切换Viewpager时，是否可见
    protected ViewPagerCustomScroll mParentViewPager;

    public abstract void XpxEvent(xpxEvent _xpxEvent);
    public abstract baseFragment setLayoutId(int resId);
    public abstract void initRealView();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getChildFragmentManager();
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    public void setVisibleToUserAfterSwitch(boolean visibleToUserAfterSwitch) {
        isVisibleToUserAfterSwitch = visibleToUserAfterSwitch;
    }
    public baseFragment setParentViewPager(ViewPagerCustomScroll _ParentViewPager) {
        this.mParentViewPager = _ParentViewPager;
        return this;
    }
    public ViewPagerCustomScroll getParentViewPager() {
        return mParentViewPager;
    }

    /**
     * 当屏幕旋转时，需要重置，重新加载界面
     */
    public void resetHasShow() {
        this.mHasShow = false;
        initRealView();
        this.mHasShow = true;
    }
}
