package com.xpsoft.xpxDroid.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.databinding.ActivityAnimateBinding;
import com.xpsoft.xpxDroid.models.eventbus.xpxEvent;
import com.xpsoft.xpxDroid.widget.AppTitleBar;

/**
 * Created by XPSoft on 2018/4/15.
 */

public class AnimateActivity extends baseFragActivity {

    private ActivityAnimateBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_animate);
        mBinding.appTitleBar.setTitle("动画").setOnTitleBarClickListener(new AppTitleBar.OnTitleBarClickListener() {
            @Override
            public void onBack() {
                finish();
            }
        });


    }
public void btnClick3(View view){
    Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_down);
    mBinding.llAnimate.startAnimation(scaleAnimation);
    mBinding.llAnimate.setVisibility(View.VISIBLE);
    scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    });
}
    public void btnClick2(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 300, 0, 300);
        AlphaAnimation appearAnimation = new AlphaAnimation(0, 1);
        appearAnimation.setDuration(500);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(appearAnimation);
        mBinding.llAnimate.startAnimation(animationSet);
        mBinding.llAnimate.setVisibility(View.VISIBLE);
    }


    public void btnClick(View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 300, 0, 300);
        AlphaAnimation appearAnimation = new AlphaAnimation(0, 1);
        appearAnimation.setDuration(500);
        appearAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mBinding.llAnimate.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        AlphaAnimation disappearAnimation = new AlphaAnimation(1, 0);
        disappearAnimation.setDuration(500);
        disappearAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mBinding.llAnimate.setVisibility(View.GONE);
            }
        });
        if (mBinding.llAnimate.getVisibility() == View.GONE) {
            mBinding.llAnimate.startAnimation(appearAnimation);
//            mBinding.llAnimate.setVisibility(View.VISIBLE);
        } else {
            mBinding.llAnimate.startAnimation(disappearAnimation);
//            mBinding.llAnimate.setVisibility(View.GONE);
        }
    }

    @Override
    public void XpxEvent(xpxEvent _xpxEvent) {

    }

    @Override
    public void handleMsg(Message msg) {

    }
}
