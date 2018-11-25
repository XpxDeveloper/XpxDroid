package com.xpsoft.xpxDroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;


import com.xpsoft.xpxDroid.fragment.baseFragment;

import java.util.List;

/**
 * Created by XPSoft on 2018/2/14.
 */

public class baseFragmentAdapter  extends FragmentPagerAdapter {
    private List<String> mTitle;
    private List<? extends baseFragment> mFragments;

    public baseFragmentAdapter(FragmentManager fm, List<String> title, List<? extends baseFragment> fragments) {
        super(fm);
        // TODO Auto-generated constructor stub
        mFragments = fragments;
        mTitle = title;
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
//            mTabLayout.getTabAt(arg0).select();
        return mFragments.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mFragments.size();
    }

    //配置标题的方法
    @Override
    public CharSequence getPageTitle(int position) {
        if(null==mTitle||position<0||position>mTitle.size()-1)return "";
        return mTitle.get(position);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        if(mFragments.size()>position){
            for (int i = 0; i < mFragments.size(); i++) {
                if(i==position){
                    mFragments.get(position).setVisibleToUserAfterSwitch(true);
                }else {
                    mFragments.get(position).setVisibleToUserAfterSwitch(false);
                }
            }
        }
    }

    @Override
    public void setPrimaryItem(View container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        if(mFragments.size()>position){
            for (int i = 0; i < mFragments.size(); i++) {
                if(i==position){
                    mFragments.get(position).setVisibleToUserAfterSwitch(true);
                }else {
                    mFragments.get(position).setVisibleToUserAfterSwitch(false);
                }
            }
        }
    }
}

