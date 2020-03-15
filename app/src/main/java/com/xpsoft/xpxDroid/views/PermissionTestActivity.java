package com.xpsoft.xpxDroid.views;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.xpsoft.xpxDroid.R;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

/**
 * Created by XPSoft on 2018/9/26.
 */

public class PermissionTestActivity extends baseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissiontest);
    }

    public void onClick_btn1(View view) {
        AndPermission.with(this)
                .runtime()
                .permission(
                        Permission.Group.STORAGE
                )
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Log.e(TAG, "onAction: onGranted");
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Log.e(TAG, "onAction: onDenied");
                    }
                })
                .start();
    }
}
