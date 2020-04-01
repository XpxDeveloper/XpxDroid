package com.xpsoft.xpxDroid.aaa.presenter;

import android.content.Context;
import javax.inject.Inject;

import com.xpsoft.xpxDroid.aaa.model.AaaRetrofit;
import com.xpsoft.xpxDroid.core.rx.RxPresenter;
import com.xpsoft.xpxDroid.aaa.presenter.contract.IAaaContract;


/**
 * ************************************************************
 * Author: XPSoft
 * CreateTime: 2020-04-01 23:49:43
 * PackageName: com.xpsoft.xpxDroid.aaa.presenter
 * Description:
 * ************************************************************
 */
public class AaaPresenter extends RxPresenter<IAaaContract.IView> implements IAaaContract.IPresenter {
    private Context context;
    private AaaRetrofit retrofit;
    @Inject
    public AaaPresenter(Context context, AaaRetrofit retrofit) {
        this.context = context;
        this.retrofit = retrofit;
    }

}
