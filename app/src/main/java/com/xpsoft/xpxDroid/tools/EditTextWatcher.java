package com.xpsoft.xpxDroid.tools;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by XPSoft on 2018/1/25.
 */

public class EditTextWatcher implements TextWatcher {

    private EditText editText;
    private String rt;
    private OnCallBackListener mOnCallBackListener;

    public interface OnCallBackListener{
        void afterTextChanged(String _str);
    }

    public EditTextWatcher(EditText editText, OnCallBackListener _OnCallBackListener) {
        this.editText = editText;
        this.mOnCallBackListener=_OnCallBackListener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {


    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before,
                              int count) {
        String searchkey = this.editText.getText().toString().trim();

    }

    @Override
    public void afterTextChanged(Editable s) {
        // editText.setText(rt);
        String text = this.editText.getText().toString().trim();
        if(mOnCallBackListener!=null){
            mOnCallBackListener.afterTextChanged(text);
        }
    }

}
