package com.xpsoft.xpxDroid.tools;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPSoft on 2018/3/24.
 * AsynTask的操作类
 */

public class AsynTaskUtils {
    private static String TAG=AsynTaskUtils.class.getSimpleName();
    private List<AsyncTask> mList=new ArrayList<>();
    private static AsynTaskUtils instance=new AsynTaskUtils();

    private AsynTaskUtils(){

    }
    public static AsynTaskUtils getInstance(){
        //这并不是推荐的单例模式写法
       /* if(instance==null){
            synchronized (AsynTaskUtils.class){
                if(instance==null){
                    instance=new AsynTaskUtils();
                }
            }
        }*/
        return instance;
    }
    public void addAsynTasker(DefaultAsynTasker _AsynTasker){
        if(!mList.contains(_AsynTasker)){
            mList.add(_AsynTasker);
        }
    }
    public void removeTask(DefaultAsynTasker _AsynTasker){
        if(_AsynTasker==null)return;
        if(mList.contains(_AsynTasker)){
            if(!_AsynTasker.isCancelled()){
                _AsynTasker.cancel(true);
            }
            mList.remove(_AsynTasker);
            _AsynTasker=null;
        }
    }
    public void clearAllTask(){
        for (AsyncTask obj:mList
             ) {
            if(!obj.isCancelled()){
                obj.cancel(true);
            }
            mList.remove(obj);
        }
    }
    public interface AsynTaskerListener{
        void doInBackground(DefaultAsynTasker defaultAsynTasker);
        void hasDone();
    }
    public static class DefaultAsynTasker extends AsyncTask<Void,Integer,Void>{

        private Class<?> mOperator;
        private AsynTaskerListener mAsynTaskerListener;
        private int mExecuteMaxTimes;//允许执行的最大次数
        private int mCurExecuteTimes=1;//当前执行次数
        private long mInterval;//定时间隔,单位：毫秒
        public boolean running=false;//是否正在执行
        public boolean needToStop;//由外部控制是否要停止执行任务

        public AsynTaskerListener getAsynTaskerListener() {
            return mAsynTaskerListener;
        }

        public void setAsynTaskerListener(AsynTaskerListener _AsynTaskerListener) {
            this.mAsynTaskerListener = _AsynTaskerListener;
        }

        public DefaultAsynTasker(Class<?> _operator, long interval, int executeTimes) {
            super();
            mOperator=_operator;
            mExecuteMaxTimes=executeTimes;
            mInterval=interval;
        }

        @Override
        protected Void doInBackground(Void... params) {
            while (mExecuteMaxTimes==0||mCurExecuteTimes<=mExecuteMaxTimes){
                try {
                    Log.i(TAG,mOperator.getSimpleName()+ "-> doInBackground: 当前执行次数："+mCurExecuteTimes);
                    if(needToStop){
                        if(!isCancelled()){
                            cancel(true);
                        }
                        if(mAsynTaskerListener!=null){
                            mAsynTaskerListener.hasDone();
                        }
                        break;
                    }
                    if(!running){
                        running=true;
                        if(mAsynTaskerListener!=null){
                            mAsynTaskerListener.doInBackground(this);
                        }
                    }

                    mCurExecuteTimes++;
                    Thread.sleep(mInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}
