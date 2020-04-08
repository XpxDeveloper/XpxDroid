package com.xpxcoder.xpxDroid.tools;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPSoft on 2018/6/4.
 * 创建Activity后，添加到这里进行管理
 */

public class ActivityUtils {
    private List<Activity> mList=new ArrayList<>();
    private static ActivityUtils instance=new ActivityUtils();

    private ActivityUtils(){

    }

    public static ActivityUtils getInstance(){
        return instance;
    }
    public void addActivity(Activity activity){
        if(!mList.contains(activity)){
            mList.add(activity);
        }
    }
    public void finishActivity(Activity activity){
        if(mList.contains(activity)){
            mList.remove(activity);
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
    public void removeActivityByFullName(Class<?> _class){
        for (Activity item:mList
                ) {
            if(item.getClass().getName().equals(_class.getName())){
                finishActivity(item);
            }
            break;
        }
    }
    public void removeActivityBySimpleName(Class<?> _class){
        for (Activity item:mList
                ) {
            if(item.getClass().getSimpleName().equals(_class.getSimpleName())){
                finishActivity(item);
            }
            break;
        }
    }
    public void removeAllActivity(){
        for (Activity item:mList
             ) {
            finishActivity(item);
        }
    }
}
