package com.xpsoft.xpxDroid.views;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.models.eventbus.xpxEvent;
import com.xpsoft.xpxDroid.tools.AsynTaskUtils;
import com.xpsoft.xpxDroid.tools.BitmapUtils;
import com.xpsoft.xpxDroid.tools.dialogUtils;
import com.xpsoft.xpxDroid.tools.dialogs.DialogBase;
import com.xpsoft.xpxDroid.tools.dialogs.DialogBaseRecycleSelectModel;
import com.xpsoft.xpxDroid.tools.dialogs.DialogFromBottom;
import com.xpsoft.xpxDroid.tools.dialogs.DialogGrid;
import com.xpsoft.xpxDroid.tools.dialogs.DialogNext;
import com.xpsoft.xpxDroid.tools.dialogs.DialogRowEdit;
import com.xpsoft.xpxDroid.tools.dialogs.DialogRowSelect;
import com.xpsoft.xpxDroid.tools.dialogs.DialogMultiRowsSingleSelect;
import com.xpsoft.xpxDroid.tools.dialogs.DialogResultModel;
import com.xpsoft.xpxDroid.tools.dialogs.DialogRowSpinner;
import com.xpsoft.xpxDroid.tools.dialogs.DialogTipSimple;
import com.xpsoft.xpxDroid.tools.dialogs.DialogWait;
import com.xpsoft.xpxDroid.widget.AppTitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPSoft on 2018/3/27.
 */

public class DialogActivity extends baseFragActivity {

    private AppTitleBar appTitleBar;

    @Override
    public void handleMsg(Message msg) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        appTitleBar = (AppTitleBar) findViewById(R.id.appTitleBar);
        appTitleBar.setTitle("对话框");
        appTitleBar.setOnTitleBarClickListener(new AppTitleBar.OnTitleBarClickListener() {
            @Override
            public void onBack() {
                finish();
            }
        });
    }

    @Override
    public void XpxEvent(xpxEvent _xpxEvent) {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                dialogMultiRowsSingleSelect();
                break;
            case R.id.btn2:
                dialogFromBottom();
                break;
            case R.id.btn3:
                dialogInputNumber();
                break;
            case R.id.btn4:
                dialogTipSimple();
                break;
            case R.id.btn5:
                dialogSpinner();
                break;
            case R.id.btn6:
                AsynTask();
                break;
            case R.id.btn7:
                StopAsynTask();
                break;
            case R.id.btn8:
                dialogWaiting();
                break;
            case R.id.btn9:
                dialogConfirm();
                break;
            case R.id.btn10:
                dialogConfirm2();
                break;
            case R.id.btn11:
                dialogConfirm4();
                break;
            case R.id.btn12:
                dialogFromBottom_withFull();
                break;
            case R.id.btn13:
                dialogConfirm5();
                break;
            case R.id.btn14:
                dialogConfirm6();
                break;
            case R.id.btn15:
                dialogShowLargePicture();
                break;
            case R.id.btn16:
                dialogShowGrid();
                break;

        }
    }

    private void dialogShowGrid() {
        DialogGrid dialogGrid=new DialogGrid();
        dialogGrid.show(getSupportFragmentManager(), "dn");
        dialogGrid.setCancelable(false);
    }

    private void dialogConfirm() {
        DialogNext dialog = new DialogNext();
        dialog.setHeaderTitle("提示");
        dialog.setCustomText("吃饭了吗？")
                .addCustomBtn("取消", new DialogBase.FootClickListener() {
                    @Override
                    public void click(DialogFragment dialog, View view) {
                        dialog.dismiss();
                    }
                }).addCustomBtn("重置", new DialogBase.FootClickListener() {
            @Override
            public void click(DialogFragment dialog, View view) {
                dialog.dismiss();
            }
        }).addCustomBtn("确定", new DialogBase.FootClickListener() {
            @Override
            public void click(DialogFragment dialog, View view) {
                dialog.dismiss();
            }
        });
        dialog.show(getSupportFragmentManager(), "dn");
        dialog.setCancelable(false);
    }

    private void dialogConfirm2() {
        DialogNext dialog = new DialogNext();
        dialog.setHeaderTitle("提示");
        dialog.setCustomText("吃饭了吗？")
                .addCustomBtn("取消", new DialogBase.FootClickListener() {
                    @Override
                    public void click(DialogFragment dialog, View view) {
                        dialog.dismiss();
                    }
                }).addCustomBtn("确定", new DialogBase.FootClickListener() {
            @Override
            public void click(DialogFragment dialog, View view) {
                dialog.dismiss();
            }
        });
        dialog.show(getSupportFragmentManager(), "dn");
        dialog.setCancelable(false);
    }

    private void dialogConfirm4() {
        DialogNext dialog = new DialogNext();
        dialog.setHeaderTitle("提示");
        dialog.setCustomText("吃饭了吗？")
                .addCustomBtn("取消", new DialogBase.FootClickListener() {
                    @Override
                    public void click(DialogFragment dialog, View view) {
                        dialog.dismiss();
                    }
                }).addCustomBtn("菜单2", new DialogBase.FootClickListener() {
            @Override
            public void click(DialogFragment dialog, View view) {
                dialog.dismiss();
            }
        }).addCustomBtn("菜单3", new DialogBase.FootClickListener() {
            @Override
            public void click(DialogFragment dialog, View view) {
                dialog.dismiss();
            }
        }).addCustomBtn("确定", new DialogBase.FootClickListener() {
            @Override
            public void click(DialogFragment dialog, View view) {
                dialog.dismiss();
            }
        });
        dialog.show(getSupportFragmentManager(), "dn");
        dialog.setCancelable(false);
    }

    private void dialogConfirm5() {
        DialogNext dialog = new DialogNext();
        dialog.setHeaderTitle("提示");
        dialog.hideHeader()
//                .showHeaderTitleToContent()
                .showSimpleHeader().setMarginWidth(250);
        dialog.setCustomText("吃饭了吗？吃饭了吗？吃饭了吗？吃饭了吗？吃饭了吗？")
                .addCustomBtn("取消", new DialogBase.FootClickListener() {
                    @Override
                    public void click(DialogFragment dialog, View view) {
                        dialog.dismiss();
                    }
                }).addCustomBtn("确定", new DialogBase.FootClickListener() {
            @Override
            public void click(DialogFragment dialog, View view) {
                dialog.dismiss();
            }
        });
        dialog.show(getSupportFragmentManager(), "dn");
        dialog.setCancelable(false);
    }

    private void dialogConfirm6() {
        DialogNext dialog = new DialogNext();
        dialog.setHeaderTitle("提示");
        dialog.hideHeader()
//                .showHeaderTitleToContent()
                .showSimpleHeader().setMarginWidth(250);
        dialog.setCustomText("吃饭了吗？吃饭了吗？吃饭了吗？吃饭了吗？吃饭了吗？")
                .addCustomBtn("确定", new DialogBase.FootClickListener() {
                    @Override
                    public void click(DialogFragment dialog, View view) {
                        dialog.dismiss();
                    }
                })
        ;
        dialog.show(getSupportFragmentManager(), "dn");
        dialog.setCancelable(false);
    }
    private Dialog mShowLargePictureDialog;
    private void dialogShowLargePicture(){
        Bitmap bitmap= BitmapUtils.ReadBitmapById(mContext,R.drawable.demo_background);
        mShowLargePictureDialog=dialogUtils.showLargePicture(mShowLargePictureDialog,mLayoutInflater,mContext,bitmap,true);
    }

    private void dialogWaiting() {
        DialogWait dialog = new DialogWait();
        dialog.show(getSupportFragmentManager(), "wait");
        dialog.showTime(true);
        dialog.setCancelable(false);
        dialog.setWaitListener(new DialogWait.WaitListener() {
            @Override
            public void execute(DialogWait _dialogWait) {
                _dialogWait.dismiss();
            }

        });
    }

    private AsynTaskUtils.DefaultAsynTasker defaultAsynTasker;

    private void StopAsynTask() {
        if (defaultAsynTasker != null) {
            defaultAsynTasker.needToStop = true;
            if (!defaultAsynTasker.isCancelled()) {
                defaultAsynTasker.cancel(true);
            }

            AsynTaskUtils.getInstance().removeTask(defaultAsynTasker);
        }
    }

    private void AsynTask() {
        defaultAsynTasker = new AsynTaskUtils.DefaultAsynTasker(this.getClass(), 5000, 12);
        defaultAsynTasker.setAsynTaskerListener(new AsynTaskUtils.AsynTaskerListener() {
            @Override
            public void doInBackground(AsynTaskUtils.DefaultAsynTasker _defaultAsynTasker) {
                Log.i(TAG, "doInBackground: 正在执行");
                _defaultAsynTasker.running = false;
            }

            @Override
            public void hasDone() {

            }
        });
        defaultAsynTasker.execute();
        AsynTaskUtils.getInstance().addAsynTasker(defaultAsynTasker);
    }

    private void dialogSpinner() {
        DialogMultiRowsSingleSelect dialogBase = new DialogMultiRowsSingleSelect();
        dialogBase.setHeaderTitle("数字");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        dialogBase.show(ft, "df");
        dialogBase.setCancelable(false);

        DialogRowSpinner drp = new DialogRowSpinner(mContext);
        List<String> list = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            list.add("测试" + i);
        }
        drp.setList(list);
        dialogBase.addSpinnerRow(drp);

        DialogRowEdit dbs = new DialogRowEdit(mContext)
                .setHint("name", "名称：", "", "请输入名称", "请输入名称", 15, 0).setTvKeyWidth(80);
        DialogRowEdit dbs2 = new DialogRowEdit(mContext)
                .setHint("telephone", "有效期(天)：", "", "请输入天数", "请输入天数", 15, 4).setInputNumber().setTvKeyWidth(80);
        dialogBase.addEditRow(dbs);
        dialogBase.addEditRow(dbs2);
        dialogBase.setListener(new DialogMultiRowsSingleSelect.Listener() {
            @Override
            public void ok(List<DialogResultModel> list) {
                for (DialogResultModel obj : list
                        ) {
                    Log.i(TAG, "ok:key： " + obj.key + ",value：" + obj.value);
                }
            }
        });
    }

    private void dialogFromBottom() {
        DialogFromBottom dialogBase = new DialogFromBottom();
        dialogBase.addCustomBtn("菜单1", new DialogBase.FootClickListener() {
            @Override
            public void click(DialogFragment dialog, View view) {

            }
        }).addCustomBtn("菜单2", new DialogBase.FootClickListener() {
            @Override
            public void click(DialogFragment dialog, View view) {

            }
        }).addCustomBtn("菜单3", new DialogBase.FootClickListener() {
            @Override
            public void click(DialogFragment dialog, View view) {

            }
        });
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        dialogBase.show(ft, "dfb");
        dialogBase.setCancelable(true);
        dialogBase.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomDialog);
    }

    private void dialogFromBottom_withFull() {
        DialogFromBottom dialogBase = new DialogFromBottom();
        dialogBase.setWithRadius(false);
        dialogBase.addCustomBtn("菜单1", new DialogBase.FootClickListener() {
            @Override
            public void click(DialogFragment dialog, View view) {

            }
        }).addCustomBtn("菜单2", new DialogBase.FootClickListener() {
            @Override
            public void click(DialogFragment dialog, View view) {

            }
        }).addCustomBtn("菜单3", new DialogBase.FootClickListener() {
            @Override
            public void click(DialogFragment dialog, View view) {

            }
        });
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        dialogBase.show(ft, "dfb");
        dialogBase.setCancelable(true);
        dialogBase.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomDialog);
    }

    private void dialogTipSimple() {
        DialogTipSimple dialog = new DialogTipSimple();
        dialog.setHeaderTitle("提示");
        dialog.setMarginWidth(100);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        dialog.show(ft, "df");
        dialog.setCancelable(false);
        dialog.setCustomText("早上好");
    }

    private void dialogInputNumber() {
        DialogMultiRowsSingleSelect dialogBase = new DialogMultiRowsSingleSelect();
        dialogBase.setHeaderTitle("数字");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        dialogBase.show(ft, "df");
        dialogBase.setCancelable(false);


        DialogRowEdit dbs = new DialogRowEdit(mContext)
                .setHint("name", "名称：", "", "请输入名称", "请输入名称", 15, 0).setTvKeyWidth(80);
        DialogRowEdit dbs2 = new DialogRowEdit(mContext)
                .setHint("telephone", "有效期(天)：", "", "请输入天数", "请输入天数", 15, 4).setInputNumber().setTvKeyWidth(80);
        dialogBase.addEditRow(dbs);
        dialogBase.addEditRow(dbs2);
        dialogBase.setListener(new DialogMultiRowsSingleSelect.Listener() {
            @Override
            public void ok(List<DialogResultModel> list) {
                for (DialogResultModel obj : list
                        ) {
                    Log.i(TAG, "ok:key： " + obj.key + ",value：" + obj.value);
                }
            }
        });
    }

    private void dialogMultiRowsSingleSelect() {
        DialogMultiRowsSingleSelect dialogBase = new DialogMultiRowsSingleSelect();
        dialogBase.setHeaderTitle("你好你好");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        dialogBase.show(ft, "df");
        dialogBase.setCancelable(false);

        List<DialogBaseRecycleSelectModel> list1 = new ArrayList<>();
        list1.add(new DialogBaseRecycleSelectModel(true, "测试A", "AAA", "1"));
        list1.add(new DialogBaseRecycleSelectModel(false, "测试bbbbbbbbcccc", "AAA", "2"));
        list1.add(new DialogBaseRecycleSelectModel(false, "测试c", "AAA", "3"));
        list1.add(new DialogBaseRecycleSelectModel(false, "测试d", "AAA", "4"));
        list1.add(new DialogBaseRecycleSelectModel(false, "测试e", "AAA", "5"));


        dialogBase.addSingleSelect(new DialogRowSelect(mContext, "测试1：").setTvKeyWidth(60).convertData("AAA", list1));
        dialogBase.addSingleSelect(new DialogRowSelect(mContext, "测试2：", 1).setTvKeyWidth(60).convertData("AAA", list1));
        dialogBase.addSingleSelect(new DialogRowSelect(mContext, "测试3：").setTvKeyWidth(60).convertData("AAA", list1));
        dialogBase.addSingleSelect(new DialogRowSelect(mContext, "测试4：").setTvKeyWidth(60).convertData("AAA", list1));

        DialogRowEdit dbs = new DialogRowEdit(mContext).setHint("name", "名称：", "", "请输入名称", "请输入名称", 15, 0).setTvKeyWidth(60);
        DialogRowEdit dbs2 = new DialogRowEdit(mContext).setHint("telephone", "号码：", "", "请输入号码", "请输入号码", 15, 4).setTvKeyWidth(60);
        dialogBase.addEditRow(dbs);
        dialogBase.addEditRow(dbs2);
        dialogBase.addSingleSelect(new DialogRowSelect(mContext, "测试5：").setTvKeyWidth(60).convertData("AAA", list1));
        dialogBase.addSingleSelect(new DialogRowSelect(mContext, "测试6：").setTvKeyWidth(60).convertData("AAA", list1));
        dialogBase.addSingleSelect(new DialogRowSelect(mContext, "测试7：").setTvKeyWidth(60).convertData("AAA", list1));
        dialogBase.addSingleSelect(new DialogRowSelect(mContext, "测试8：").setTvKeyWidth(60).convertData("AAA", list1));


        dialogBase.setListener(new DialogMultiRowsSingleSelect.Listener() {
            @Override
            public void ok(List<DialogResultModel> list) {
                for (DialogResultModel obj : list
                        ) {
                    Log.i(TAG, "ok:key： " + obj.key + ",value：" + obj.value);
                }
            }
        });
    }
}
