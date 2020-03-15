package com.xpsoft.xpxDroid.widget.draws;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.xpsoft.xpxDroid.tools.UiUtils;

/**
 * Created by XPSoft on 2018/4/16.
 * 自定义时钟
 */

public class CustomClock extends View {

    private Paint mPaintCircle5Min;
    private Paint mPaintCircle1Min;
    private TextPaint mTextPaint;
    private Context mContext;
    private int DEFAULT_HEIGHT = 0;
    private int DEFAULT_WIDTH = 0;

    public CustomClock(Context context) {
        this(context, null);
    }

    public CustomClock(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        DEFAULT_HEIGHT = UiUtils.dp2px(mContext, 50);
        DEFAULT_WIDTH = UiUtils.dp2px(mContext, 50);
        mPaintCircle5Min = new Paint();
        mPaintCircle1Min=new Paint();
        mTextPaint=new TextPaint();

        mTextPaint.setTextSize(15);
        mTextPaint.setColor(Color.BLACK);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = DEFAULT_WIDTH;
        int height = DEFAULT_HEIGHT;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = Math.min(width, widthSize);
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = Math.min(height, heightSize);
        }
        width = Math.max(width, height);

        setMeasuredDimension(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int padding=getPaddingLeft()+getPaddingEnd();

        int r=getWidth()/2-padding/2;
        double l=(int)(2*Math.PI*r);//周长
        float l_360=(float)l/360;//360分之一

        mPaintCircle5Min.setStyle(Paint.Style.STROKE);
        mPaintCircle5Min.setStrokeWidth(4);
        mPaintCircle5Min.setAntiAlias(true);
        mPaintCircle5Min.setColor(Color.RED);
        mPaintCircle5Min.setPathEffect(new DashPathEffect(new float[]{2*l_360, 28*l_360}, 0));

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, r, mPaintCircle5Min);


        mPaintCircle1Min.setStyle(Paint.Style.STROKE);
        mPaintCircle1Min.setStrokeWidth(2);
        mPaintCircle1Min.setAntiAlias(true);
        mPaintCircle1Min.setColor(Color.RED);
        mPaintCircle1Min.setPathEffect(new DashPathEffect(new float[]{1*l_360, 5*l_360}, 0));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, r, mPaintCircle1Min);

        canvas.drawText("sss",getWidth() / 2, getHeight() / 2,mTextPaint);

        //C点为圆心，A点为直角边的一边，B点为斜边

        float ac=(float)Math.cos(Math.toRadians(90))*(r-10);
        float ab=(float)Math.sin(Math.toRadians(90))*(r-10);

        mTextPaint.setStrokeWidth(4);
        canvas.drawLine(getWidth()/2,getHeight()/2,getWidth()/2+ab,getHeight()/2-ac,mTextPaint);
    }
}
