/**
 * @author 作者 E-mail:
 * @date 创建时间：2017年6月13日 上午10:20:01
 * @version 1.0
 * @parameter
 * @return
 */
package com.xpsoft.xpxDroid.map.gdmap;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.TextOptions;


/**
 * @author xpsoft
 *
 */
public class GDMapMarkerTest implements Parcelable {

    private Marker marker;
    private AMap mMap;
    private LatLng point;
    private String PowerName;

    private Context mContext;

    private GDMapMarkerTest(Parcel in) {
//        CarInfo mCarInfo = in.readParcelable(CarInfo.class.getClassLoader());

    }

    public GDMapMarkerTest(AMap baiduMap, Context context) {
        this.mMap = baiduMap;
        this.mContext = context;
    }

    public void remove() {
        if (marker != null) {
            marker.remove();
            marker = null;
        }

    }

    public Marker getMarker() {
        return marker;
    }

    public LatLng getPosition() {
        return point;
    }

    // addToList为标记是否要加到mBMapHelper的列表中
    public void addPointAndDraw(LatLng LatLng, BitmapDescriptor bitmap,float rotate) {
        if (LatLng == null)
            return;

        this.point = LatLng;
        //高德地图设置旋转角度是以正北为起点,逆时针旋转的.     是逆时针的...........    所以转化成顺时针效果要用360做差
        MarkerOptions mOverlay = new MarkerOptions().position(point)
                .icon(bitmap).title("测试").snippet("内容").anchor(0.0f, 0.5f).zIndex(9).draggable(false).rotateAngle(rotate);
        //会因为图片的外部尺寸和图片内部实际内容的尺寸的因素，而导致anchor的2个值不同，而不是写一样的数值
        marker = mMap.addMarker(mOverlay);
//        ((Marker)marker).setRotate();

        TextOptions mTO=new TextOptions().position(point).zIndex(9).backgroundColor(Color.YELLOW).text("测试").fontSize(30);
        mMap.addText(mTO);

    }
    public void updatePosition(){
        this.point=new LatLng(this.point.latitude+0.001,this.point.longitude+0.001);
        ((Marker) marker).setPosition(this.point);
    }


    /*
     * （非 Javadoc）
     *
     * @see android.os.Parcelable#describeContents()
     */
    @Override
    public int describeContents() {
        // TODO 自动生成的方法存根
        return 0;
    }

    /*
     * （非 Javadoc）
     *
     * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO 自动生成的方法存根

    }

    public static final Creator<GDMapMarkerTest> CREATOR
            = new Creator<GDMapMarkerTest>() {
        public GDMapMarkerTest createFromParcel(Parcel in) {
            return new GDMapMarkerTest(in);
        }

        public GDMapMarkerTest[] newArray(int size) {
            return new GDMapMarkerTest[size];
        }
    };

    /**
     * @return powerName
     */
    public String getPowerName() {
        return PowerName;
    }

    /**
     * @param powerName
     *            要设置的 powerName
     */
    public void setPowerName(String powerName) {
        PowerName = powerName;
    }

}
