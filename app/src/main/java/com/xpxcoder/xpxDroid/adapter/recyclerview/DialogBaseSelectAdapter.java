package com.xpxcoder.xpxDroid.adapter.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.tools.dialogs.DialogBaseRecycleSelectModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPSoft on 2018/3/27.
 */

public class DialogBaseSelectAdapter extends RecyclerView.Adapter<DialogBaseSelectAdapter.MyViewHolder> {

    private Context mContext;
    private List<DialogBaseRecycleSelectModel> mList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private dispatchgrouplistAdapter.OnItemClickListener mOnItemClickListener;
    private boolean multiSelected;//控制是否多选

    public DialogBaseSelectAdapter(Context context, List<DialogBaseRecycleSelectModel> list, boolean _multiSelected) {
        this.mContext = context;
        this.mList = list;
        this.multiSelected = _multiSelected;
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 设置回调监听
     *
     * @param listener
     */
    public void setOnItemClickListener(dispatchgrouplistAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.dialog_recycle_item_select, null);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MyViewHolder vHolder = holder;
        DialogBaseRecycleSelectModel model = mList.get(position);
        vHolder.btn.setText(model.showName);
        if (model.selected) {
//            vHolder.btn.setBackgroundColor(Color.YELLOW);
            vHolder.btn.setTextColor(Color.WHITE);
            vHolder.btn.setBackground(mContext.getResources().getDrawable(R.drawable.dialog_select_selected));

        } else {
//            vHolder.btn.setBackgroundColor(Color.GRAY);
            vHolder.btn.setTextColor(Color.BLACK);
            vHolder.btn.setBackground(mContext.getResources().getDrawable(R.drawable.dialog_select_unselected));
        }
        vHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (mOnItemClickListener != null) {
                    if (!multiSelected) {
                        resetSelected();
                    }
                    int pos = vHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(vHolder.itemView, pos);
                }
            }
        });
    }

    private void resetSelected() {
        for (DialogBaseRecycleSelectModel obj : mList
                ) {
            obj.selected = false;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public Button btn;

        public MyViewHolder(View view) {
            super(view);
            btn = (Button) view.findViewById(R.id.btn);
        }

    }
}
