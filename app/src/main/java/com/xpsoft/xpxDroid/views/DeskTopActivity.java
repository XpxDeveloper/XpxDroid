package com.xpsoft.xpxDroid.views;

import android.databinding.DataBindingUtil;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.adapter.baseFragmentAdapter;
import com.xpsoft.xpxDroid.databinding.ActivityDesktopBinding;
import com.xpsoft.xpxDroid.fragment.baseFragment;
import com.xpsoft.xpxDroid.fragment.desktopMainFragment;
import com.xpsoft.xpxDroid.fragment.desktopSecondFragment;
import com.xpsoft.xpxDroid.fragment.lazybase_01.lazyFragment;
import com.xpsoft.xpxDroid.models.eventbus.xpxEvent;
import com.xpsoft.xpxDroid.tools.AsynTaskUtils;
import com.xpsoft.xpxDroid.widget.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by XPSoft on 2018/2/17.
 */

public class DeskTopActivity extends baseFragActivity {


    private ActivityDesktopBinding mBinding;
    private List<Map<String, Object>> dataList;
    private List<baseFragment> mFragList = new ArrayList<>();

    private AudioRecord record;
    private AudioRecord record2;

    @Override
    public void XpxEvent(xpxEvent _xpxEvent) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_desktop);
        mFragList.add(new lazyFragment().setRealFragClass(desktopMainFragment.class).setParentViewPager(mBinding.ViewPager));
        mFragList.add(new lazyFragment().setRealFragClass(desktopSecondFragment.class).setParentViewPager(mBinding.ViewPager));
        baseFragmentAdapter mFragAdapter = new baseFragmentAdapter(getSupportFragmentManager(), null, mFragList);
        mBinding.ViewPager.setForbidScroll(false);
        mBinding.ViewPager.setOffscreenPageLimit(mFragList.size());//目前是5个菜单，设置为5可以避免资源销毁问题
        mBinding.ViewPager.setAdapter(mFragAdapter);//给ViewPager设置适配器
        mBinding.ViewPager.setOnPageChangeListener(new ViewPagerIndicator(this, mBinding.ll, 2));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AsynTaskUtils.getInstance().clearAllTask();
    }
    private  void useless(){
        try{
            int frequence = 8000; //录制频率，单位hz.这里的值注意了，写的不好，可能实例化AudioRecord对象的时候，会出错。我开始写成11025就不行。这取决于硬件设备
            int channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO;
            int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;

            //根据定义好的几个配置，来获取合适的缓冲大小
            int bufferSize = AudioRecord.getMinBufferSize(frequence, channelConfig, audioEncoding);

            /*int bufferSize = AudioRecord.getMinBufferSize(frequence,
                    AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT) * 16;*/

            /*AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                    16000, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);*/


            //实例化AudioRecord
            record = new AudioRecord(MediaRecorder.AudioSource.MIC, frequence, channelConfig, audioEncoding, bufferSize);
            record.startRecording();
//             record2 = new AudioRecord(MediaRecorder.AudioSource.MIC, frequence, channelConfig, audioEncoding, bufferSize);

        /*AudioRecord audioRecord2 = new AudioRecord(MediaRecorder.AudioSource.MIC,
                16000, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void handleMsg(Message msg) {

    }
}
