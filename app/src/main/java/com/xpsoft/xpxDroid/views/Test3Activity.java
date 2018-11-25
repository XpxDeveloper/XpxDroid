package com.xpsoft.xpxDroid.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;

import com.squareup.otto.Subscribe;
import com.xpsoft.xpxDroid.BR;
import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.databinding.ActivityTest3Binding;
import com.xpsoft.xpxDroid.global.baseApp;
import com.xpsoft.xpxDroid.models.databinding.Test3;
import com.xpsoft.xpxDroid.models.eventbus.xpxEvent;

/**
 * Created by XPSoft on 2018/3/20.
 */

public class Test3Activity extends baseFragActivity {
    private Test3 user = new Test3();

    @Override
    public void handleMsg(Message msg) {

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTest3Binding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_test3);

        viewDataBinding.setTest3(user);
        //通过setVariable设置变量值
//        viewDataBinding.setVariable(BR.test3listener,new Test3.test3Listener());
        baseApp.bus.post(new xpxEvent(xpxEvent.XPX_EVENT_TYPE1));
    }

    @Subscribe
    @Override
    public void XpxEvent(xpxEvent _xpxEvent) {

    }
   /* public void click(View view){
        user.setContent("现在是："+String.valueOf(System.currentTimeMillis()));
    }*/
}
