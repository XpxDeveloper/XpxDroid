package com.xpxcoder.xpxDroid.views;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.databinding.ActivityDrawerBinding;
import com.xpxcoder.xpxDroid.widget.AppTitleBar;

/**
 * Created by XPSoft on 2018/2/19.
 */

public class DrawerActivity extends baseActivity{

    private ActivityDrawerBinding mBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_drawer);

        mBinding.appTitleBar.setTitle("抽屉式");
        mBinding.appTitleBar.setOnTitleBarClickListener(new AppTitleBar.OnTitleBarClickListener() {
            @Override
            public void onBack() {
                mBinding.drawer.openDrawer(Gravity.LEFT);
            }
        });
        mBinding.appTitleBar.setOnTitleBarRightClickListener("菜单", new AppTitleBar.OnTitleBarRightClickListener() {
            @Override
            public void onClick() {
                mBinding.drawer.openDrawer(Gravity.RIGHT);
            }
        });
        //获取头布局文件
        View headerView = mBinding.navigationView.getHeaderView(0);
        mBinding.drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        mBinding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //在这里处理item的点击事件
                return true;
            }
        });
    }
}
