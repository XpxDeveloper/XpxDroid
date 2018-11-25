package com.xpsoft.xpxDroid.tools.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.tools.ShareUtils;
import com.xpsoft.xpxDroid.tools.sysUtils;
import com.xpsoft.xpxDroid.views.AnimateActivity;
import com.xpsoft.xpxDroid.views.AppBarLayoutActivity;
import com.xpsoft.xpxDroid.views.CustomViewActivity;
import com.xpsoft.xpxDroid.views.IM2_0Activity;
import com.xpsoft.xpxDroid.views.IjkplayerActivity;
import com.xpsoft.xpxDroid.views.MVPActivity;
import com.xpsoft.xpxDroid.views.PermissionTestActivity;
import com.xpsoft.xpxDroid.views.RetrofitActivity;
import com.xpsoft.xpxDroid.views.TBSActivity;
import com.xpsoft.xpxDroid.views.bmap.MarkerClusterDemo;
import com.xpsoft.xpxDroid.views.gdmap.cluster.ClusterDemoActivity;
import com.xpsoft.xpxDroid.views.gdmap.gdmapActivity;
import com.xpsoft.xpxDroid.views.searchviews.SearchActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.sharesdk.tencent.qq.QQ;

public class DialogGrid extends DialogBaseImpl {

    private View mView;
    private GridView gridView;
    private List<Map<String, Object>> dataList=new ArrayList<>();

    private Button BtnCancel;
    private DialogGrid mSelf;

    public DialogGrid() {
        mSelf=this;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.dialog_grid, null, false);
        BtnCancel = (Button) mView.findViewById(R.id.btnCancel);
        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        gridView=mView.findViewById(R.id.gridView);
        init();
        return mView;
    }
    private void init(){
        //图标
        int icon[] = {R.drawable.btn_border_circle_msgcount, R.drawable.btn_border_circle_msgcount};
        //图标下的文字
        String name[] = {"视频", "retrofit"};
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            map.put("text", name[i]);
            dataList.add(map);
        }


        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),
                dataList, R.layout.widget_iconandname, new String[]{"img", "text"}, new int[]{R.id.memberstate, R.id.membername});
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
//                    overridePendingTransition(android.R.anim.slide_in_left   ,android.R.anim.slide_out_right);
                }else if(position==1){
                }else if(position==2){
                }else if(position==3){
                }
            }
        });
        gridView.setAdapter(simpleAdapter);
    }
}
