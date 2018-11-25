package com.xpsoft.xpxDroid.tools.dialogs;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by XPSoft on 2018/3/29.
 */

public abstract class DialogRowBase extends FrameLayout {
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected View mView;
    protected String mDialogRowKey="";//当前行，存储在本地文件中时的键值对的key


    public DialogRowBase(@NonNull Context context) {
        this(context,null);
    }

    public DialogRowBase(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DialogRowBase(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
    }
    protected abstract void initView();
    public abstract String getRowResult();

    public abstract String getRowKey();
    public abstract void generateUI();
    public abstract boolean valid();
}
