package com.xpsoft.xpxDroid.aaa.model;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

import com.xpsoft.xpxDroid.core.base.BaseResponse;
import com.xpsoft.xpxDroid.core.base.BaseRetrofit;


 /**
  * ************************************************************
  * Author: XPSoft
  * CreateTime: 2020-04-01 23:49:43
  * PackageName: com.xpsoft.xpxDroid.aaa.model
  * Description:
  * ************************************************************
  */
public class AaaRetrofit extends BaseRetrofit {

    private IAaaApi iAaaApi;

    public AaaRetrofit(IAaaApi iAaaApi) {
        this.iAaaApi = iAaaApi;
    }

}
