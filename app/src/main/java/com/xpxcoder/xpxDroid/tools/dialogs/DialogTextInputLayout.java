package com.xpxcoder.xpxDroid.tools.dialogs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;

import com.google.android.material.textfield.TextInputLayout;

/**
 * Created by XPSoft on 2018/3/27.
 */

public class DialogTextInputLayout extends TextInputLayout {
    public DialogTextInputLayout(Context context) {
        super(context);
    }

    public DialogTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DialogTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        recoverEditTextBackGround();
    }

    @Override
    public void setError(@Nullable CharSequence error) {
        super.setError(error);
        recoverEditTextBackGround();
    }

    /**
     * 将背景颜色重置
     */
    private void recoverEditTextBackGround(){
        if (getEditText() == null) {
            return;
        }
        Drawable editTextBackground = getEditText().getBackground();
        if (editTextBackground == null) {
            return;
        }
        if (androidx.appcompat.widget.DrawableUtils.canSafelyMutateDrawable(editTextBackground)) {
            editTextBackground = editTextBackground.mutate();
        }
        DrawableCompat.clearColorFilter(editTextBackground);
    }
}