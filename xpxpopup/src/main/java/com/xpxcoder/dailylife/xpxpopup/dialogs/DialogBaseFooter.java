package com.xpxcoder.dailylife.xpxpopup.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.databinding.DialogBaseFooterBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPSoft on 2018/3/26.
 */

public class DialogBaseFooter extends FrameLayout {
    private Context mContext;
    private LayoutInflater mInflater;
    private Listener mListener;
    private DialogBaseFooter mSelf;

    //自定义按钮
    private List<DialogBase.customBtn> mList = new ArrayList<>();

    public DialogBaseFooter(@NonNull Context context) {
        this(context, null);
    }

    public DialogBaseFooter(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DialogBaseFooter(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mSelf=this;
        initView();
    }

    private void initView() {
        if (isInEditMode()) return;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         mInflater.inflate(R.layout.dialog_base_footer, null, false);
        addView(mBinding.getRoot());
        mBinding.btnOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.confirm();
                }
            }
        });
    }

    public DialogBaseFooter addCustomBtn(DialogBase.customBtn customBtn) {
        if (customBtn!=null&&!mList.contains(customBtn)) {
            mList.add(customBtn);
        }
        return this;
    }

    public DialogBaseFooter addCustomBtn(DialogFragment dialogFragment, String btnName, DialogBase.FootClickListener listener) {
        if (!TextUtils.isEmpty(btnName) && listener != null) {
            mList.add(new DialogBase.customBtn(dialogFragment,btnName, listener));
        }
        return this;
    }
    /**
     * 让自定义菜单生效
     *
     * @return
     */
    public void addCustomBtns(List<DialogBase.customBtn> list){
        if(list==null||list.size()==0)return;
        mBinding.llContainer.removeAllViews();
        for (int i=0;i<list.size();i++
                ) {
            final DialogBase.customBtn obj=list.get(i);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
            params.weight=1.0f;
            Button btn = new Button(mContext);
            btn.setText(obj.getBtnName());
            btn.setGravity(Gravity.CENTER);
            btn.setLayoutParams(params);
            btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(obj.getListener()!=null){
                        obj.getListener().click(obj.getDialogFragment(),v);
                    }
                }
            });
            if (list.size() == 1) {
                btn.setBackgroundResource(R.drawable.btn_press_white2highlight_green_lrb);
                btn.setTextColor(Color.BLACK);
            }else {
                btn.setTextColor(Color.BLACK);
                if(i==0){
                    btn.setBackgroundResource(R.drawable.btn_press_white2highlight_gray_radius_l);
                }else if(i==list.size()-1) {
                    btn.setBackgroundResource(R.drawable.btn_press_white2highlight_gray_radius_r);
                }else {
                    btn.setBackgroundResource(R.drawable.btn_press_white2highlight_gray_middle);
                }
            }
            mBinding.llContainer.addView(btn);
        }

        postInvalidate();
    }


    public interface Listener {
        void confirm();
    }


    public void setListener(Listener mListener) {
        this.mListener = mListener;
    }



}
