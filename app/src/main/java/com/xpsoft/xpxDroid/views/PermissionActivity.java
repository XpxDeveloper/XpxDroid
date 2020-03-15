package com.xpsoft.xpxDroid.views;

import android.content.pm.PackageManager;
import androidx.annotation.NonNull;

/**
 * Created by XPSoft on 2018/4/16.
 */

public abstract class PermissionActivity extends baseActivity {



    public abstract void onRequestPermissionsSuccess(int requestCode);
    public abstract void onRequestPermissionsFail(int requestCode);
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            onRequestPermissionsSuccess(requestCode);
        } else {
            //startInstallPermissionSettingActivity();
            onRequestPermissionsFail(requestCode);
        }
    }


}
