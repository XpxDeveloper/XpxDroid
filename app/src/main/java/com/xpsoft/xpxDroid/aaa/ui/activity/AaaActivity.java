package com.xpsoft.xpxDroid.aaa.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import butterknife.OnClick;

import com.xpsoft.xpxDroid.aaa.presenter.AaaPresenter;
import com.xpsoft.xpxDroid.aaa.presenter.contract.IAaaContract;
import com.xpsoft.xpxDroid.base.OneBaseActivity;
import com.xpsoft.xpxDroid.core.rx.RxBus;
import com.xpsoft.xpxDroid.core.rx.RxEvent;
import com.xpsoft.xpxDroid.R;

/**
 * ************************************************************
 * Author: XPSoft
 * CreateTime: 2020-04-01 23:49:43
 * PackageName: com.xpsoft.xpxDroid.aaa.ui.activity
 * Description:
 * ************************************************************
 */
@Route(path = "")
public class AaaActivity extends OneBaseActivity<AaaPresenter> implements IAaaContract.IView {

    @Override
    protected boolean isDarkMode() {
        return super.isDarkMode();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aaa;
    }

    @Override
    protected void inject() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({})
    public void onViewClicked(View view) {
        switch (view.getId()) {

        }
    }
}
