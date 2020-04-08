package com.xpxcoder.xpxDroid.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import com.xpxcoder.xpxDroid.R;

/**
 * Created by XPSoft on 2018/8/15.
 */

public class MapTestDataUtils {
    //横向排列
    public static Bitmap getMarkerPowerBitmap(Context context, String value) {
        int iconWidth = (int) UiUtils.dp2px(context, 30);
        int iconHeight = (int) UiUtils.dp2px(context, 30);
        Drawable iconDraw = context.getResources()
                .getDrawable(R.drawable.btn_next);

        // 车牌号文字颜色
        Paint txtPaint = new Paint();
        txtPaint.setColor(Color.parseColor("black"));
        Typeface font = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);
        txtPaint.setTypeface(font);
        txtPaint.setTextScaleX(2.0F);
        txtPaint.setTextSize(UiUtils.dp2px(context, 15));// (int)(14 *scale+
        // 0.5f)

        float textLength = getFontLength(txtPaint, value)
                + UiUtils.dp2px(context, 14);
        float textHight = getFontHeight(txtPaint)
                + UiUtils.dp2px(context, 4);
        float textAlign = getFontLeading(txtPaint)
                + UiUtils.dp2px(context, 2);

        int iBitmapWith = (int) (iconWidth + textLength);
        int iBitmapHeight = iconHeight;
        Bitmap bitmap = Bitmap.createBitmap(iBitmapWith, iBitmapHeight,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        int startx = 0;
        int starty = 0;

        //
        if (iconDraw != null) {
            iconDraw.setBounds(startx, starty+10, startx + iconWidth,
                    (starty + iconHeight));
            iconDraw.draw(canvas);
        }

        //
        startx = startx + iconWidth+ UiUtils.dp2px(context, 6);
        starty = (int) UiUtils.dp2px(context, 8);
        Paint paint = new Paint();
        // 实际测试过程中，发现textLength要比fRectRight大一些，才能把左右边框显示出来，所以下面计算时减5，不一定是减5
        // 只是测试时，减5，然后textLength+5，这样结合使用有效果，没有去深究
        float fRectRight = startx + (int) UiUtils.dp2px(context, 2)
                + textLength - (int)UiUtils.dp2px(context, 5);
        // canvas.drawRect(startx+2, starty, startx +2+ textLength+6+textAlign,
        // starty + textHight, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(1);
        canvas.drawRect(startx+10, starty,
                fRectRight+20, starty + textHight, paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        // 车牌号矩形框背景色
        paint.setColor(Color.WHITE);
        canvas.drawRect(startx + (int) UiUtils.dp2px(context, 1) + 10,
                starty + 1, fRectRight - 2, starty + textHight - 1, paint);
        canvas.drawText(value, startx +10+ (int) UiUtils.dp2px(context, 3),
                starty + textAlign, txtPaint);

        return bitmap;
    }

    public static Bitmap getMarkerPowerBitmap2(Context context, String value,float TextScaleX,int _paintStrokeWidth) {
        int iconWidth = (int) UiUtils.dp2px(context, 30);
        int iconHeight = (int) UiUtils.dp2px(context, 30);
        Drawable iconDraw = context.getResources()
                .getDrawable(R.drawable.btn_next);

        // 车牌号文字颜色
        Paint txtPaint = new Paint();
        txtPaint.setColor(Color.parseColor("black"));
        Typeface font = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);
        txtPaint.setTypeface(font);
        txtPaint.setTextScaleX(TextScaleX);
        txtPaint.setTextSize(UiUtils.dp2px(context, 15));// (int)(14 *scale+
        // 0.5f)

        int textPaddingHorizon= UiUtils.dp2px(context, 3);
        int textPaddingVertical=UiUtils.dp2px(context, 2);
        float textWidth = getFontLength(txtPaint, value)
                +2*textPaddingHorizon;
        float textHeight = getFontHeight(txtPaint);//  +2*textPaddingVertical ;
        float textAlign = getFontLeading(txtPaint)
                + UiUtils.dp2px(context, 2);


        int rectWidth=(int)textWidth+iconWidth;//UiUtils.dp2px(context, 100);//矩形框的宽度
        int rectHeight=(int)textHeight;//UiUtils.dp2px(context, 50);//矩形框的高度

        int maxHeight=iconHeight>rectHeight?iconHeight:rectHeight;
        Bitmap bitmap = Bitmap.createBitmap( rectWidth,  maxHeight,
                Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();

        int paintStrokeWidth=_paintStrokeWidth<1?2:_paintStrokeWidth;//如果设置为1，则右边框和底边框，可能显示不出来，所以至少设置为2
        Canvas canvas = new Canvas(bitmap);

        //画车辆图标
        iconDraw.setBounds(0, 0, iconWidth, iconHeight);
        iconDraw.draw(canvas);


        //画矩形框
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(paintStrokeWidth);
        int rectStart=iconWidth;
        int rectTop=(maxHeight-rectHeight)/2;
        canvas.drawRect(rectStart, rectTop,
                rectWidth, rectTop+rectHeight, paint);

        //画文字背景色
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(rectStart +paintStrokeWidth,
                rectTop+paintStrokeWidth, rectWidth-paintStrokeWidth,rectTop+rectHeight-paintStrokeWidth, paint);

        //写文字
        Paint.FontMetrics fontMetrics = txtPaint.getFontMetrics();
        canvas.drawText(value,rectStart+  textPaddingHorizon,rectTop+ Math.abs(fontMetrics.top),txtPaint);


        return bitmap;
    }


    /**
     * ַ ߶
     *
     * @param paint
     * @return
     */
    public static float getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }

    /**
     *
     * @param paint
     * @return
     */
    public static float getFontLeading(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.leading - fm.ascent;
    }
    public static float getFontLength(Paint paint, String str) {
        return paint.measureText(str);
    }


}
