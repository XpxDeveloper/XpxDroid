/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.xpsoft.xpxDroid.views.bmap;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.baidu.mapapi.clusterutil.clustering.Cluster;
import com.baidu.mapapi.clusterutil.clustering.ClusterItem;
import com.baidu.mapapi.clusterutil.clustering.ClusterManager;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapLoadedCallback;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.models.eventbus.xpxEvent;
import com.xpsoft.xpxDroid.views.baseFragActivity;

import java.util.ArrayList;
import java.util.List;



/**
 * 此Demo用来说明点聚合功能
 */
public class MarkerClusterDemo extends baseFragActivity implements OnMapLoadedCallback {

    MapView mMapView;
    BaiduMap mBaiduMap;
    MapStatus ms;
    private ClusterManager<MyItem> mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmap_marker_cluster_demo);
        mMapView = (MapView) findViewById(R.id.bmapView);
        ms = new MapStatus.Builder().target(new LatLng(39.914935, 116.403119)).zoom(8).build();
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setOnMapLoadedCallback(this);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(ms));
        // 定义点聚合管理类ClusterManager
        mClusterManager = new ClusterManager<MyItem>(this, mBaiduMap);
        // 添加Marker点
        addMarkers();
        // 设置地图监听，当地图状态发生改变时，进行点聚合运算
        mBaiduMap.setOnMapStatusChangeListener(mClusterManager);
        // 设置maker点击时的响应
        mBaiduMap.setOnMarkerClickListener(mClusterManager);

        mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItem>() {
            @Override
            public boolean onClusterClick(Cluster<MyItem> cluster) {
                Toast.makeText(MarkerClusterDemo.this,
                        "有" + cluster.getSize() + "个点", Toast.LENGTH_SHORT).show();
                List<MyItem> items = (List<MyItem>) cluster.getItems();
                LatLngBounds.Builder builder2 = new LatLngBounds.Builder();
                int i=0;
                for(MyItem myItem : items){
                    builder2 = builder2.include(myItem.getPosition());
                    Log.i("map","log: i="+ i++ +" pos="+myItem.getPosition().toString());
                }

                LatLngBounds latlngBounds = builder2.build();
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngBounds(latlngBounds,mMapView.getWidth(),mMapView.getHeight());
                mBaiduMap.animateMapStatus(u);
                return false;
            }
        });
        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
            @Override
            public boolean onClusterItemClick(MyItem item) {
                Toast.makeText(MarkerClusterDemo.this,
                        "点击单个Item", Toast.LENGTH_SHORT).show();

                return false;
            }
        });

    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    /**
     * 向地图添加Marker点
     */
    public void addMarkers() {
        // 添加Marker点
        LatLng llA = new LatLng(39.963175, 116.400244);
        LatLng llB = new LatLng(39.942821, 116.369199);
        LatLng llC = new LatLng(39.939723, 116.425541);
        LatLng llD = new LatLng(39.906965, 116.401394);
        LatLng llE = new LatLng(39.956965, 116.331394);
        LatLng llF = new LatLng(39.886965, 116.441394);
        LatLng llG = new LatLng(39.996965, 116.411394);


        LatLng llG_1 = new LatLng(39.996965, 116.411394);
        LatLng llG_2 = new LatLng(39.996915, 116.411384);
        LatLng llG_3 = new LatLng(39.996925, 116.411374);
        LatLng llG_4 = new LatLng(39.996935, 116.411364);
        LatLng llG_5 = new LatLng(39.996945, 116.411354);
        LatLng llG_6 = new LatLng(39.996955, 116.411344);
        LatLng llG_7 = new LatLng(39.996985, 116.411334);
        LatLng llG_8 = new LatLng(39.996975, 116.411324);
        LatLng llG_9 = new LatLng(39.996995, 116.411314);
        LatLng llG_10 = new LatLng(39.996991, 116.411311);
        LatLng llG_11 = new LatLng(39.996992, 116.411312);
        LatLng llG_12 = new LatLng(39.996993, 116.411313);
        LatLng llG_13 = new LatLng(39.996994, 116.411314);
        LatLng llG_14 = new LatLng(39.996995, 116.411315);


        List<MyItem> items = new ArrayList<MyItem>();
        items.add(new MyItem(llA));
        items.add(new MyItem(llB));
        items.add(new MyItem(llC));
        items.add(new MyItem(llD));
        items.add(new MyItem(llE));
        items.add(new MyItem(llF));
        items.add(new MyItem(llG));

        items.add(new MyItem(llG_1));
        items.add(new MyItem(llG_2));
        items.add(new MyItem(llG_3));
        items.add(new MyItem(llG_4));
        items.add(new MyItem(llG_5));
        items.add(new MyItem(llG_6));
        items.add(new MyItem(llG_7));
        items.add(new MyItem(llG_8));
        items.add(new MyItem(llG_9));
        items.add(new MyItem(llG_10));
        items.add(new MyItem(llG_11));
        items.add(new MyItem(llG_12));
        items.add(new MyItem(llG_13));
        items.add(new MyItem(llG_14));


        mClusterManager.addItems(items);

    }

    /**
     * 每个Marker点，包含Marker点坐标以及图标
     */
    public class MyItem implements ClusterItem {
        private final LatLng mPosition;

        public MyItem(LatLng latLng) {
            mPosition = latLng;
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }

        @Override
        public BitmapDescriptor getBitmapDescriptor() {
            return BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_gcoding);
        }
    }

    @Override
    public void onMapLoaded() {
        // TODO Auto-generated method stub
        ms = new MapStatus.Builder().zoom(9).build();
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(ms));
    }

    @Override
    public void XpxEvent(xpxEvent _xpxEvent) {

    }

    @Override
    public void handleMsg(Message msg) {

    }
}
