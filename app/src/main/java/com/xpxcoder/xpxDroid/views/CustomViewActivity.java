package com.xpxcoder.xpxDroid.views;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Message;
import androidx.annotation.Nullable;

import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.databinding.ActivityCustomviewBinding;
import com.xpxcoder.xpxDroid.models.eventbus.xpxEvent;
import com.xpxcoder.xpxDroid.tools.MapTestDataUtils;
import com.xpxcoder.xpxDroid.widget.AppTitleBar;

/**
 * Created by XPSoft on 2018/4/14.
 * 自定义控件
 */

public class CustomViewActivity extends baseFragActivity{

    private ActivityCustomviewBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_customview);
        mBinding.appTitleBar.setTitle("自定义控件").setOnTitleBarClickListener(new AppTitleBar.OnTitleBarClickListener() {
            @Override
            public void onBack() {
                finish();
            }
        });
        mBinding.image.setImageBitmap(MapTestDataUtils.getMarkerPowerBitmap2(mContext,"测试测试",1.2f,2));
    }

    @Override
    public void XpxEvent(xpxEvent _xpxEvent) {

    }

    @Override
    public void handleMsg(Message msg) {

    }
}
