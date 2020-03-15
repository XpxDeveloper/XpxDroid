package com.xpsoft.xpxDroid.views.gdmap;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;

import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;


import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.map.gdmap.GDMapMarkerTest;
import com.xpsoft.xpxDroid.models.eventbus.xpxEvent;
import com.xpsoft.xpxDroid.tools.MapTestDataUtils;
import com.xpsoft.xpxDroid.views.baseFragActivity;
import com.xpsoft.xpxDroid.widget.AppTitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPSoft on 2018/8/14.
 */

public class gdmapActivity extends baseFragActivity implements AMap.InfoWindowAdapter, AMap.OnInfoWindowClickListener{

    private AppTitleBar appTitleBar;

    private MapView mapView;
    private AMap aMap;
    private GDMapMarkerTest obj;
    private List<Marker> markerList = new ArrayList<>();
    private UiSettings uiSettings;

    private Marker mCurMarker;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gdmap);
        appTitleBar = (AppTitleBar) findViewById(R.id.appTitleBar);
        appTitleBar.setOnTitleBarClickListener(new AppTitleBar.OnTitleBarClickListener() {
            @Override
            public void onBack() {
                finish();
            }
        });
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        aMap = mapView.getMap();

        uiSettings=aMap.getUiSettings();
        uiSettings.setRotateGesturesEnabled(false);
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 设置卫星地图模式，aMap是地图控制器对象。
        LatLng latLng = new LatLng(
                26.069154, 119.311131);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(
                latLng, 18, 30, 30));
        aMap.moveCamera(cameraUpdate);

        aMap.setInfoWindowAdapter(this);
        aMap.setOnInfoWindowClickListener(this);

        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                mCurMarker=marker;
                marker.showInfoWindow();
                return false;
            }
        });
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.e(TAG, "onMapClick: " );
                /*if(mCurMarker!=null&& mCurMarker.isInfoWindowShown()){
                    mCurMarker.hideInfoWindow();
                }*/
            }
        });

        mHandler.sendEmptyMessageDelayed(1000, 1000);
        //绘制marker
        Marker marker = aMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.986919, 116.353369))
                .icon(BitmapDescriptorFactory.fromBitmap(MapTestDataUtils.getMarkerPowerBitmap(mContext, "测试")))
                .draggable(true));
        markerList.add(marker);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void XpxEvent(xpxEvent _xpxEvent) {

    }
    @Override
    public View getInfoWindow(Marker marker) {
        View infoWindow = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_customview, null);//custom_info_window为自定义的layout文件
//        TextView name=(TextView)infoWindow.findViewById(R.id.inforwindow_text);
//        name.setText("北郊一网点");
        return infoWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View infoWindow = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_customview, null);//custom_info_window为自定义的layout文件
//        TextView name=(TextView)infoWindow.findViewById(R.id.inforwindow_text);
//        name.setText("北郊一网点");
        return infoWindow;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.e(TAG, "onInfoWindowClick: ");
    }
    @Override
    public void handleMsg(Message msg) {
        switch (msg.what) {
            case 1000:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap carIcon = MapTestDataUtils.getMarkerPowerBitmap(mContext, "测试");
                        BitmapDescriptor power = BitmapDescriptorFactory.fromBitmap(carIcon);

//                        obj = new GDMapMarkerTest(aMap, mContext);
//                        obj.addPointAndDraw(new LatLng(
//                                26.069154, 119.311131), power);
                        for (int i = 0; i < 500; i++
                                ) {
//                            Bitmap carIcon1 = mContext.getResources().getDrawable(R.drawable.btn_next);//MapTestDataUtils.getMarkerPowerBitmap(mContext, "测试"+i);//
                            BitmapDescriptor power1 = BitmapDescriptorFactory.fromResource(R.drawable.btn_next);
//                            BitmapDescriptor power1 = BitmapDescriptorFactory.fromBitmap(carIcon1);

                            GDMapMarkerTest obj1 = new GDMapMarkerTest(aMap, mContext);
                            try {
                                int j = (int) (Math.random() * 2);
                                if (j < 1) {
                                    obj1.addPointAndDraw(new LatLng(
                                            26.077591 - Math.random(), 119.345626 + Math.random()), power1,((float) (360*Math.random())));//119.345626,26.077591
                                } else {
                                    obj1.addPointAndDraw(new LatLng(
                                            26.077591 + Math.random(), 119.345626 - Math.random()), power1,((float) (360*Math.random())));//119.345626,26.077591
                                }
                                markerList.add(obj1.getMarker());
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
