package com.xpxcoder.xpxDroid.models.databinding;

import android.util.Log;

/**
 * Created by XPSoft on 2018/3/20.
 */

public class Test3Listener {
    public void click(Test3 test3){
        Log.i("Test3Listener", "click: ");
        test3.setContent("现在是："+String.valueOf(System.currentTimeMillis()));
    }
}
