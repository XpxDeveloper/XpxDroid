package com.xpsoft.xpxDroid.global;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
//import com.mob.MobSDK;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.squareup.otto.Bus;
import com.tencent.smtt.sdk.QbSdk;
import com.xpsoft.xpxDroid.tools.AlarmManagerUtils;
import com.xpsoft.xpxDroid.tools.ThreadUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import androidx.multidex.MultiDex;

/**
 * Created by XPSoft on 2018/2/15.
 */

public class baseApp extends Application {

    public static EventBus bus;
    private RefWatcher refWatcher;
    private static final int MEMORY_SIZE = 5 * 1024 * 1024;
    private static final int DISK_SIZE = 20 * 1024 * 1024;
    private String TAG=this.getClass().getSimpleName();

    public baseApp() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        MobSDK.init(this);
        refWatcher = setupLeakCanary();
        bus = new EventBus();
        ThreadUtils.clearAll();
        AlarmManagerUtils.getInstance().init(getApplicationContext());
        SDKInitializer.initialize(this);

        // 初始化 Image-Loader
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCache(new LruMemoryCache(MEMORY_SIZE))
                .diskCache(new UnlimitedDiscCache(new File(getCacheDir(),"caches")))
                .diskCacheSize(DISK_SIZE)
                .defaultDisplayImageOptions(options)
                .build();

        ImageLoader.getInstance().init(configuration);

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                Log.e(TAG, "uncaughtException: "+t.getName()+",class:"+t.getClass().getName()+",", e);
            }
        });
        tbs();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
    private void tbs(){
        QbSdk.initX5Environment(getApplicationContext(), new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {

            }
        });
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        baseApp leakApplication = (baseApp) context.getApplicationContext();
        return leakApplication.refWatcher;
    }
}
