package com.xpsoft.xpxDroid.views;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Message;
import androidx.annotation.Nullable;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.databinding.ActivityIjkplayerBinding;
import com.xpsoft.xpxDroid.models.eventbus.xpxEvent;
import com.xpsoft.xpxDroid.widget.AppTitleBar;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by XPSoft on 2018/4/8.
 */

public class IjkplayerActivity extends baseFragActivity {

    private ActivityIjkplayerBinding mBinding;
    private String s1="http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4";

    @Override
    public void handleMsg(Message msg) {

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_ijkplayer);
        mBinding.appTitleBar.setTitle("ijkplayer");
        mBinding.appTitleBar.setOnTitleBarClickListener(new AppTitleBar.OnTitleBarClickListener() {
            @Override
            public void onBack() {
                finish();
            }
        });
        mBinding.jiecaoPlayer.setUp(s1,JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"视频标题");
    }

    @Override
    public void XpxEvent(xpxEvent _xpxEvent) {

    }
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
