package com.xpsoft.xpxDroid.tools.dialogs;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.databinding.DialogBaseTest1Binding;
import com.xpsoft.xpxDroid.tools.uiUtils;

/**
 * Created by XPSoft on 2018/3/26.
 * 对话框基类
 */

public class DialogBaseImpl extends DialogBase {

    protected String TAG=getClass().getSimpleName();
    protected Context mContext;
    protected String mTitle="";
    private DialogBaseTest1Binding mBinding;
    protected boolean mFullSreen;//是否设置全屏显示，如果true，则mDpWidth、mDpHeight就没用了

    public DialogBaseImpl() {
        super();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

//        View view = inflater.inflate(R.layout.dialog_base_test1, ((ViewGroup) window.findViewById(android.R.id.content)), false);//需要用android.R.id.content这个view

        mBinding= DataBindingUtil.inflate(inflater,R.layout.dialog_base_test1,container,false);
        View view =mBinding.getRoot();// inflater.inflate(R.layout.dialog_base_test1, container);

        mBinding.dialogHeader.setTitle(mTitle).setListener(new DialogBaseHeader.Listener() {
            @Override
            public void close() {
                dismiss();
            }
        });

        //部分机型弹出dialog时，顶部会出现一条蓝色的横线，百度后，可以通过下面的方法处理 begin
        int dividerId = mContext.getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider =getDialog(). findViewById(dividerId);
        if(divider!=null){
            divider.setBackgroundColor(Color.TRANSPARENT);
        }
        //部分机型弹出dialog时，顶部会出现一条蓝色的横线，百度后，可以通过下面的方法处理 end
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /*@Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            super.onCreateDialog(savedInstanceState);
            //为了样式统一和兼容性，可以使用 V7 包下的 AlertDialog.Builder
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // 设置主题的构造方法
            // AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
            builder.setTitle("注意：")
                    .setMessage("是否退出应用？")
                    .setPositiveButton("确定", null)
                    .setNegativeButton("取消", null)
                    .setCancelable(false);
            //builder.show(); // 不能在这里使用 show() 方法
            return builder.create();
        }*/
    public void setHeaderLayout() {

    }

    public void setFooterLayout() {

    }
    public DialogBaseImpl setHeaderTitle(String _title){
        mTitle=_title;
        return this;
    }


    public void setFullScreen() {
        mFullSreen = true;
        return;
        // 设置宽度为屏宽、位置靠近屏幕底部
        /*Window window = getDialog().getWindow();
        window.setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);*/
    }

    public void setCustomSize(int dpWidth, int dpHeight) {
        // 设置宽度为屏宽、位置靠近屏幕底部
        Window window = this.getDialog().getWindow();
        window.setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = uiUtils.dp2px(mContext, dpWidth);
        wlp.height = uiUtils.dp2px(mContext, dpHeight);
        window.setAttributes(wlp);
    }

    @Override
    public void setListener(Listener Listener) {

    }
}
