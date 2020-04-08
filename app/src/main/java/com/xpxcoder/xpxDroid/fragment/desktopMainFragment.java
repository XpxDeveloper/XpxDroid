package com.xpxcoder.xpxDroid.fragment;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.databinding.FragmentDesktopmainBinding;
import com.xpxcoder.xpxDroid.fragment.lazybase_01.lazyFragment;
import com.xpxcoder.xpxDroid.tools.WidgetIdUtils;
import com.xpxcoder.xpxDroid.views.BMapActivity;
import com.xpxcoder.xpxDroid.views.ContactsActivity;
import com.xpxcoder.xpxDroid.views.DrawerActivity;
import com.xpxcoder.xpxDroid.views.IM1_0Activity;
import com.xpxcoder.xpxDroid.views.Test3Activity;
import com.xpxcoder.xpxDroid.views.DialogActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XPSoft on 2018/2/17.
 */

public class desktopMainFragment extends lazyFragment {

    private FragmentDesktopmainBinding  mBinding;
    private List<Map<String, Object>> dataList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        mBinding =DataBindingUtil.inflate(mInflater, R.layout.fragment_desktopmain, null, false);
        mContainer = mBinding.getRoot();
        mContainer.setId(WidgetIdUtils.generateViewId());

        //图标
        int icon[] = {R.drawable.btn_border_circle_msgcount, R.drawable.btn_border_circle_msgcount, R.drawable.btn_border_circle_msgcount,
                R.drawable.btn_border_circle_msgcount, R.drawable.btn_border_circle_msgcount, R.drawable.btn_border_circle_msgcount};
        //图标下的文字
        String name[] = {"聊天", "抽屉式", "联系人列表", "MVVM测试", "dialog", "百度地图"};
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            map.put("text", name[i]);
            dataList.add(map);
        }


        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),
                dataList, R.layout.widget_iconandname, new String[]{"img", "text"}, new int[]{R.id.memberstate, R.id.membername});
        mBinding.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    IM1_0Activity.actionStartActivity(mContext, IM1_0Activity.class);
//                    overridePendingTransition(android.R.anim.slide_in_left   ,android.R.anim.slide_out_right);
                }else if(position==1){
                    DrawerActivity.actionStartActivity(mContext,DrawerActivity.class);
                }else if(position==2){
                    ContactsActivity.actionStartActivity(mContext,ContactsActivity.class);
                }else if(position==3){
                    Test3Activity.actionStartActivity(mContext,Test3Activity.class);
                }else if(position==4){
                    DialogActivity.actionStartActivity(mContext,DialogActivity.class);
                }else if(position==5){
                    BMapActivity.actionStartActivity(mContext,BMapActivity.class);
                }
            }
        });
        mBinding.gridview.setAdapter(simpleAdapter);

        return mContainer;
    }
}
