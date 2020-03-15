package com.xpsoft.xpxDroid.widget.CustomRefleshListView;

import android.content.Context;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.xpsoft.xpxDroid.inter.widget.IFrameLayout;

/**
 * Created by XPSoft on 2018/6/30.
 */

public class CustomSwipeRefreshListView extends FrameLayout implements IFrameLayout {
    public CustomSwipeRefreshListView(@NonNull Context context) {
        super(context);
    }

    public CustomSwipeRefreshListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSwipeRefreshListView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void initView() {

    }
}
