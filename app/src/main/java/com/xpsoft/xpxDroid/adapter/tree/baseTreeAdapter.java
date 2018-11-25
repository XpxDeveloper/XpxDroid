package com.xpsoft.xpxDroid.adapter.tree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPSoft on 2018/2/9.
 * 这部分来源于张鸿洋在网上分享的实现方法
 */

public abstract class baseTreeAdapter extends BaseAdapter {

    protected Context mContext;
    /**
     * 存储所有可见的Node
     */
    protected List<baseTreeNode> mNodes;
    protected LayoutInflater mInflater;
    /**
     * 存储所有的Node
     */
    protected List<baseTreeNode> mAllNodes;

    /**
     * 点击的回调接口
     */
    private OnTreeNodeClickListener onTreeNodeClickListener;

    public interface OnTreeNodeClickListener {
        /**
         * 处理node click事件
         *
         * @param node
         * @param position
         */
        void onClick(baseTreeNode node, int position);

        /**
         * 处理checkbox选择改变事件
         *
         * @param node
         * @param position
         * @param checkedNodes
         */
        void onCheckChange(baseTreeNode node, int position, List<baseTreeNode> checkedNodes);
    }

    public void setOnTreeNodeClickListener(
            OnTreeNodeClickListener onTreeNodeClickListener) {
        this.onTreeNodeClickListener = onTreeNodeClickListener;
    }

    /**
     * @param mTree
     * @param context
     * @param datas
     * @param defaultExpandLevel 默认展开几级树
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public baseTreeAdapter(ListView mTree, Context context, List<baseTreeNode> datas,
                               int defaultExpandLevel, boolean isHide)
            throws IllegalArgumentException, IllegalAccessException {
        mContext = context;
        /**
         * 对所有的Node进行排序
         */
        mAllNodes = baseTreeHelper
                .getSortedNodes(datas, defaultExpandLevel, isHide);
        /**
         * 过滤出可见的Node
         */
        mNodes = baseTreeHelper.filterVisibleNode(mAllNodes);
        mInflater = LayoutInflater.from(context);

        /**
         * 设置节点点击时，可以展开以及关闭；并且将ItemClick事件继续往外公布
         */
        mTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                expandOrCollapse(position);

                if (onTreeNodeClickListener != null) {
                    onTreeNodeClickListener.onClick(mNodes.get(position),
                            position);
                }
            }

        });

    }

    /**
     * 相应ListView的点击事件 展开或关闭某节点
     *
     * @param position
     */
    public void expandOrCollapse(int position) {
        baseTreeNode n = mNodes.get(position);

        if (n != null)// 排除传入参数错误异常
        {
            if (!n.isLeaf()) {
                n.setExpand(!n.isExpand());
                mNodes = baseTreeHelper.filterVisibleNode(mAllNodes);
                notifyDataSetChanged();// 刷新视图
            }
        }
    }

    @Override
    public int getCount() {
        return mNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return mNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final baseTreeNode node = mNodes.get(position);

        convertView = getConvertView(node, position, convertView, parent);
        // 设置内边距
        convertView.setPadding(node.getLevel() * 40, 3, 3, 3);
        if (!node.isHideChecked()) {//先暂时设置只允许叶子节点的checkbox可以点击
            //获取各个节点所在的父布局
            RelativeLayout myView = (RelativeLayout) convertView;
            //父布局下的CheckBox
            CheckBox cb = (CheckBox) myView.getChildAt(0);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
//					updateThreeStateForOutSide(node,position,isChecked);
                    baseTreeHelper.setNodeChecked(node, isChecked);
                    List<baseTreeNode> checkedNodes = new ArrayList<baseTreeNode>();
                    for (baseTreeNode n : mAllNodes) {
                        if (n.isChecked()) {
                            checkedNodes.add(n);
                        }
                    }

                    onTreeNodeClickListener.onCheckChange(node, position, checkedNodes);
                    notifyDataSetChanged();
                }

            });
        }

        return convertView;
    }


    public abstract View getConvertView(baseTreeNode node, int position,
                                        View convertView, ViewGroup parent);



}
