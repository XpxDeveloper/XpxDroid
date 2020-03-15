package com.xpsoft.xpxDroid.models.eventbus;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by XPSoft on 2018/2/11.
 */

public class xpxEvent {
    public static final int XPX_EVENT_TYPE1 = 10000;

    private int Code;

    @IntDef({XPX_EVENT_TYPE1})
    @Retention(RetentionPolicy.CLASS)
    public @interface xpxEventType {
    }

    public xpxEvent(@xpxEventType int _code) {
        this.Code = _code;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }
}
