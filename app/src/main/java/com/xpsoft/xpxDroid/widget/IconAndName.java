package com.xpsoft.xpxDroid.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.tools.uiUtils;


/**
 * Created by xpsoft on 2017/12/8.
 * 群组成员
 */

public class IconAndName extends FrameLayout implements View.OnClickListener {

    private View layoutView;
    private Context mContext;
    private ImageView mGroupMemberState;
    private TextView mTV_MemberName;
    private OnThisClickListener mOnThisClickListener;
    private LinearLayout mContainer;
    private int mSize;

    public IconAndName(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public IconAndName(Context context, int appType) {
        super(context);
        // Try using the context-application instead of a context-activity
        // to avoid memory leak
        mContext = context;
        initView();
    }

    public IconAndName(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }


    public void setSize(int _dp){
        mSize=_dp;
    }

    private void initView() {
        if (isInEditMode()) return;
//			anim = AnimationUtils.loadAnimation(mContext, R.anim.welcome_rotate);
        layoutView = View.inflate(mContext, R.layout.widget_iconandname, null);
        mContainer=(LinearLayout)layoutView.findViewById(R.id.container);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutView.setLayoutParams(params);
        addView(layoutView);
        mTV_MemberName = (TextView) layoutView.findViewById(R.id.membername);
        mGroupMemberState = (ImageView) layoutView.findViewById(R.id.memberstate);
        if(mSize>0){
            LayoutParams params2 = new LayoutParams(uiUtils.dp2px(mContext,mSize), uiUtils.dp2px(mContext,mSize));
            mGroupMemberState.setLayoutParams(params2);
        }
//        mTV_MemberName.setOnClickListener(this);
//        mGroupMemberState.setOnClickListener(this);
        mContainer.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
//            case R.id.membername:
//            case R.id.memberstate:
            case R.layout.widget_iconandname:
            case R.id.container:
//                lightBack(true);
                if(mOnThisClickListener!=null){
                    mOnThisClickListener.onClick();
                }
                break;
            default:
                break;

        }
    }

    public void setOnThisClickListener(OnThisClickListener aOnThisClickListener) {
        this.mOnThisClickListener = aOnThisClickListener;
    }

    public interface OnThisClickListener {
        void onClick();
    }
    //高亮背景
    public void lightBack(boolean _flag){
        if(_flag){
            layoutView.setBackgroundColor(getResources().getColor(R.color.red));
        }else{
            layoutView.setBackgroundColor(Color.WHITE);
        }
    }
}
