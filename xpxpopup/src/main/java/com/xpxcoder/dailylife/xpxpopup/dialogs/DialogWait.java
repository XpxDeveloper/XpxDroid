package com.xpxcoder.dailylife.xpxpopup.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.tools.SysUtils;

/**
 * Created by XPSoft on 2018/3/30.
 * 等待框
 */

public class DialogWait extends DialogBaseImpl {

    private View mView;
    private Button BtnCancel;
    private TextView tvMsg;
    private int mProgess=0;//进度
    private String mTitle="查询中,请稍侯...";
    private boolean showTime;
    private WaitListener mListener;
    private DialogWait mSelf;

    public DialogWait() {
        mSelf=this;
    }

    /**
     * 开启时间显示
     * @param show
     */
    public void  showTime(boolean show) {
        this.showTime=show;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.dialog_wait, null, false);
        BtnCancel = (Button) mView.findViewById(R.id.btnCancel);
        tvMsg=(TextView)mView.findViewById(R.id.tvMsg);
        if(showTime){
            setProgess(0);
        }
        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.execute(mSelf);
                }
                dismiss();
            }
        });


        return mView;
    }
    public void setProgess(int progess){
        mProgess=progess;
        tvMsg.setText(mTitle+"("+ SysUtils.convertSecond2Desc(progess)+")");
    }
    public void setTitle(String title){
        mTitle=title;
        tvMsg.setText(mTitle+"("+ SysUtils.convertSecond2Desc(mProgess)+")");
    }
    public interface WaitListener{
        void execute(DialogWait _dialogWait);
    }

    public void setWaitListener(WaitListener Listener) {
        mListener=Listener;
    }
}
