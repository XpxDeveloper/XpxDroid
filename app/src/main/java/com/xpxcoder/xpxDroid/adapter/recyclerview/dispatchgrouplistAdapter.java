package com.xpxcoder.xpxDroid.adapter.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.databinding.RecycleItemDispatchgrouplistBinding;

import java.util.ArrayList;

/**
 * Created by XPSoft on 2018/2/16.
 */

public class dispatchgrouplistAdapter  extends RecyclerView.Adapter<dispatchgrouplistAdapter.ViewHolder>{

    private ArrayList<String> mData;
    /**
     * 事件回调监听
     */
    private OnItemClickListener onItemClickListener;

    public dispatchgrouplistAdapter(ArrayList<String> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }
    /**
     * 设置回调监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_dispatchgrouplist, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // 绑定数据
//        holder.mTv.setText(mData.get(position));
        holder.getBinding().groupName.setText(mData.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, pos);
                }
                //表示此事件已经消费，不会触发单击事件
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        RecycleItemDispatchgrouplistBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mBinding = RecycleItemDispatchgrouplistBinding.bind(itemView);
//            mTv = (TextView) itemView.findViewById(R.id.tv);
        }
        public RecycleItemDispatchgrouplistBinding getBinding(){
            return mBinding;
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

}
