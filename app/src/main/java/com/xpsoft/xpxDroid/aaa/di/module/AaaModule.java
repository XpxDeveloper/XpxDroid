package com.xpsoft.xpxDroid.aaa.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import com.xpsoft.xpxDroid.lib.base.di.scope.RetrofitScope;
import com.xpsoft.xpxDroid.aaa.model.IAaaApi;
import com.xpsoft.xpxDroid.aaa.model.AaaRetrofit;

 /**
  * ************************************************************
  * Author: XPSoft
  * CreateTime: 2020-04-01 23:49:43
  * PackageName: com.xpsoft.xpxDroid.aaa.di.module
  * Description:
  * ************************************************************
  */
@Module
public class AaaModule {
    private final Activity activity;

    public AaaModule(Activity activity) {
        this.activity = activity;
    }
    @RetrofitScope
    @Provides
    public AaaRetrofit provideAaaRetrofit(Retrofit retrofit) {
        return new AaaRetrofit(retrofit.create(IAaaApi.class));
    }

}
