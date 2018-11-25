package com.xpsoft.xpxDroid.fragment.lazybase_02;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.xpsoft.xpxDroid.widget.ViewPagerCustomScroll;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.adapter.baseFragmentAdapter;
import com.xpsoft.xpxDroid.databinding.FragmentDispatchBinding;
import com.xpsoft.xpxDroid.fragment.baseFragment;
import com.xpsoft.xpxDroid.fragment.dispatchChatRoomFragment;
import com.xpsoft.xpxDroid.fragment.dispatchGroupListFragment;
import com.xpsoft.xpxDroid.fragment.lazybase_02.lazyFragment;
import com.xpsoft.xpxDroid.models.eventbus.xpxEvent;
import com.xpsoft.xpxDroid.tools.WidgetIdUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPSoft on 2018/2/16.
 */

public class dispatch_02_Fragment extends lazyFragment {

    private FragmentDispatchBinding mBinding;
    private com.xpsoft.xpxDroid.widget.ViewPagerCustomScroll viewPager;
    private baseFragmentAdapter mFragAdapter;

    private List<baseFragment> mFragList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public lazyFragment setLayoutId(int resId) {
        mResId=resId;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void initRealView() {
        super.initRealView();
        mFragList.clear();
        viewPager= (com.xpsoft.xpxDroid.widget.ViewPagerCustomScroll) mRootView.findViewById(R.id.ViewPager);
        mFragList.add(new dispatchGroupList_02_Fragment().setLayoutId(R.layout.fragment_grouplist).setParentViewPager(viewPager));
//        mFragList.add(new lazyFragment().setRealFragClass(dispatchChatRoomFragment.class).setParentViewPager(mBinding.ViewPager));

        if(mFragAdapter==null){
            mFragAdapter = new baseFragmentAdapter(getChildFragmentManager(), null, mFragList);
        }
        viewPager.setForbidScroll(true);
        viewPager.setOffscreenPageLimit(mFragList.size());//
        viewPager.setAdapter(mFragAdapter);//给ViewPager设置适配器
        mFragAdapter.notifyDataSetChanged();
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
