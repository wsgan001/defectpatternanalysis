package cn.edu.fudan.se.code.change.tree.utils;

import java.util.Map;

import cn.edu.fudan.se.code.change.tree.bean.TreeNode;

public interface ITreeNodeClone {
	public TreeNode cloneNoChildren(TreeNode codeTreeNode);
	public TreeNode clone(TreeNode codeTreeNode);
	public TreeNode cloneWholeTree(TreeNode codeTreeNode);
	public TreeNode cloneWholeTree(TreeNode codeTreeNode, Map<TreeNode, TreeNode> clonedMap);
}
