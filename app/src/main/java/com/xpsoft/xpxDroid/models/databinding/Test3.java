package com.xpsoft.xpxDroid.models.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.xpsoft.xpxDroid.BR;

/**
 * Created by XPSoft on 2018/3/20.
 */

public class Test3 extends BaseObservable {
    private String content = "sdf";

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        notifyPropertyChanged(BR.content);
    }

    public static void click(Test3 test3) {
        Log.i("Test3.Test3Listener", "click: ");
        test3.setContent("现在是：" + String.valueOf(System.currentTimeMillis()));
    }
}
