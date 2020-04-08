package com.xpxcoder.xpxDroid.widget.draws;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.tools.UiUtils;

/**
 * Created by XPSoft on 2018/4/14.
 */

public class CustomTextViewCircle extends View {

    private Context mContext;
    private float mTextSize;
    private int mTextColor;
    private int mBackgroundColor;
    private float mRadius;
    private String mText="";
    private Paint mPaint;
    private Rect rect;

    private int DEFAULT_HEIGHT=0;
    private int DEFAULT_WIDTH=0;

    public CustomTextViewCircle(Context context) {
        this(context,null);
    }

    public CustomTextViewCircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomTextViewCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        rect=new Rect();
        DEFAULT_HEIGHT=UiUtils.dp2px(mContext,50);
        DEFAULT_WIDTH=UiUtils.dp2px(mContext,50);
        TypedArray typedArray=null;
        mPaint=new Paint();
        try{
            typedArray=mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView,defStyleAttr,0);
            mTextSize=typedArray.getDimension(R.styleable.CustomView_xpxTextSize, UiUtils.dp2px(mContext,14));
            mTextColor=typedArray.getColor(R.styleable.CustomView_xpxColor, Color.BLACK);
            mBackgroundColor=typedArray.getColor(R.styleable.CustomView_xpxBackgroundColor,Color.WHITE);
            mRadius=typedArray.getDimension(R.styleable.CustomView_xpxRadius,0);
            mText=typedArray.getString(R.styleable.CustomView_xpxText);

            mPaint.setTextSize(mTextSize);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setAntiAlias(true);//抗锯齿
            mPaint.setDither(true);//防抖动
            mPaint.getTextBounds(mText, 0, mText.length(), rect);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            typedArray.recycle();
        }
        initView();
    }
    private void initView(){

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
        width=Math.max(width,height);

        setMeasuredDimension(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mBackgroundColor);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaint);
        mPaint.setColor(mTextColor);
        canvas.drawText(mText,getWidth()/2-rect.width()/2,getHeight()/2+rect.height()/2,mPaint);
    }
}
