package com.xpsoft.xpxDroid.tools.dialogs;

/**
 * Created by XPSoft on 2018/3/27.
 * recycleview每个记录的实体类
 */

public class DialogBaseRecycleSelectModel {
    public boolean selected;//是否选中了
    public String showName = "";//显示名称
    public String itemKey;//项目开发中，选中一项后，需要保存记录这个值到sp文件中
    public String dialogRowKey = "";//对话框中，当前行的key

    public DialogBaseRecycleSelectModel(boolean selected, String showName, String dialogRowKey, String itemKey) {
        this.selected = selected;
        this.showName = showName;
        this.dialogRowKey=dialogRowKey;
        this.itemKey = itemKey;
    }
}
