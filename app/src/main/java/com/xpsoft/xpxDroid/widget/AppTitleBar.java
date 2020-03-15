package com.xpsoft.xpxDroid.widget;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.databinding.WidgetApptitlebarBinding;

/**
 * Created by XPSoft on 2018/2/9.
 */

public class AppTitleBar extends FrameLayout  implements View.OnClickListener{

    private Context mContext;
    private WidgetApptitlebarBinding mBinding;

    private OnTitleBarClickListener mOnTitleBarClickListener;
    private OnTitleBarRightClickListener mOnTitleBarRightClickListener;

    public AppTitleBar(@NonNull Context context) {
        this(context,null);
    }

    public AppTitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AppTitleBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        initView();
    }
    private void initView(){
        if(isInEditMode())return;
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding= DataBindingUtil.inflate(mInflater,R.layout.widget_apptitlebar,null,false);
        mBinding.tvTitle.setOnClickListener(this);
        mBinding.ibtnBack.setOnClickListener(this);
        mBinding.tvRight.setOnClickListener(this);
        addView(mBinding.getRoot());
    }
    public AppTitleBar setTitle(String title){
        if(null==mBinding)return this;
        mBinding.tvTitle.setText(title);
        return this;
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.ibtnBack:
                mOnTitleBarClickListener.onBack();
                break;
            case R.id.tvRight:
                if(mOnTitleBarRightClickListener!=null){
                    mOnTitleBarRightClickListener.onClick();
                }
                break;
            default:break;

        }
    }
    public void setOnTitleBarClickListener(OnTitleBarClickListener _OnTitleClickListener)
    {
        this.mOnTitleBarClickListener=_OnTitleClickListener;
    }
    public interface OnTitleBarClickListener
    {
        void onBack();
    }
    public interface  OnTitleBarRightClickListener{
        void onClick();
    }
    public void setOnTitleBarRightClickListener(String a_btnName, OnTitleBarRightClickListener _OnRightClickListener)
    {
        if(mBinding.tvRight.getVisibility()!=VISIBLE){
            mBinding.tvRight.setVisibility(VISIBLE);
        }
        mBinding.tvRight.setText(a_btnName);
        this.mOnTitleBarRightClickListener=_OnRightClickListener;
    }
}
