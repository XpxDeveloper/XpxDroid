package com.xpxcoder.xpxDroid.tools.dialogs;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.databinding.DialogBaseTest1Binding;
import com.xpxcoder.xpxDroid.tools.UiUtils;
import com.xpxcoder.xpxDroid.widget.MyTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPSoft on 2018/3/29.
 * 简单的提示对话框
 */

public class DialogNext extends DialogBaseImpl {


    private View mView;
    private DialogBaseTest1Binding mBinding;
    private View mCustomView;//自定义
    private String mCustomText = "";
    private boolean mHideHeader;//隐藏默认的标题栏。（改为显示标题到中间内容区域？）
    private boolean mShowHeaderTitleToContent;
    private boolean mShowHeaderIconToContent;
    private int mHeaderIconResId =0;
    private boolean mShowSimpleHeader;

    //自定义按钮
    private List<customBtn> mList = new ArrayList<>();

    public DialogNext() {
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
            //标题
            if(mShowHeaderIconToContent&&mHeaderIconResId>0){
                ImageView imageView=new ImageView(mContext);
                imageView.setBackgroundResource(mHeaderIconResId);
                ViewGroup.LayoutParams lp = new LinearLayout.LayoutParams(UiUtils.dp2px(mContext,45),UiUtils.dp2px(mContext,45));
                ((LinearLayout.LayoutParams) lp).gravity=Gravity.CENTER;
                ((LinearLayout.LayoutParams) lp).topMargin=UiUtils.dp2px(mContext,25);
                imageView.setLayoutParams(lp);
                mBinding.llContent.addView(imageView);
            }else if(mShowHeaderTitleToContent){
                TextView textView=new TextView(mContext);
                textView.setText(mTitle);
                textView.setHeight(UiUtils.dp2px(mContext,50));
                textView.setGravity(Gravity.CENTER);
                mBinding.llContent.addView(textView);
            }
            //内容
            MyTextView textView=new MyTextView(mContext);
            textView.setText(mCustomText);
//            textView.setPadding(50,60,5,50);
//            textView.setBackgroundColor(Color.RED);
            int px1=UiUtils.dp2px(mContext,30);
            textView.setMinHeight(px1);
            textView.setGravity(Gravity.LEFT);
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int px2=UiUtils.dp2px(mContext,30);
            int px3=UiUtils.dp2px(mContext,20);
            lp.setMargins(px2,UiUtils.dp2px(mContext,20),px2,px3);
            textView.setLayoutParams(lp);
            mBinding.llContent.addView(textView);
        }
        if(mShowSimpleHeader){
            mBinding.dialogHeader.showSimpleHeader();
            mBinding.dialogHeader.setLayoutParams(new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, UiUtils.dp2px(mContext,5)));
            mBinding.dialogHeader.setBackgroundResource(R.drawable.dialog_header_background_radius_lrb);
        }else if(mHideHeader){
            mBinding.dialogHeader.setVisibility(View.GONE);
        }else {
            mBinding.dialogHeader.setTitle(mTitle).setListener(new DialogBaseHeader.Listener() {
                @Override
                public void close() {
                    dismiss();
                }
            });
        }

        if(mList.size()>0){
            mBinding.dialogFooter.addCustomBtns(mList);
        }else {
            mBinding.dialogFooter.setListener(new DialogBaseFooter.Listener() {
                @Override
                public void confirm() {
                    dismiss();
                }
            });
        }

        return mView;
    }

    public void setCustomView(View CustomView) {
        this.mCustomView = CustomView;
    }

    public DialogNext setCustomText(String CustomText) {
        this.mCustomText = CustomText;
        return this;

    }
    public DialogNext hideHeader(){
        mHideHeader=true;
        return this;
    }
    public DialogNext showHeaderTitleToContent(){
        mShowHeaderTitleToContent=true;
        return this;
    }

    public DialogNext ShowHeaderIconToContent(int resId) {
        this.mShowHeaderIconToContent = true;
        this.mHeaderIconResId =resId;
        return this;
    }

    public DialogNext showSimpleHeader(){
        mShowSimpleHeader=true;
        return this;
    }
    public DialogNext addCustomBtn(customBtn customBtn){
        mList.add(customBtn);
        return this;
    }
    public DialogNext addCustomBtn(String btnName, FootClickListener listener) {
        if (!TextUtils.isEmpty(btnName) && listener != null) {
            mList.add(new customBtn(this,btnName, listener));
        }
        return this;
    }

    @Override
    public void setListener(Listener Listener) {

    }
}
