package com.xpsoft.xpxDroid.views;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.databinding.ActivityBmapBinding;
import com.xpsoft.xpxDroid.map.bmap.BMapLayerTest;
import com.xpsoft.xpxDroid.map.bmap.BMapMarkerTest;
import com.xpsoft.xpxDroid.models.eventbus.xpxEvent;
import com.xpsoft.xpxDroid.tools.MapTestDataUtils;
import com.xpsoft.xpxDroid.tools.uiUtils;
import com.xpsoft.xpxDroid.widget.AppTitleBar;

import java.util.Random;

/**
 * Created by XPSoft on 2018/4/2.
 */

public class BMapActivity extends baseFragActivity {

    private ActivityBmapBinding mBinding;
    private BaiduMap mBaiduMap;
    private UiSettings mUiSettings;
    private BMapMarkerTest obj;
    InfoWindow mInfoWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_bmap);
        mBinding.appTitleBar.setTitle("百度地图");
        mBinding.appTitleBar.setOnTitleBarClickListener(new AppTitleBar.OnTitleBarClickListener() {
            @Override
            public void onBack() {
                finish();
            }
        });
        MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(new LatLng(
                26.069154, 119.311131));//119.311131,26.069154
        mBaiduMap = mBinding.bmapView.getMap();
        mUiSettings = mBaiduMap.getUiSettings();
        mBaiduMap.animateMapStatus(status);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory
                .newMapStatus(new MapStatus.Builder().zoom(14).build()));
        mHandler.sendEmptyMessageDelayed(1000, 1000);


//        updatePosition();

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                BMapLayerTest layer = new BMapLayerTest(mContext);

                layer.setOnClickListener(new BMapLayerTest.OnClickListener() {

                    @Override
                    public void onYesClick() {
                        // TODO 自动生成的方法存根

                    }

                    @Override
                    public void onNoClick() {
                        // TODO 自动生成的方法存根
                        mBaiduMap.hideInfoWindow();
                    }
                });
                layer.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO 自动生成的方法存根

                    }
                });

                mInfoWindow = new InfoWindow(layer, marker.getPosition(), -20);

                mBaiduMap.showInfoWindow(mInfoWindow);
                return false;
            }
        });
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {

            @Override
            public boolean onMapPoiClick(MapPoi arg0) {
                // TODO 自动生成的方法存根
                return false;
            }

            @Override
            public void onMapClick(LatLng arg0) {
                // TODO 自动生成的方法存根
                mBaiduMap.hideInfoWindow();
            }
        });
    }

    private void updatePosition() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                obj.updatePosition();
                if (mInfoWindow != null) {
                    mBaiduMap.showInfoWindow(mInfoWindow);
                }

                updatePosition();
            }
        }, 1000);
    }

    @Override
    public void XpxEvent(xpxEvent _xpxEvent) {

    }


    @Override
    public void handleMsg(Message msg) {
        switch (msg.what) {
            case 1000:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Bitmap carIcon = MapTestDataUtils.getMarkerPowerBitmap(mContext, "测试");
//                        BitmapDescriptor power = BitmapDescriptorFactory.fromBitmap(carIcon);
//                        BitmapDescriptor power1 = BitmapDescriptorFactory.fromResource(R.drawable.btn_next);
                        /*obj = new BMapMarkerTest(mBaiduMap, mContext);
                        obj.addPointAndDraw(new LatLng(
                                26.069154, 119.311131), power);*/
                        for (int i = 0; i < 500; i++
                                ) {
                            Bitmap carIcon1 =MapTestDataUtils.getMarkerPowerBitmap(mContext, "测试"+i);// mContext.getResources()   .getDrawable(R.drawable.btn_next);//
//                            BitmapDescriptor power1 = BitmapDescriptorFactory.fromResource(R.drawable.btn_next);
                           BitmapDescriptor power1 = BitmapDescriptorFactory.fromBitmap(carIcon1);

//                            Bitmap carIcon1 = mContext.getResources()   .getDrawable(R.drawable.btn_next);// getMarkerPowerBitmap(mContext, "测试");

                            BMapMarkerTest obj1 = new BMapMarkerTest(mBaiduMap, mContext);
                            try {
                                int j = (int) (Math.random() * 2);
                                if (j < 1) {
                                    obj1.addPointAndDraw(new LatLng(
                                            26.077591 - 0.001 * i, 119.345626 + 0.001 * i), power1);//119.345626,26.077591
                                } else {
                                    obj1.addPointAndDraw(new LatLng(
                                            26.077591 + 0.001 * i, 119.345626 - 0.001 * i), power1);//119.345626,26.077591
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });

                break;
        }
    }
}
