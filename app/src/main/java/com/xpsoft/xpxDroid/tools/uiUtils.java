package com.xpsoft.xpxDroid.tools;

import android.content.Context;
import android.support.design.widget.BaseTransientBottomBar;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by XPSoft on 2018/2/17.
 */

public class uiUtils {
    /*public static int Dp2Px(Context _Context, double _dipval)
    {
        float scale=_Context.getResources().getDisplayMetrics().density;
        return (int) (_dipval*scale+0.5f);
    }*/
    public static  int dp2px(Context _Context,int dp) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,_Context.getResources().getDisplayMetrics());
    }

    public static  int sp2px(Context _Context,int sp) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, _Context.getResources().getDisplayMetrics());
    }
    public static void showToastShort(Context _context, String _content){
        Toast.makeText(_context, _content,Toast.LENGTH_SHORT).show();
    }
    public static void showToastLong(Context _context, String _content){
        Toast.makeText(_context, _content,Toast.LENGTH_LONG).show();
    }
    public static ScreenSize getScreen(Window window){
        ScreenSize screenSize=new ScreenSize();
        WindowManager wm=window.getWindowManager();
        screenSize.width=wm.getDefaultDisplay().getWidth();
        screenSize.height=wm.getDefaultDisplay().getHeight();
        return screenSize;
    }
    public static class ScreenSize{
        public int width;
        public int height;
    }
}
