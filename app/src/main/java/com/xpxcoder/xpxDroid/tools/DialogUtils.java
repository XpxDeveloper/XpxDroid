package com.xpxcoder.xpxDroid.tools;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.tools.dialogs.DialogBase;
import com.xpxcoder.xpxDroid.tools.dialogs.DialogNext;
import com.xpxcoder.xpxDroid.tools.showLargePhoto.DragImageView;

/**
 * Created by XPSoft on 2018/5/10.
 */

public class DialogUtils {
    public static void dialogConfirm(FragmentManager fm, String msg, DialogBase.FootClickListener left, DialogBase.FootClickListener right) {
        DialogNext dialog = new DialogNext();
        dialog.setHeaderTitle("提示");
        dialog.setCustomText(msg)
                .addCustomBtn("取消",left).addCustomBtn("确定", right);
        dialog.show(fm, "dn");
        dialog.setCancelable(false);
    }
    public static void dialogConfirm(FragmentManager fm,String msg, DialogBase.FootClickListener right) {
        DialogNext dialog = new DialogNext();
        dialog.setHeaderTitle("提示");
        dialog.setCustomText(msg)
                .addCustomBtn("取消", new DialogBase.FootClickListener() {
                    @Override
                    public void click(DialogFragment dialog, View view) {
                        dialog.dismiss();
                    }
                }).addCustomBtn("确定", right);
        dialog.show(fm, "dn");
        dialog.setCancelable(false);
    }
    public static void dialog3Confirm(FragmentManager fm, String msg, DialogBase.FootClickListener left, DialogBase.FootClickListener middle, DialogBase.FootClickListener right) {
        DialogNext dialog = new DialogNext();
        dialog.setHeaderTitle("提示");
        dialog.setCustomText(msg)
                .addCustomBtn("取消", left).addCustomBtn("重置", middle).addCustomBtn("确定", right);
        dialog.show(fm, "dn");
        dialog.setCancelable(false);
    }
    public static void dialog3Confirm(FragmentManager fm, String msg, DialogBase.FootClickListener middle, DialogBase.FootClickListener right) {
        DialogNext dialog = new DialogNext();
        dialog.setHeaderTitle("提示");
        dialog.setCustomText(msg)
                .addCustomBtn("取消", new DialogBase.FootClickListener() {
                    @Override
                    public void click(DialogFragment dialog, View view) {
                        dialog.dismiss();
                    }
                }).addCustomBtn("重置", middle).addCustomBtn("确定", right);
        dialog.show(fm, "dn");
        dialog.setCancelable(false);
    }
    private static int state_height = 0;//全屏查看大图时使用这个变量
    public static Dialog showLargePicture(Dialog dialog, LayoutInflater _Inflater,
                                        final Context context, Bitmap bitmap, boolean CanceledOnTouchOutside) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog=null;
            return null;
        }
        state_height = 0;
        View mView = _Inflater.inflate(R.layout.dialog_showlargepicture, null);

        RelativeLayout ibtn_close = (RelativeLayout) mView
                .findViewById(R.id.btn_close);
        final DragImageView dragImageView = (DragImageView) mView
                .findViewById(R.id.dialog_picture);
        WindowManager manager = ((Activity) context).getWindowManager();
        final int window_width = manager.getDefaultDisplay().getWidth()
                ;
        final int window_height = manager.getDefaultDisplay().getHeight()
                ;
        // dragImageView.setBackgroundResource(resId);

        Bitmap bmp = BitmapUtils.getBitmap(bitmap, window_width, window_height);
        // BitmapUtil.ReadBitmapById(context, resId, window_width,
        // window_height);
        // 设置图片
        dragImageView.setImageBitmap(bmp);
        dragImageView.setmActivity((Activity) context);// 注入Activity.
        /** 测量状态栏高度 **/
        ViewTreeObserver viewTreeObserver = dragImageView.getViewTreeObserver();
        viewTreeObserver
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        if (state_height == 0) {
                            // 获取状况栏高度
                            Rect frame = new Rect();
                            ((Activity) context).getWindow()
                                    .getDecorView()
                                    .getWindowVisibleDisplayFrame(frame);
                            state_height = frame.top;
                            dragImageView.setScreen_H(window_height
                                    - state_height);
                            dragImageView.setScreen_W(window_width);
                        }

                    }
                });



        dialog = new Dialog(context,
                R.style.MyFullScreenDialog);
        final Dialog fDialog=dialog;

        ibtn_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO 自动生成的方法存根
                if(fDialog!=null){
                    fDialog.dismiss();
                }
            }
        });

        // 设置它的ContentView
        dialog.setContentView(mView);
        dialog.setCanceledOnTouchOutside(CanceledOnTouchOutside);

        WindowManager wm = ((Activity) context).getWindowManager();
        int screenWidth = wm.getDefaultDisplay().getWidth();
        int screenHeight = wm.getDefaultDisplay().getHeight();

        int ContentWidth = (int)UiUtils.dp2px(context, 1000);
        int ContentHeight = (int)  UiUtils.dp2px(context, 700);

        WindowManager.LayoutParams lp = dialog.getWindow()
                .getAttributes();
        lp.width = screenWidth;// (int)(display.getWidth()); //设置宽度
        lp.height = screenHeight;
        dialog.getWindow().setAttributes(lp);

        dialog.show();
        return dialog;
    }
}
