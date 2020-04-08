/**
 * @author 作者 E-mail:
 * @date 创建时间：2017年6月13日 上午10:20:01
 * @version 1.0
 * @parameter
 * @return
 */
package com.xpxcoder.xpxDroid.map.bmap;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;


/**
 * @author xpsoft
 *
 */
public class BMapMarkerTest implements Parcelable {

    private Overlay ol;
    private BaiduMap mBaiduMap;
    private LatLng point;
    private String PowerName;

    private Context mContext;

    private BMapMarkerTest(Parcel in) {
//        CarInfo mCarInfo = in.readParcelable(CarInfo.class.getClassLoader());

    }

    public BMapMarkerTest(BaiduMap baiduMap, Context context) {
        this.mBaiduMap = baiduMap;
        this.mContext = context;
    }

    public void remove() {
        if (ol != null) {
            ol.remove();
            ol = null;
        }

    }

    public LatLng getPosition() {
        return point;
    }

    // addToList为标记是否要加到mBMapHelper的列表中
    public void addPointAndDraw(LatLng LatLng, BitmapDescriptor bitmap) {
        if (LatLng == null)
            return;

        this.point = LatLng;
        OverlayOptions mOverlay = new MarkerOptions().position(point)
                .icon(bitmap).anchor(0.2f, 0.5f).zIndex(9).draggable(false);
        //会因为图片的外部尺寸和图片内部实际内容的尺寸的因素，而导致anchor的2个值不同，而不是写一样的数值
        ol = mBaiduMap.addOverlay(mOverlay);
//        ((Marker)ol).setRotate();

       /* TextOptions mTO=new TextOptions().position(point).zIndex(9).bgColor(Color.WHITE).text("测试").fontSize(30);
        mBaiduMap.addOverlay(mTO);*/

    }
    public void updatePosition(){
        this.point=new LatLng(this.point.latitude+0.001,this.point.longitude+0.001);
        ((Marker)ol).setPosition(this.point);
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

    public static final Parcelable.Creator<BMapMarkerTest> CREATOR
            = new Parcelable.Creator<BMapMarkerTest>() {
        public BMapMarkerTest createFromParcel(Parcel in) {
            return new BMapMarkerTest(in);
        }

        public BMapMarkerTest[] newArray(int size) {
            return new BMapMarkerTest[size];
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
