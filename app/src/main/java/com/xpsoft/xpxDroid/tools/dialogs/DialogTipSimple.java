package com.xpsoft.xpxDroid.tools.dialogs;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.databinding.DialogBaseTest1Binding;

/**
 * Created by XPSoft on 2018/3/29.
 * 简单的提示对话框
 */

public class DialogTipSimple extends DialogBaseImpl {


    private View mView;
    private DialogBaseTest1Binding mBinding;
    private View mCustomView;//自定义
    private String mCustomText = "";

    public DialogTipSimple() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_base_test1, container, false);
        mView = mBinding.getRoot();
        if (mCustomView != null) {
            mBinding.llContent.addView(mCustomView);
        }else if(!TextUtils.isEmpty(mCustomText)){
            TextView textView=new TextView(mContext);
            textView.setText(mCustomText);
            textView.setGravity(Gravity.CENTER);
            mBinding.llContent.addView(textView);
        }
        mBinding.dialogHeader.setTitle(mTitle).setListener(new DialogBaseHeader.Listener() {
            @Override
            public void close() {
                dismiss();
            }
        });
        mBinding.dialogFooter.setListener(new DialogBaseFooter.Listener() {
            @Override
            public void confirm() {
                dismiss();
            }
        });
        return mView;
    }

    public void setCustomView(View CustomView) {
        this.mCustomView = CustomView;
    }

    public void setCustomText(String CustomText) {
        this.mCustomText = CustomText;

    }

    @Override
    public void setListener(Listener Listener) {

    }
}
