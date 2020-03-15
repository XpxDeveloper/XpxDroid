package com.xpxcoder.dailylife.xpxpopup.dialogs;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.tools.SysUtils;
import com.xpsoft.xpxDroid.tools.UiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPSoft on 2018/3/27.
 * 从底部弹出的对话框，用于选择操作
 */

public class DialogFromBottom extends DialogBaseImpl {

    private View mView;
    private ListView mListView;
    private LinearLayout llBottom;
    //自定义按钮
    private List<customBtn> mList = new ArrayList<>();
    private boolean withRadius=true;//对话框与底部周围，是否有边距和圆角,默认是true

    public void setWithRadius(boolean withRadius) {
        this.withRadius = withRadius;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if(withRadius){
            mView = inflater.inflate(R.layout.dialog_base_frombottom_withradius, container, false);
        }else {
            mView = inflater.inflate(R.layout.dialog_base_frombottom_full, container, false);
        }

        mListView = (ListView) mView.findViewById(R.id.listview);
        llBottom = (LinearLayout) mView.findViewById(R.id.llBottom);

        llBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelf.dismiss();
            }
        });

        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public Object getItem(int position) {
                return mList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = new TextView(mContext);
                tv.setText(mList.get(position).getBtnName());
                tv.setHeight(UiUtils.dp2px(mContext, 50));
                tv.setGravity(Gravity.CENTER);
                return tv;
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                customBtn item=mList.get(position);
                if(item!=null&&item.getListener()!=null){
                    item.getListener().click(mSelf,view);
                }
                dismiss();
            }
        });
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        if(withRadius){
            if(SysUtils.DeviceHasNavigationBar(mContext)&&SysUtils.isNavBarVisible(mContext,((Activity)mContext).getWindow())){
                lp.y = UiUtils.dp2px(mContext, 15)+SysUtils.getVirtualBarHeight(mContext);//与屏幕底部边距
            }else {
                lp.y = UiUtils.dp2px(mContext, 15);//与屏幕底部边距
            }
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
            WindowManager wm = window.getWindowManager();
            int width = wm.getDefaultDisplay().getWidth() - UiUtils.dp2px(mContext, 30);
            window.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);//这2行,和上面的一样,注意顺序就行;

            window.setAttributes(lp);
        }else {
            lp.y = UiUtils.dp2px(mContext, 0);//与屏幕底部边距
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
            WindowManager wm = window.getWindowManager();
            int width = wm.getDefaultDisplay().getWidth() - UiUtils.dp2px(mContext, 0);
            window.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);//这2行,和上面的一样,注意顺序就行;

            window.setAttributes(lp);
        }

    }
    public DialogFromBottom addCustomBtn(String btnName, FootClickListener listener) {
        if (!TextUtils.isEmpty(btnName) && listener != null) {
            mList.add(new customBtn(this,btnName, listener));
        }
        return this;
    }
}
