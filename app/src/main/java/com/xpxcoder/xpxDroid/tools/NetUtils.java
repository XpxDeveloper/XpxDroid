package com.xpxcoder.xpxDroid.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by XPSoft on 2018/4/15.
 */

public class NetUtils {
    public static class NetType {
        public static final int MOBILE=1000;
        public static final int WIFI=1001;
        public int state;
    }

    public static boolean isNetworkAvailable(Context context) {
        // 获得网络状态管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 建立网络数组
            NetworkInfo[] net_info = connectivityManager.getAllNetworkInfo();

            if (net_info != null) {
                for (int i = 0; i < net_info.length; i++) {
                    // 判断获得的网络状态是否是处于连接状态
                    if (net_info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static NetType getAPNType(Context context) {
        NetType netType = new NetType();
        return netType;
    }
}
