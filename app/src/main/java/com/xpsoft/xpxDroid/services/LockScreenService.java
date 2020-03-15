package com.xpsoft.xpxDroid.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import android.util.Log;

public class LockScreenService extends Service {

    private  static final  String TAG= "LockScreenService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        sendMessage();

    }
    /**
     * 模仿推送，发消息
     */
    private void sendMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "sendMessage: ");
                Intent intent = new Intent();
                intent.setAction("com.xpsoft.xpxDroid.receivers.LockScreenMsgReceiver");
                sendBroadcast(intent); //发送广播
            }
        }).start();
    }
}
