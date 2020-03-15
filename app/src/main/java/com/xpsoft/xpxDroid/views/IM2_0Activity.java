package com.xpsoft.xpxDroid.views;

import android.content.res.Configuration;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Message;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.Window;

import com.squareup.otto.Subscribe;
import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.adapter.baseFragmentAdapter;
import com.xpsoft.xpxDroid.databinding.ActivityMainBinding;
import com.xpsoft.xpxDroid.fragment.baseFragment;
import com.xpsoft.xpxDroid.fragment.lazybase_02.desktopSecond_02_Fragment;
import com.xpsoft.xpxDroid.fragment.lazybase_02.dispatch_02_Fragment;
import com.xpsoft.xpxDroid.global.baseApp;
import com.xpsoft.xpxDroid.models.eventbus.xpxEvent;

import java.util.ArrayList;
import java.util.List;

public class IM2_0Activity extends baseFragActivity {

    private ActivityMainBinding mBinding;
    private List<baseFragment> mFragList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();

    @Override
    public void handleMsg(Message msg) {

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 设置无标题栏
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        baseApp.bus.register(this);

        mTitleList.add("调度");
        mTitleList.add("地图");
        mTitleList.add("视频");
        mTitleList.add("发现");
        mTitleList.add("我的");

        mFragList.add(new dispatch_02_Fragment().setLayoutId(R.layout.fragment_dispatch).setParentViewPager(mBinding.ViewPager));
        mFragList.add(new desktopSecond_02_Fragment().setLayoutId(R.layout.fragment_first).setParentViewPager(mBinding.ViewPager));
        mFragList.add(new desktopSecond_02_Fragment().setLayoutId(R.layout.fragment_first).setParentViewPager(mBinding.ViewPager));
        mFragList.add(new desktopSecond_02_Fragment().setLayoutId(R.layout.fragment_first).setParentViewPager(mBinding.ViewPager));
        mFragList.add(new desktopSecond_02_Fragment().setLayoutId(R.layout.fragment_first).setParentViewPager(mBinding.ViewPager));


        baseFragmentAdapter mFragAdapter = new baseFragmentAdapter(getSupportFragmentManager(), mTitleList, mFragList);
        mBinding.ViewPager.setForbidScroll(true);
        mBinding.ViewPager.setOffscreenPageLimit(mFragList.size());//目前是5个菜单，设置为5可以避免资源销毁问题
        mBinding.ViewPager.setAdapter(mFragAdapter);//给ViewPager设置适配器
        mBinding.mTabLayout.setupWithViewPager(mBinding.ViewPager);//将TabLayout和ViewPager关联起来。
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        baseApp.bus.unregister(this);
    }

    @Override
    @Subscribe
    public void XpxEvent(xpxEvent _xpxEvent) {
        for (baseFragment _lazy:mFragList
             ) {
            _lazy.XpxEvent(_xpxEvent);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged:sadfasdf ");
        for (baseFragment _lazy:mFragList
                ) {
            _lazy.resetHasShow();
        }
    }
}
