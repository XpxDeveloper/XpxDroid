package com.xpsoft.xpxDroid.tools;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.StringDef;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;

/**
 * Created by XPSoft on 2018/6/28.
 */

public class ShareUtils {
    public static void showShare(Context context) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("test");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(context);
    }
    public static final String Platform_SinaWeibo = SinaWeibo.NAME;

    @StringDef({""})
    @Retention(RetentionPolicy.CLASS)
    public @interface PlatformType{

    }
    public static void shareLogin(String name) {
        Platform weibo = ShareSDK.getPlatform(name);
        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        weibo.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                arg2.printStackTrace();
                Log.i("ShareUtils", "onError: ");
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                String s= arg0.getDb().exportData();
                String accessToken = arg0.getDb().getToken(); // 获取授权token
                String openId = arg0.getDb().getUserId(); // 获取用户在此平台的ID
                String nickname = arg0.getDb().getUserName(); // 获取用户昵称
                String unionid = arg0.getDb().get("unionid");
                // 接下来执行您要的操作
                Log.i("ShareUtils", "onComplete: "+s);
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub
                Log.i("ShareUtils", "onCancel: ");
            }
        });
        //authorize与showUser单独调用一个即可
        weibo.authorize();//要功能不要数据，在监听oncomplete中不会返回用户数据

        //想要移除授权状态，在想移除的地方执行下面的方法即可
        //weibo.removeAccount(true);
    }
}
