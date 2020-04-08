package com.xpxcoder.xpxDroid.tools.dialogs;

import android.content.Context;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.adapter.recyclerview.DialogBaseSelectAdapter;
import com.xpxcoder.xpxDroid.adapter.recyclerview.dispatchgrouplistAdapter;
import com.xpxcoder.xpxDroid.tools.UiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPSoft on 2018/3/27.
 * 对话框中，添加一行单选操作
 */

public class DialogRowSelect extends DialogRowBase {

    private String mTitle="";//当前行，显示的字段名称
    private TextView tvKey;
    private RecyclerView rvContent;
    private int mRowShowNum=3;//每行显示的单选元素数量，默认3个
    private List<DialogBaseRecycleSelectModel> mList =new ArrayList<>();

    private DialogBaseSelectAdapter mAdapter;
    private boolean multiSelected;//控制是否多选


    public DialogRowSelect(@NonNull Context context, String _rowTitle) {
        this(context,null,_rowTitle,3);
    }
    public DialogRowSelect(@NonNull Context context, String _rowTitle, int rowShowNum) {
        this(context,null,_rowTitle,rowShowNum);
    }

    public DialogRowSelect(@NonNull Context context, @Nullable AttributeSet attrs, String _rowTitle, int rowShowNum) {
        this(context, attrs,0,_rowTitle,rowShowNum);
    }

    public DialogRowSelect(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, String _rowTitle, int rowShowNum) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        mTitle=_rowTitle;
        mRowShowNum=rowShowNum;
        initView();
    }

    public List<DialogBaseRecycleSelectModel> getList() {
        return mList;
    }


    protected void initView() {
        if (isInEditMode()) return;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.dialog_row_select, null, false);
        tvKey=(TextView) mView.findViewById(R.id.tvKey);
        tvKey.setText(mTitle);
        rvContent=(RecyclerView) mView.findViewById(R.id.rvContent);
        rvContent.setHasFixedSize(true);
       /* StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                3, StaggeredGridLayoutManager.VERTICAL);*/
        GridLayoutManager layoutManager = new GridLayoutManager(mContext,  mRowShowNum);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(mRowShowNum>1){
                    return mList.get(position).showName.length()>5? 2 : 1;
                }else {
                    return 1;
                }

            }
        });
        rvContent.setLayoutManager(layoutManager);
//        rvContent.addItemDecoration(new SpaceItemDecoration(30));


        addView(mView);
    }

    public boolean isMultiSelected() {
        return multiSelected;
    }

    public void setMultiSelected(boolean multiSelected) {
        this.multiSelected = multiSelected;
    }

    public DialogRowSelect convertData(String _dialogRowKey, final List<DialogBaseRecycleSelectModel> list){
        mDialogRowKey=_dialogRowKey;
        this.mList.addAll(list);
        return this;
    }
    public DialogRowSelect setTvKeyWidth(int dpWidth){
        if(tvKey!=null){
            tvKey.getLayoutParams().width= UiUtils.dp2px(mContext,dpWidth);
            //不能直接通过setWidth()方法设置，这样是无效的，具体的要看源码实现过程
        }

        return this;
    }

    public String getRowKey() {
        return mDialogRowKey;
    }

    @Override
    public String getRowResult() {
        //先暂时处理一下单选，后续完善多选
        for (DialogBaseRecycleSelectModel obj:mList
             ) {
            if(obj.selected)return obj.itemKey;
        }
        return "";
    }

    @Override
    public void generateUI() {
        mAdapter=new DialogBaseSelectAdapter(mContext, this.mList,multiSelected);
        rvContent.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new dispatchgrouplistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DialogBaseRecycleSelectModel model=mList.get(position);
                if(model!=null){
                    model.selected=!model.selected;
                    mAdapter.notifyDataSetChanged();
//                    mAdapter.notifyItemChanged(position);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public boolean valid() {
        return true;
    }
}
