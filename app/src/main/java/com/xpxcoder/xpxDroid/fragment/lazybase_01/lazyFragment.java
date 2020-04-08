package com.xpxcoder.xpxDroid.fragment.lazybase_01;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.fragment.baseFragment;
import com.xpxcoder.xpxDroid.models.eventbus.xpxEvent;
import com.xpxcoder.xpxDroid.tools.WidgetIdUtils;

/**
 * Created by XPSoft on 2018/2/9.
 */

public class lazyFragment extends baseFragment {

    protected String TAG = this.getClass().getName();
    private Class<?> mRealClass;
    protected lazyFragment mRealFrag;//我真正需要的fragment内容
    protected lazyFragment mParentFrag;//
    protected extraListener mExtraListener;
    private ProgressBar pbWait;
    protected boolean mDefaultExtraAction = true;//是否选用默认的加载行为

    public lazyFragment() {
        super();
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

    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }

    @Override
    public lazyFragment setLayoutId(int resId) {
return this;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (mExtraListener != null && mDefaultExtraAction) {
            mExtraListener.doExtraThing();
        }
        mContainer = inflater.inflate(R.layout.fragment_lazy, null);
        mContainer.setId(WidgetIdUtils.generateViewId());
        pbWait = (ProgressBar) mContainer.findViewById(R.id.pbWait);
        return mContainer;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            if (!mHasShow) {
                initRealFrag();
                mHasShow = true;
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public lazyFragment setRealFragClass(Class<?> _class) {
        mRealFrag = null;
        mRealClass = _class;
        return this;
    }

    private void initRealFrag() {
        if (mRealClass == null) return;
        Log.i(TAG, "initRealFrag: " + mRealClass);
        try {
            mRealFrag = (lazyFragment) mRealClass.newInstance();
            mRealFrag.setExtraListener(new extraListener() {
                @Override
                public void doExtraThing() {
                    ((ViewGroup) mContainer).removeView(pbWait);
                }
            });
            mRealFrag.mParentFrag=this;
            mRealFrag.setParentViewPager(mParentViewPager);
            mFragmentManager.beginTransaction().replace(mContainer.getId(), mRealFrag).commit();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void XpxEvent(xpxEvent _xpxEvent) {
        if (mRealFrag != null) {
            mRealFrag.XpxEvent(_xpxEvent);
        }
    }

    public interface extraListener {
        void doExtraThing();
    }

    public void setExtraListener(extraListener _extraListener) {
        this.mExtraListener = _extraListener;
    }


    public boolean isVisibleToUserAfterSwitch() {
        return isVisibleToUserAfterSwitch;
    }

    public void setVisibleToUserAfterSwitch(boolean visibleToUserAfterSwitch) {
        isVisibleToUserAfterSwitch = visibleToUserAfterSwitch;
    }

    @Override
    public void initRealView() {

    }
}
