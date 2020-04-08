package com.xpxcoder.xpxDroid.tools.dialogs;

import android.app.Activity;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.databinding.DialogBaseTest1Binding;
import com.xpxcoder.xpxDroid.tools.UiUtils;

import java.util.List;

/**
 * Created by XPSoft on 2018/3/26.
 * 对话框基类
 */

public abstract class DialogBase extends DialogFragment {

    protected String TAG=getClass().getSimpleName();
    protected Context mContext;
    protected String mTitle="";
    private DialogBaseTest1Binding mBinding;
    protected int mMarginWidth;//水平跟屏幕的间距,是2边间距的总和
    protected int mWidth;
    protected DialogBase mSelf;
    protected boolean mFullSreen;//是否设置全屏显示，如果true，则mDpWidth、mDpHeight就没用了

    public DialogBase() {
        super();
        mSelf=this;
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
        mBinding= DataBindingUtil.inflate(inflater,R.layout.dialog_base_test1,container,false);
        View view =mBinding.getRoot();

        mBinding.dialogHeader.setTitle(mTitle).setListener(new DialogBaseHeader.Listener() {
            @Override
            public void close() {
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Window window = getDialog().getWindow();
//                getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (mFullSreen) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//注意此处
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//这2行,和上面的一样,注意顺序就行;
        }else if(mWidth>0){
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//注意此处
            window.setLayout(mWidth, WindowManager.LayoutParams.WRAP_CONTENT);//这2行,和上面的一样,注意顺序就行;
        } else if (mMarginWidth > 0) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//注意此处
            WindowManager.LayoutParams wlp = window.getAttributes();
            WindowManager wm=window.getWindowManager();
            int width=wm.getDefaultDisplay().getWidth()- mMarginWidth;
            window.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);//这2行,和上面的一样,注意顺序就行;
        }else {
            //设置默认的左右边距
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//注意此处
            WindowManager.LayoutParams wlp = window.getAttributes();
            WindowManager wm=window.getWindowManager();
            int width=wm.getDefaultDisplay().getWidth()- UiUtils.dp2px(mContext, 30);
            window.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);//这2行,和上面的一样,注意顺序就行;
            Log.i(TAG, "onActivityCreated: "+wlp.width+"，"+UiUtils.dp2px(mContext, 30));
        }

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
    public DialogBase setHeaderTitle(String _title){
        mTitle=_title;
        return this;
    }

    public DialogBase setMarginWidth(int marginWidth) {
        if (marginWidth > 0) {
            mMarginWidth = marginWidth;
        }

        return this;
    }

    public DialogBase setWidth(int width) {
        this.mWidth = width;
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
        wlp.width = UiUtils.dp2px(mContext, dpWidth);
        wlp.height = UiUtils.dp2px(mContext, dpHeight);
        window.setAttributes(wlp);
    }
    public interface Listener {
        void ok(List<DialogResultModel> list);
    }

    public abstract void setListener(Listener _Listener);

    public interface FootClickListener {
        void click(DialogFragment dialog, View view);
    }
    public static class customBtn {
        private DialogFragment dialogFragment;
        private String btnName = "";
        private int ResId=0;//资源
        private FootClickListener listener;

        public customBtn(DialogFragment dialogFragment,String btnName, FootClickListener listener) {
            this.dialogFragment=dialogFragment;
            this.btnName = btnName;
            this.listener = listener;
        }
        public customBtn(DialogFragment dialogFragment,String btnName,int resId, FootClickListener listener) {
            this.dialogFragment=dialogFragment;
            this.btnName = btnName;
            this.ResId=resId;
            this.listener = listener;
        }

        public String getBtnName() {
            return btnName;
        }

        public int getResId() {
            return ResId;
        }

        public void setResId(int resId) {
            ResId = resId;
        }

        public void setBtnName(String btnName) {
            this.btnName = btnName;
        }

        public DialogFragment getDialogFragment() {
            return dialogFragment;
        }

        public FootClickListener getListener() {
            return listener;
        }

        public void setListener(FootClickListener listener) {
            this.listener = listener;
        }
    }
}
