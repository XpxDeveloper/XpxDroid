package com.xpsoft.xpxDroid.fragment.lazybase_02;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ProgressBar;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.databinding.FragmentLazy02Binding;
import com.xpsoft.xpxDroid.fragment.baseFragment;
import com.xpsoft.xpxDroid.models.eventbus.xpxEvent;

/**
 * Created by XPSoft on 2018/4/15.
 */

public abstract class lazyFragment extends baseFragment {

    protected int mResId;
    protected View mRootView;
    private ViewStub mViewStub;
    private ProgressBar pbWait;
    private FragmentLazy02Binding mBinding;


    public lazyFragment() {
    }

    public abstract lazyFragment setLayoutId(int resId);

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            if (!mHasShow) {
                initRealView();
                mHasShow = true;
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            //TODO now it's visible to user
            onResume();
        } else {
            //TODO now it's invisible to user
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_lazy_02, null, false);
        mRootView = mBinding.getRoot();
        mViewStub = (ViewStub) mRootView.findViewById(R.id.viewStub);
        pbWait = (ProgressBar) mRootView.findViewById(R.id.pbWait);
        return mRootView;
    }

    public void initRealView() {
        if (mViewStub != null) {
            mViewStub.setLayoutResource(mResId);
            mViewStub.inflate();
            ((ViewGroup) mRootView).removeView(pbWait);
            mViewStub = null;//这时候，根据ViewStub的特性，自身已经从界面结构中移除
        }
        /*else {
            mViewStub=new ViewStub(mContext);
            ((ViewGroup) mRootView).addView(mViewStub);
            mViewStub.setLayoutResource(mResId);
            mViewStub.inflate();
            mViewStub = null;//这时候，根据ViewStub的特性，自身已经从界面结构中移除

            *//*((ViewGroup) mRootView).removeAllViews();
            View view = mInflater.inflate(mResId, null);
            ((ViewGroup) mRootView).addView(view);*//*
        }*/

    }


    @Override
    public void XpxEvent(xpxEvent _xpxEvent) {

    }
}
