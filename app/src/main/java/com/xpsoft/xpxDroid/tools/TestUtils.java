package com.xpsoft.xpxDroid.tools;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * Created by XPSoft on 2018/8/29.
 */

public class TestUtils {
    public void test(){
        try {
            Class<?> clz=Class.forName("");
            Field declaredField = clz.getDeclaredField("");
            clz.getField("");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {

        }
    }
    public void test2(Context context){
        Toast.makeText(context,"",Toast.LENGTH_SHORT);
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                return null;
            }
        };
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
