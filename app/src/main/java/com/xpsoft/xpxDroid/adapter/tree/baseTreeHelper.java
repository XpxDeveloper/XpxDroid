package com.xpsoft.xpxDroid.adapter.tree;

import com.xpsoft.xpxDroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 这部分来源于张鸿洋在网上分享的实现方法
 */
public class baseTreeHelper {

	/**
	 * 根据所有节点获取可见节点
	 * 
	 * @param allNodes
	 * @return
	 */
	public static List<baseTreeNode> filterVisibleNode(List<baseTreeNode> allNodes) {
		List<baseTreeNode> visibleNodes = new ArrayList<baseTreeNode>();
		for (baseTreeNode node : allNodes) {
			// 如果为根节点，或者上层目录为展开状态
			if (node.isRoot() || node.isParentExpand()) {
				setNodeIcon(node);
				visibleNodes.add(node);
			}
		}
		return visibleNodes;
	}

	/**
	 * 获取排序的所有节点
	 * 
	 * @param datas
	 * @param defaultExpandLevel
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <T> List<baseTreeNode> getSortedNodes(List<baseTreeNode> datas,
                                                int defaultExpandLevel, boolean isHide)
			throws IllegalAccessException, IllegalArgumentException {
		List<baseTreeNode> sortedNodes = new ArrayList<baseTreeNode>();
		// 将用户数据转化为List<Node>
		List<baseTreeNode> nodes = convertData2Nodes(datas, isHide);
		// 拿到根节点
		List<baseTreeNode> rootNodes = getRootNodes(nodes);
		// 排序以及设置Node间关系
		for (baseTreeNode node : rootNodes) {
			addNode(sortedNodes, node, defaultExpandLevel, 1);
		}
		return sortedNodes;
	}

	/**
	 * 把一个节点上的所有的内容都挂上去
	 */
	private static void addNode(List<baseTreeNode> nodes, baseTreeNode node,
                                int defaultExpandLeval, int currentLevel) {

		nodes.add(node);
		if (defaultExpandLeval >= currentLevel) {
			node.setExpand(true);
		}

		if (node.isLeaf())
			return;
		for (int i = 0; i < node.getChildrenNodes().size(); i++) {
			addNode(nodes, node.getChildrenNodes().get(i), defaultExpandLeval,
					currentLevel + 1);
		}
	}

	/**
	 * 获取所有的根节点
	 * 
	 * @param nodes
	 * @return
	 */
	public static List<baseTreeNode> getRootNodes(List<baseTreeNode> nodes) {
		List<baseTreeNode> rootNodes = new ArrayList<baseTreeNode>();
		for (baseTreeNode node : nodes) {
			if (node.isRoot()) {
				rootNodes.add(node);
			}
		}

		return rootNodes;
	}

	/**
	 * 将泛型datas转换为node
	 * 
	 * @param datas
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <T> List<baseTreeNode> convertData2Nodes(List<baseTreeNode> datas, boolean isHide)
			throws IllegalAccessException, IllegalArgumentException {
		List<baseTreeNode> nodes = new ArrayList<baseTreeNode>();
		baseTreeNode node = null;


		/**
		 * 比较nodes中的所有节点，分别添加children和parent
		 */
		for (int i = 0; i < nodes.size(); i++) {
			baseTreeNode n = nodes.get(i);
			for (int j = i + 1; j < nodes.size(); j++) {
				baseTreeNode m = nodes.get(j);
				if (n.getId() == m.getpId()) {
					n.getChildrenNodes().add(m);
					m.setParent(n);
				} else if (n.getpId() == m.getId()) {
					n.setParent(m);
					m.getChildrenNodes().add(n);
				}
			}
		}

		for (baseTreeNode n : nodes) {
			setNodeIcon(n);
		}
		return nodes;
	}

	/**
	 * 设置打开，关闭icon
	 * 
	 * @param node
	 */
	public static void setNodeIcon(baseTreeNode node) {
		if (node.getChildrenNodes().size() > 0 && node.isExpand()) {
			node.setIcon(R.drawable. btn_down);
		} else if (node.getChildrenNodes().size() > 0 && !node.isExpand()) {
			node.setIcon(R.drawable.btn_next);
		} else
			node.setIcon(-1);
	}

	public static void setNodeChecked(baseTreeNode node, boolean isChecked) {
		// 自己设置是否选择
		node.setChecked(isChecked);
		if (isChecked) {
			((DemoTreeNode)node).setThreeState(1);
		} else {
			((DemoTreeNode)node).setThreeState(0);
		}
		/**
		 * 非叶子节点,子节点处理
		 */
		setChildrenNodeChecked(node, isChecked);
		/** 父节点处理 */
//		setParentNodeChecked(node);
		setParentNodeCheckedState(node);

	}

	/**
	 * 非叶子节点,子节点处理
	 */
	private static void setChildrenNodeChecked(baseTreeNode node, boolean isChecked) {
		node.setChecked(isChecked);
		if (isChecked) {
			((DemoTreeNode)node).setThreeState(1);
		} else {
			((DemoTreeNode)node).setThreeState(0);
		}
		if (!node.isLeaf()) {
			for (baseTreeNode n : node.getChildrenNodes()) {
				// 所有子节点设置是否选择
				setChildrenNodeChecked(n, isChecked);
			}
		}
	}

	/**
	 * 设置父节点选择
	 * 
	 * @param node
	 */
	private static void setParentNodeChecked(baseTreeNode node) {

		/** 非根节点 */
		if (!node.isRoot()) {
			baseTreeNode rootNode = node.getParent();
			boolean isAllChecked = true;
			for (baseTreeNode n : rootNode.getChildrenNodes()) {
				if (!n.isChecked()) {
					isAllChecked = false;
					break;
				}
			}

			if (isAllChecked) {
				rootNode.setChecked(true);
			} else {
				rootNode.setChecked(false);
			}
			setParentNodeChecked(rootNode);
		}
	}

	/*
    * add by xpx
    * */
	public static void setParentNodeCheckedState(baseTreeNode node) {
		int iCheckCnt = 0;
		for (baseTreeNode n : node.getChildrenNodes()) {
			if (((DemoTreeNode)n).getThreeState() > 0) {
				iCheckCnt++;
			}
		}
		if (iCheckCnt < 1) {
			((DemoTreeNode)node).setThreeState(0);//0未选，1全选，2半选
			node.setChecked(false);
		} else if (iCheckCnt >= 1 && iCheckCnt < node.getChildrenNodes().size()) {
			((DemoTreeNode)node).setThreeState(2);//0未选，1全选，2半选
			node.setChecked(true);
		} else {
			((DemoTreeNode)node).setThreeState(1);//0未选，1全选，2半选
			node.setChecked(true);
		}

		/** 非根节点 */
		if (!node.isRoot()) {
			setParentNodeCheckedState(node.getParent());
		}
	}

}
