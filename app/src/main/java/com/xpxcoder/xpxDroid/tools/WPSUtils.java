package com.xpxcoder.xpxDroid.tools;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.core.content.FileProvider;

import java.io.File;

/**
 * Created by XPSoft on 2018/8/1.
 */

public class WPSUtils {
    public static boolean openFile(String path, Activity activity) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("Open_Mode", "Read_Only");
        bundle.putBoolean("Send_Close_Broad", true);
        bundle.putString("Third_Package", "jg.hczh");
        bundle.putBoolean("Clear_Buffer", true);
        bundle.putBoolean("Clear_Trace", true);
        // bundle.putBoolean(CLEAR_FILE, true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setClassName("cn.wps.moffice_eng", "cn.wps.moffice.documentmanager.PreStartActivity2");

        File file = new File(path);
        if (file == null || !file.exists()) {
            return false;
        }

        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        intent.putExtras(bundle);

        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean openFile(String path, Context context) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("Open_Mode", "Read_Only");
        bundle.putBoolean("Send_Close_Broad", true);
        bundle.putString("Third_Package", "jg.hczh");
        bundle.putBoolean("Clear_Buffer", true);
        bundle.putBoolean("Clear_Trace", true);
        // bundle.putBoolean(CLEAR_FILE, true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setClassName("cn.wps.moffice_eng", "cn.wps.moffice.documentmanager.PreStartActivity2");

        File file = new File(path);
        if (file == null || !file.exists()) {
            return false;
        }

        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileProvider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }

//		Uri uri = Uri.fromFile(file);
//		intent.setData(uri);
        intent.putExtras(bundle);

        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }
}
