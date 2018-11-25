package com.xpsoft.xpxDroid.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.global.enumType;
import com.xpsoft.xpxDroid.models.IM.ChatMsg;

import java.util.List;

/**
 * Created by XPSoft on 2018/2/20.
 */

public class dispatchChatRoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //数据集
    public List<ChatMsg> mDatas;
    public int getItemViewType(int position){
        return mDatas.get(position).get_msgType().ordinal() ;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == enumType.MSG_TYPE.MSG_TYPE_TEXT.ordinal()){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_chatmsg_text,parent,false);
            return new TextHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    public class TextHolder extends RecyclerView.ViewHolder{

        public TextHolder(View itemView) {
            super(itemView);
        }
    }
}
