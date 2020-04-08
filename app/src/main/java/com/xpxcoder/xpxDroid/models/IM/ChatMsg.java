package com.xpxcoder.xpxDroid.models.IM;

import com.xpxcoder.xpxDroid.global.enumType;

/**
 * Created by XPSoft on 2018/2/20.
 */

public class ChatMsg {
    private enumType.MSG_TYPE _msgType= enumType.MSG_TYPE.MSG_TYPE_TEXT;//默认
    private String _rawMsg="";
    private String _msgAfterAnalysize="";

    public ChatMsg(){

    }
    public static ChatMsg toModel(){
        try {
            ChatMsg model=new ChatMsg();
            return model;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public enumType.MSG_TYPE get_msgType() {
        return _msgType;
    }

    public void set_msgType(enumType.MSG_TYPE _msgType) {
        this._msgType = _msgType;
    }

    public String get_rawMsg() {
        return _rawMsg;
    }

    public void set_rawMsg(String _rawMsg) {
        this._rawMsg = _rawMsg;
    }

    public String get_msgAfterAnalysize() {
        return _msgAfterAnalysize;
    }

    public void set_msgAfterAnalysize(String _msgAfterAnalysize) {
        this._msgAfterAnalysize = _msgAfterAnalysize;
    }
}
