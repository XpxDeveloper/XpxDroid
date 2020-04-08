package com.xpxcoder.xpxDroid.views;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by XPSoft on 2018/2/9.
 */

public class baseActivity extends AppCompatActivity{
    protected String TAG=getClass().getName();
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public static void actionStartActivity(Context _context, Class<?> _toAct) {
        Intent intent = new Intent(_context, _toAct);
        _context.startActivity(intent);
    }
}
