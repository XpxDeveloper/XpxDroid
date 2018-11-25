package com.xpsoft.xpxDroid.adapter.tree;

/**
 * Created by XPSoft on 2018/2/20.
 */

public class DemoTreeNode extends baseTreeNode {
    /**
     * 节点选中的状态；0，未选中；1，全选中；2，部分选中
     */
    private int ThreeState = 0;
    private String state;
    public boolean leaf;//是不是最后的叶子
    public String desc;

    public DemoTreeNode(){
        super();
    }

    public int getThreeState() {
        return ThreeState;
    }

    public void setThreeState(int threeState) {
        ThreeState = threeState;
    }

    /**
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state 要设置的 state
     */
    public void setState(String state) {
        this.state = state;
    }
}
