package com.xpxcoder.xpxDroid.tools.dialogs;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.tools.SysUtils;
import com.xpxcoder.xpxDroid.tools.UiUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DialogGrid extends DialogBaseImpl {

    private View mView;
    private GridView gridView;
    private List<customBtn> dataList=new ArrayList<>();
    private List<Map<String,Object>> adapterList=new ArrayList<>();
    private boolean withRadius=true;//对话框与底部周围，是否有边距和圆角,默认是true
    private int GridColumns=3;//默认3个

    private LinearLayout llBottom;
    private DialogGrid mSelf;

    public DialogGrid() {
        mSelf=this;
    }

    public DialogGrid setWithRadius(boolean withRadius) {
        this.withRadius = withRadius;
        return this;
    }

    public DialogGrid setGridColumns(int gridColumns) {
        if(gridColumns<0||gridColumns>5)return this;
        GridColumns = gridColumns;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.dialog_grid, null, false);
        llBottom = (LinearLayout) mView.findViewById(R.id.llBottom);

        llBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelf.dismiss();
            }
        });
        gridView=mView.findViewById(R.id.gridView);
        init();
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

    private void init(){
        adapterList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < dataList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", dataList.get(i).getResId());
            map.put("text", dataList.get(i).getBtnName());
            adapterList.add(map);
        }


        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),
                adapterList, R.layout.widget_iconandname_white, new String[]{"img", "text"}, new int[]{R.id.memberstate, R.id.membername});
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                customBtn item=dataList.get(position);
                if(item!=null&&item.getListener()!=null){
                    item.getListener().click(mSelf,view);
                }
                dismiss();
            }
        });
        gridView.setNumColumns(GridColumns);
        gridView.setAdapter(simpleAdapter);
    }
    public DialogGrid addCustomBtn(String btnName,int resId, FootClickListener listener) {
        if (!TextUtils.isEmpty(btnName) && listener != null) {
            dataList.add(new customBtn(this,btnName,resId, listener));
        }
        return this;
    }
}
