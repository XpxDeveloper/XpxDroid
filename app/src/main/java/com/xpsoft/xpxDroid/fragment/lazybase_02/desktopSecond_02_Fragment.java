package com.xpsoft.xpxDroid.fragment.lazybase_02;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.databinding.FragmentFirstBinding;
import com.xpsoft.xpxDroid.models.eventbus.xpxEvent;
import com.xpsoft.xpxDroid.views.IM2_0Activity;
import com.xpsoft.xpxDroid.views.CustomViewActivity;
import com.xpsoft.xpxDroid.views.IjkplayerActivity;
import com.xpsoft.xpxDroid.views.RetrofitActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XPSoft on 2018/2/11.
 */

public class desktopSecond_02_Fragment extends lazyFragment {

    private FragmentFirstBinding mBinding;
    private List<Map<String, Object>> dataList = new ArrayList<>();
    private GridView gridView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public lazyFragment setLayoutId(int resId) {
        mResId = resId;
        return this;
    }

    @Override
    public void initRealView() {
        super.initRealView();
        //图标
        int icon[] = {R.drawable.btn_border_circle_msgcount, R.drawable.btn_border_circle_msgcount, R.drawable.btn_border_circle_msgcount,
                R.drawable.btn_border_circle_msgcount, R.drawable.btn_border_circle_msgcount, R.drawable.btn_border_circle_msgcount};
        //图标下的文字
        String name[] = {"视频", "retrofit", "自定义控件", "懒加载2.0", "待开发", "待开发"};
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            map.put("text", name[i]);
            dataList.add(map);
        }
        gridView = (GridView) mRootView.findViewById(R.id.gridview);

        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),
                dataList, R.layout.widget_iconandname, new String[]{"img", "text"}, new int[]{R.id.memberstate, R.id.membername});
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    IjkplayerActivity.actionStartActivity(mContext, IjkplayerActivity.class);
//                    overridePendingTransition(android.R.anim.slide_in_left   ,android.R.anim.slide_out_right);
                } else if (position == 1) {
                    RetrofitActivity.actionStartActivity(mContext, RetrofitActivity.class);
                } else if (position == 2) {
                    CustomViewActivity.actionStartActivity(mContext, CustomViewActivity.class);
                } else if (position == 3) {
                    IM2_0Activity.actionStartActivity(mContext, IM2_0Activity.class);
                }
            }
        });
        gridView.setAdapter(simpleAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void XpxEvent(xpxEvent _xpxEvent) {

    }
}
