package com.xpsoft.xpxDroid.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.adapter.baseFragmentAdapter;
import com.xpsoft.xpxDroid.databinding.FragmentDispatchBinding;
import com.xpsoft.xpxDroid.fragment.lazybase_01.lazyFragment;
import com.xpsoft.xpxDroid.models.eventbus.xpxEvent;
import com.xpsoft.xpxDroid.tools.WidgetIdUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPSoft on 2018/2/16.
 */

public class dispatchFragment extends lazyFragment {

    private FragmentDispatchBinding mBinding;

    private List<baseFragment> mFragList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mBinding= DataBindingUtil.inflate(mInflater, R.layout.fragment_dispatch,null,false);
        mContainer=mBinding.getRoot();
        mContainer.setId(WidgetIdUtils.generateViewId());

        mFragList.add(new lazyFragment().setRealFragClass(dispatchGroupListFragment.class).setParentViewPager(mBinding.ViewPager));
        mFragList.add(new lazyFragment().setRealFragClass(dispatchChatRoomFragment.class).setParentViewPager(mBinding.ViewPager));

        baseFragmentAdapter mFragAdapter = new baseFragmentAdapter(getChildFragmentManager(), null, mFragList);
        mBinding.ViewPager.setForbidScroll(true);
        mBinding.ViewPager.setOffscreenPageLimit(mFragList.size());//
        mBinding.ViewPager.setAdapter(mFragAdapter);//给ViewPager设置适配器
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
