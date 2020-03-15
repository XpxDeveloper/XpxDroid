package com.xpxcoder.dailylife.xpxpopup.dialogs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.tools.UiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPSoft on 2018/3/30.
 * 对话框，添加一行下拉框选项
 */

public class DialogRowSpinner extends DialogRowBase {

    private Spinner mSpinner;
    private static String[] mArrayString = null;
    private ArrayAdapter<String> mArrayAdapter;
    private List<String> mList=new ArrayList<>();

    public DialogRowSpinner(@NonNull Context context) {
        this(context,null);
    }

    public DialogRowSpinner(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DialogRowSpinner(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    protected void initView() {
        if (isInEditMode()) return;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.dialog_row_spinner, null, false);
        mSpinner=(Spinner)mView.findViewById(R.id.spinner);

        mArrayAdapter=new ArrayAdapter<String>(mContext,R.layout.dialog_spinner,mList){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                if (convertView == null){
//                    设置spinner展开的Item布局
                    convertView = mInflater.inflate(R.layout.dialog_spinner_item_style1, parent, false);
                }
                TextView spinnerText=(TextView)convertView.findViewById(R.id.tvValue);
                spinnerText.setText(getItem(position));
                return convertView;
            }
        };
        mSpinner.setAdapter(mArrayAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                UiUtils.showToastShort(mContext,"你点击的是:"+mList.get(pos));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        addView(mView);
    }

    public void setList(List<String> mList) {
        this.mList.addAll(mList);
        mArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public String getRowResult() {
        return null;
    }

    @Override
    public String getRowKey() {
        return null;
    }

    @Override
    public void generateUI() {

    }

    @Override
    public boolean valid() {
        return false;
    }
}
