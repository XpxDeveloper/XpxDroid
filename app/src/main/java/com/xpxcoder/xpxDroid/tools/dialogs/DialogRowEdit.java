package com.xpxcoder.xpxDroid.tools.dialogs;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.databinding.DialogRowEditBinding;
import com.xpxcoder.xpxDroid.tools.EditTextWatcher;
import com.xpxcoder.xpxDroid.tools.UiUtils;

/**
 * Created by XPSoft on 2018/3/27.
 * 对话框，添加一行编辑项
 */

public class DialogRowEdit extends DialogRowBase {

    private DialogRowEditBinding mBinding;

    private String mKeyTitle = "";
    private String mDefaultValue = "";
    private String mErrorHint = "";
    private String mHint = "";
    private int mWordsMaxLimit;
    private int mWordsMinLimit;

    private TextView tvKey;

    public DialogRowEdit(@NonNull Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public DialogRowEdit(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public DialogRowEdit(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    protected void initView() {
        if (isInEditMode()) return;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(mInflater, R.layout.dialog_row_edit, null, false);
        addView(mBinding.getRoot());
    }

    public DialogRowEdit setTvKeyWidth(int dpWidth) {
        if (mBinding != null) {
            mBinding.tvKey.getLayoutParams().width = UiUtils.dp2px(mContext, dpWidth);
            //不能直接通过setWidth()方法设置，这样是无效的，具体的要看源码实现过程
        }

        return this;
    }

    /**
     * 显示错误提示，并获取焦点
     *
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    /**
     * 设置只能输入数字
     *
     * @return
     */
    public DialogRowEdit setInputNumber() {
        if (mBinding != null) {
            mBinding.etValue.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        return this;
    }

    public DialogRowEdit setHint(String _dialogRowKey, String _keyTitle, String _defaultValue, String _hint, String _errorHint, int _wordsMaxLimit, int _wordsMinLimit) {
        if (mBinding != null) {
            mKeyTitle = _keyTitle;
            mBinding.tvKey.setText(_keyTitle);
            mDialogRowKey = _dialogRowKey;
            mDefaultValue = _defaultValue;
            mHint = _hint;
            mErrorHint = _errorHint;
            mWordsMaxLimit = _wordsMaxLimit;
            mWordsMinLimit = _wordsMinLimit;
            mBinding.tilAccount.setHint(_hint);

            mBinding.tilAccount.getEditText().setText(mDefaultValue);

            if (mWordsMaxLimit > 0) {
                mBinding.tilAccount.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(mWordsMaxLimit)});
                mBinding.tilAccount.getEditText().addTextChangedListener(new EditTextWatcher(mBinding.tilAccount.getEditText(), new EditTextWatcher.OnCallBackListener() {
                    @Override
                    public void afterTextChanged(String _str) {
                        valid();
                    }
                }));
            }
        }
        return this;
    }


    @Override
    public String getRowResult() {
        return mBinding.etValue.getText().toString();
    }

    @Override
    public String getRowKey() {
        return mDialogRowKey;
    }

    @Override
    public void generateUI() {

    }

    @Override
    public boolean valid() {
        String value = mBinding.etValue.getText().toString();
        if (TextUtils.isEmpty(value)) {
//            mBinding.tilAccount.setHint(mErrorHint);
            mBinding.tilAccount.setErrorEnabled(true);
            mBinding.tilAccount.setError(mErrorHint);
//            mBinding.tilAccount.getEditText().getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.DST_ATOP);
            return false;
        }
        if (mWordsMaxLimit > 0 && value.length() >= mWordsMaxLimit) {
            mBinding.tilAccount.setErrorEnabled(true);
            mBinding.tilAccount.setError(String.format("字符不能超过%d个", mWordsMaxLimit));
//            mBinding.tilAccount.getEditText().getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.DST_ATOP);
//            mBinding.tilAccount.getEditText().setBackgroundColor(Color.WHITE);
            return true;//设置了最大值，文本框已经设置了字符限制，不会超过。所以可以返回true
        }
        if (mWordsMinLimit > 0 && value.length() < mWordsMinLimit) {
            mBinding.tilAccount.setErrorEnabled(true);
            mBinding.tilAccount.setError(String.format("字符不能少于%d个", mWordsMinLimit));
//            mBinding.tilAccount.getEditText().getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.DST_ATOP);
//            mBinding.tilAccount.getEditText().setBackgroundColor(Color.WHITE);
            return false;
        }
        mBinding.tilAccount.setError("");
        mBinding.tilAccount.setErrorEnabled(false);
        return true;
    }
}
