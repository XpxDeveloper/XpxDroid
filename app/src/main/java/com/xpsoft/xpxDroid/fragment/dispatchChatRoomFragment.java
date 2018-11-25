package com.xpsoft.xpxDroid.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.databinding.FragmentChatroomBinding;
import com.xpsoft.xpxDroid.fragment.lazybase_01.lazyFragment;
import com.xpsoft.xpxDroid.models.eventbus.xpxEvent;
import com.xpsoft.xpxDroid.tools.WidgetIdUtils;

/**
 * Created by XPSoft on 2018/2/16.
 */

public class dispatchChatRoomFragment extends lazyFragment {

    private FragmentChatroomBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mBinding= DataBindingUtil.inflate(mInflater, R.layout.fragment_chatroom,null,false);
        mContainer=mBinding.getRoot();
        mContainer.setId(WidgetIdUtils.generateViewId());
        mBinding.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getParentViewPager()!=null){
                    getParentViewPager().setCurrentItem(0);
                }
            }
        });
        return mContainer;
    }
    @Override
    public void XpxEvent(xpxEvent _xpxEvent) {

    }
}
