package com.xpxcoder.xpxDroid.http.retrofit;

/**
 * Created by XPSoft on 2018/4/14.
 */

public class Fault extends RuntimeException {
    private int errorCode;

    public Fault(int errorCode,String message){
        super(message);
        errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}