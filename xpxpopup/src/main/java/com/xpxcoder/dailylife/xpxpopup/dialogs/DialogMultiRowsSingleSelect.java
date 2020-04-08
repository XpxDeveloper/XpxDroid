package com.xpxcoder.dailylife.xpxpopup.dialogs;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.databinding.DialogMultirowsSingleselectBinding;
import com.xpxcoder.xpxDroid.tools.UiUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by XPSoft on 2018/3/26.
 * 自定义复杂的dialog，有多行，包含单选操作
 */

public class DialogMultiRowsSingleSelect extends DialogBaseImpl {

    private Context mContext;
    private View mView;
    private LinearLayout llContent;
    private ScrollView svContent;
    private int mDpWidth;
    private HashMap<String, List<DialogBaseRecycleSelectModel>> mSingleSelectList = new HashMap<>();
    private HashMap<String, String> mEditList = new HashMap<>();
    /*private List<dialogMultiRowsSelect> mDialogMultiRowsSelect = new ArrayList<>();
    private List<dialogMultiRowsEdit> mDialogMultiRowsEdit = new ArrayList<>();*/
    private List<DialogRowBase> mDialogRowBaseList=new ArrayList<>();
    private DialogMultirowsSingleselectBinding mBinding;

    private Listener mListener;

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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_multirows_singleselect, container, false);
        mView = mBinding.getRoot();// inflater.inflate(R.layout.dialog_multirows_singleselect,container);
        llContent = (LinearLayout) mView.findViewById(R.id.llContent);
        svContent = (ScrollView) mView.findViewById(R.id.svContent);
        //添加单选操作 begin
        /*Iterator<Map.Entry<String, List<DialogBaseRecycleSelectModel>>> it = mSingleSelectList.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, List<DialogBaseRecycleSelectModel>> entry = it.next();
            dialogMultiRowsSelect dbs=new dialogMultiRowsSelect(mContext);
            dbs.convertData(entry.getKey(), entry.getValue());
            dbs.generateRecycle();
            llContent.addView(dbs);
        }*/
        /*Iterator<Map.Entry<String, dialogMultiRowsSelect>> it = mDialogMultiRowsSelect.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, dialogMultiRowsSelect> entry = it.next();
            entry.getValue().setKey(entry.getKey());
            entry.getValue().generateRecycle();
            llContent.addView(entry.getValue());
        }*/
        for (DialogRowBase obj : mDialogRowBaseList
                ) {
            obj.generateUI();
            llContent.addView(obj);
        }
        //添加单选操作 end


        final Window window = getDialog().getWindow();
        // 向 ViewTreeObserver 注册方法，以获取控件尺寸
        ViewTreeObserver vto = svContent.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int height = llContent.getHeight();
                int screenHeight = UiUtils.getScreen(window).height;
                Log.i(TAG, "onGlobalLayout: " + height + "，" + screenHeight);
                if (height > screenHeight) {
                    //这里的2种写法都可以
//                    svContent.getLayoutParams().height = screenHeight * 2 / 3;
                    svContent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, screenHeight * 3 / 4));

                }
                // 成功调用一次后，移除 Hook 方法，防止被反复调用
                // removeGlobalOnLayoutListener() 方法在 API 16 后不再使用
                // 使用新方法 removeOnGlobalLayoutListener() 代替

                if(Build.VERSION.SDK_INT >=16){
                    svContent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }else {
                    svContent.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });

        mBinding.dialogHeader.setTitle(mTitle).setListener(new DialogBaseHeader.Listener() {
            @Override
            public void close() {
                dismiss();
            }
        });
        mBinding.dialogFooter.setListener(new DialogBaseFooter.Listener() {
            @Override
            public void confirm() {
                boolean ok = true;
                for (DialogRowBase obj : mDialogRowBaseList
                        ) {
                    if (!obj.valid()) {
                        ok = false;
                    }
                }
                if (ok) {
                    //继续处理
                    dismiss();
                    if (mListener != null) {
                        List<DialogResultModel> list = new ArrayList<>();
                        for (DialogRowBase obj : mDialogRowBaseList
                                ) {
                            list.add(new DialogResultModel(obj.getRowKey(), obj.getRowResult()));
                        }
                        mListener.ok(list);
                    }
                }
            }
        });

        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Window window = getDialog().getWindow();
        Log.i(TAG, "onCreateView: " + llContent.getMeasuredHeight() + "，" + UiUtils.getScreen(window).height);
    }


    @Override
    public void setListener(Listener Listener) {
        this.mListener = Listener;
    }

    public void setHeaderLayout() {

    }

    public void setFooterLayout() {

    }

    public DialogMultiRowsSingleSelect setHeaderTitle(String _title) {
        mTitle = _title;
        return this;
    }

    public DialogMultiRowsSingleSelect setKeyWidth(int dpWidth) {
        mDpWidth = dpWidth;
        return this;
    }

    public DialogMultiRowsSingleSelect addSingleSelect(String _key, List<DialogBaseRecycleSelectModel> list) {
        mSingleSelectList.put(_key, list);

        //这里不能直接操作，此时mContext还没得到
        /*dialogBaseSelect dbs=new dialogBaseSelect(mContext);
        dbs.convertData(_key, list);
        llContent.addView(dbs);*/
        return this;
    }

    public DialogMultiRowsSingleSelect addSingleSelect(DialogRowSelect obj) {
        mDialogRowBaseList.add( obj);
        return this;
    }

    public DialogMultiRowsSingleSelect addEditRow(DialogRowEdit obj) {
        mDialogRowBaseList.add(obj);
        return this;
    }
    public DialogMultiRowsSingleSelect addSpinnerRow(DialogRowSpinner obj) {
        mDialogRowBaseList.add(obj);
        return this;
    }
}
