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
import com.xpxcoder.xpxDroid.databinding.FragmentFirstBinding;
import com.xpxcoder.xpxDroid.fragment.lazybase_01.lazyFragment;
import com.xpxcoder.xpxDroid.models.eventbus.xpxEvent;
import com.xpxcoder.xpxDroid.tools.ShareUtils;
import com.xpxcoder.xpxDroid.tools.WidgetIdUtils;
import com.xpxcoder.xpxDroid.tools.SysUtils;
import com.xpxcoder.xpxDroid.views.AppBarLayoutActivity;
import com.xpxcoder.xpxDroid.views.IM2_0Activity;
import com.xpxcoder.xpxDroid.views.AnimateActivity;
import com.xpxcoder.xpxDroid.views.CustomViewActivity;
import com.xpxcoder.xpxDroid.views.IjkplayerActivity;
import com.xpxcoder.xpxDroid.views.MVPActivity;
import com.xpxcoder.xpxDroid.views.PermissionTestActivity;
import com.xpxcoder.xpxDroid.views.RetrofitActivity;
import com.xpxcoder.xpxDroid.views.TBSActivity;
import com.xpxcoder.xpxDroid.views.bmap.MarkerClusterDemo;
import com.xpxcoder.xpxDroid.views.gdmap.cluster.ClusterDemoActivity;
import com.xpxcoder.xpxDroid.views.gdmap.gdmapActivity;
import com.xpxcoder.xpxDroid.views.searchviews.SearchActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by XPSoft on 2018/2/11.
 */

public class desktopSecondFragment extends lazyFragment {

    private FragmentFirstBinding mBinding;
    private List<Map<String, Object>> dataList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        mBinding=DataBindingUtil.inflate(mInflater,R.layout.fragment_first,null,false);
        mContainer=mBinding.getRoot();
        mContainer.setId(WidgetIdUtils.generateViewId());

        //图标
        int icon[] = {R.drawable.btn_border_circle_msgcount, R.drawable.btn_border_circle_msgcount,
                R.drawable.btn_border_circle_msgcount, R.drawable.btn_border_circle_msgcount,
                R.drawable.btn_border_circle_msgcount, R.drawable.btn_border_circle_msgcount,
                R.drawable.btn_border_circle_msgcount,R.drawable.btn_border_circle_msgcount,
                R.drawable.btn_border_circle_msgcount,R.drawable.btn_border_circle_msgcount,
                R.drawable.btn_border_circle_msgcount,R.drawable.btn_border_circle_msgcount,
                R.drawable.btn_border_circle_msgcount,R.drawable.btn_border_circle_msgcount,
                R.drawable.btn_border_circle_msgcount,R.drawable.btn_border_circle_msgcount};
        //图标下的文字
        String name[] = {"视频", "retrofit", "自定义控件", "懒加载2.0",
                "动画", "数据库操作","MVP","第三方分享","第三方登录",
                "百度聚合","标题滑动","搜索","高德地图","高德聚合","权限测试","腾讯TBS"};
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
                    IjkplayerActivity.actionStartActivity(mContext, IjkplayerActivity.class);
//                    overridePendingTransition(android.R.anim.slide_in_left   ,android.R.anim.slide_out_right);
                }else if(position==1){
                    RetrofitActivity.actionStartActivity(mContext,RetrofitActivity.class);
                }else if(position==2){
                    CustomViewActivity.actionStartActivity(mContext,CustomViewActivity.class);
                }else if(position==3){
                    IM2_0Activity.actionStartActivity(mContext,IM2_0Activity.class);
                }else if(position==4){
                    AnimateActivity.actionStartActivity(mContext,AnimateActivity.class);
                }else if(position==6){
                    MVPActivity.actionStartActivity(mContext,MVPActivity.class);
                }else if(position==7){
                    ShareUtils.showShare(mContext);
                }else if(position==8){
//                    ShareUtils.shareLogin(SinaWeibo.NAME);
//                    ShareUtils.shareLogin(Wechat.NAME);
//                    ShareUtils.shareLogin(QQ.NAME);
                }else if(position==9){
                    MarkerClusterDemo.actionStartActivity(mContext,MarkerClusterDemo.class);
                }else if(position==10){
                    AppBarLayoutActivity.actionStartActivity(mContext,AppBarLayoutActivity.class);
                }else if(position==11){
                    SearchActivity.actionStartActivity(mContext,SearchActivity.class);
                }else if(position==12){
                    SysUtils.actionStartActivity(mContext,gdmapActivity.class);
                }else if(position==13){
                    ClusterDemoActivity.actionStartActivity(mContext,ClusterDemoActivity.class);
                }else if(position==14){
                    PermissionTestActivity.actionStartActivity(mContext,PermissionTestActivity.class);
                }else if(position==15){
                    TBSActivity.actionStartActivity(mContext,TBSActivity.class);
                }
            }
        });
        mBinding.gridview.setAdapter(simpleAdapter);
        return mContainer;
    }

    @Override
    public void onStart() {
        super.onStart();
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
