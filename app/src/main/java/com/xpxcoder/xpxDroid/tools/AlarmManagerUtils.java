package com.xpxcoder.xpxDroid.tools;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;


import com.xpxcoder.xpxDroid.receivers.AlarmClockReceiver;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by XPSoft on 2018/6/6.
 */

public class AlarmManagerUtils {
    private static AlarmManagerUtils instance=new AlarmManagerUtils();
    private Context context;
    private AlarmManager alarmManager;
    private static final int INTERVAL = 1000 * 60 * 60 * 24;// 24h

    private AlarmManagerUtils(){

    }

    public static AlarmManagerUtils getInstance(){
        return instance;
    }
    public void init(Context context){
        this.context=context;
        alarmManager = (AlarmManager)context. getSystemService(ALARM_SERVICE);
    }
    public void setNewAlarm(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Intent alarmIntent = new Intent(context, AlarmClockReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),INTERVAL , broadcast);
    }
}
