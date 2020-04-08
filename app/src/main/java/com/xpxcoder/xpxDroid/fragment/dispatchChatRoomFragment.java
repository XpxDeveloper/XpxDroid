package com.xpxcoder.xpxDroid.fragment;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.databinding.FragmentChatroomBinding;
import com.xpxcoder.xpxDroid.fragment.lazybase_01.lazyFragment;
import com.xpxcoder.xpxDroid.models.eventbus.xpxEvent;
import com.xpxcoder.xpxDroid.tools.WidgetIdUtils;

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
