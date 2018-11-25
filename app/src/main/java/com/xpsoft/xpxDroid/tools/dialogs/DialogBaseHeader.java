package com.xpsoft.xpxDroid.tools.dialogs;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.databinding.DialogBaseHeaderBinding;

/**
 * Created by XPSoft on 2018/3/26.
 * 对话框标题栏
 */

public class DialogBaseHeader extends FrameLayout{

    private Context mContext;
    private LayoutInflater mInflater;
    private DialogBaseHeaderBinding mBinding;
    private Listener mListener;
    private boolean mShowSimpleHeader;


    public DialogBaseHeader(@NonNull Context context) {
        this(context,null);
    }

    public DialogBaseHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DialogBaseHeader(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        initView();
    }
    private void initView(){
        if(isInEditMode())return;
        mInflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding= DataBindingUtil.inflate(mInflater, R.layout.dialog_base_header,null,false);
        addView(mBinding.getRoot());

        mBinding.ibtnClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.close();
                }
            }
        });
    }
    public interface  Listener{
        void close();
    }

    public void setListener(Listener mListener) {
        this.mListener = mListener;
    }
    public DialogBaseHeader setTitle(String title){
        if(null==mBinding)return null;
        if(!TextUtils.isEmpty(title)){
            mBinding.tvTitle.setText(title);
        }
        return this;
    }
    public DialogBaseHeader showSimpleHeader(){
        mShowSimpleHeader=true;
        if(mBinding!=null){
            mBinding.rlHeader.removeAllViews();
            /*mBinding.rlHeader.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, uiUtils.dp2px(mContext,5)));
            mBinding.rlHeader.setBackgroundResource(R.drawable.dialog_header_background_radius_lrb);*/
        }
        return this;
    }
    public void setTitleIcon(Drawable drawable){
        if(null==mBinding)return;
        mBinding.ivTitleIcon.setBackground(drawable);
    }
}
