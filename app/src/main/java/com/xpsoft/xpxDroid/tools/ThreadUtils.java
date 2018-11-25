package com.xpsoft.xpxDroid.tools;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPSoft on 2018/2/23.
 * 1、处理一些定时操作，非定时操作也可以用这个类处理
 * 2、集中处理定时操作，避免忘记销毁
 * 3、APP退出时，要记得调用clear()方法销毁
 */

public class ThreadUtils {

    private static String TAG = ThreadUtils.class.getSimpleName();
    private static Handler mHandler = new Handler();
    private static List<XpxRunnable> mTimerList = new ArrayList<>();//需要定时执行的线程列表
    private static List<XpxRunnable> mOneTimeList = new ArrayList<>();//只执行一次的线程
    private static ThreadUtils mInstance = new ThreadUtils();

    private ThreadUtils() {

    }

    public static ThreadUtils getInstance() {
        //这并不是推荐的单例模式写法
        /*if (mInstance == null) {
            synchronized (ThreadUtils.class) {
                mInstance = new ThreadUtils();
            }
        }*/
        return mInstance;
    }

    /**
     * 启动应用时，执行一次
     * 退出app时，也可以调用一次
     */
    public static void clearAll() {
        clearAllTimer();
        removeAllOneRunnable();
    }

    /**
     * 启动应用时，执行一次
     * 退出app时，也可以调用一次
     */
    public static void clearAllTimer() {
        for (XpxRunnable xpxR : mTimerList
                ) {
            xpxR.running = true;
            mHandler.removeCallbacks(xpxR.r);
            mTimerList.remove(xpxR);
        }
    }

    public static void clearTimerBy(XpxRunnable xpxR) {
        if (xpxR == null) return;
        Log.i(TAG, "clearBy: index:" + mTimerList.indexOf(xpxR) + ",size:" + mTimerList.size());
        if (mTimerList.contains(xpxR)) {
            xpxR.running = true;
            mHandler.removeCallbacks(xpxR.r);
            mTimerList.remove(xpxR);
            xpxR = null;
        }
    }

    /**
     * 清除所有只执行一次的线程
     */
    public static void removeAllOneRunnable() {
        for (XpxRunnable r : mOneTimeList
                ) {
            removeOneRunnable(r);
        }
    }

    /**
     * 清除某个只执行一次的线程
     *
     * @param xpxR
     */
    public static void removeOneRunnable(XpxRunnable xpxR) {
        if (mOneTimeList.contains(xpxR)) {
            mHandler.removeCallbacks(xpxR.r);
            mOneTimeList.remove(xpxR);
        }
    }

    public interface Listener {
        /**
         * @param _r 返回这个参数，是为了外部调用执行完业务代码时，将线程运行状态改为false
         */
        void doThings(XpxRunnable _r);
    }

    /**
     * 只执行一次
     *
     * @param _operator
     * @param _postDelay
     * @param _listener
     */
    public XpxRunnable execute(Class<?> _operator, long _postDelay, final Listener _listener) {
        Log.i(TAG, "execute: " + _operator.getName());//记录调用方
        Runnable r = new Runnable() {
            @Override
            public void run() {
                XpxRunnable xpxR = getXpxRunnableFromOneTimeList(this);
                if (_listener != null) {
                    _listener.doThings(xpxR);
                }
            }
        };
        XpxRunnable xpxRunnable = new XpxRunnable(_operator, r, _postDelay, 0);
        mHandler.postDelayed(r, _postDelay);
        return xpxRunnable;
    }

    /**
     * 定时执行
     *
     * @param _operator
     * @param _interval
     * @param _listener
     */
    public XpxRunnable beginTimer(Class<?> _operator, final long _interval, final Listener _listener) {
        Log.i(TAG, "beginTimer: " + _operator.getName());//记录调用方
        return beginTimerPostDelay(_operator, _interval, 0, _listener);
    }

    public XpxRunnable beginTimerPostDelay(Class<?> _operator, final long _interval, long _postDelay, final Listener _listener) {
        Log.i(TAG, "beginTimerPostDelay: " + _operator.getName());//记录调用方
        Runnable r = new Runnable() {
            @Override
            public void run() {

                XpxRunnable xpxR = getXpxRunnableFromTimerList(this);

                if (xpxR == null) return;

                Log.i(TAG, "beginTimerPostDelay: " + xpxR.operator.getName());//记录调用方

                if (!xpxR.pause&&!xpxR.running) {
                    xpxR.running = true;
                    if (_listener != null) {
                        _listener.doThings(xpxR);
                    }
                }

                mHandler.postDelayed(this, _interval);
            }
        };
        XpxRunnable xpxRunnable = new XpxRunnable(_operator, r, _postDelay, _interval);
        mTimerList.add(xpxRunnable);
        mHandler.postDelayed(r, _postDelay);
        return xpxRunnable;
    }

    private XpxRunnable getXpxRunnableFromTimerList(Runnable _r) {
        for (XpxRunnable xpxR : mTimerList
                ) {
            if (xpxR.r.equals(_r)) {
                return xpxR;
            }
        }
        return null;
    }

    private XpxRunnable getXpxRunnableFromOneTimeList(Runnable _r) {
        for (XpxRunnable xpxR : mOneTimeList
                ) {
            if (xpxR.r.equals(_r)) {
                return xpxR;
            }
        }
        return null;
    }

    private void setOnOrOffByXpxR(Runnable _r, boolean _running) {
        for (XpxRunnable xpxR : mTimerList
                ) {
            if (xpxR.r.equals(_r)) {
                xpxR.running = _running;
            }
        }
    }

    private void setOnOrOff(XpxRunnable _xpxR, boolean _running) {
        _xpxR.running = _running;
    }

    public static class XpxRunnable {
        public boolean running = false;//false未开始，true开始
        public Runnable r;
        public long postDelay;//初始化时延迟操作时间
        public long interval;//定时操作间隔
        public Class<?> operator;//调用方
        public boolean pause;//手动配置暂停，比如页面处于暂停状态

        public XpxRunnable(Class<?> _operator, Runnable _r, long _postDelay, long _interval) {
            this.operator = _operator;
            this.r = _r;
            this.postDelay = _postDelay;
            this.interval = _interval;
        }
    }
}
