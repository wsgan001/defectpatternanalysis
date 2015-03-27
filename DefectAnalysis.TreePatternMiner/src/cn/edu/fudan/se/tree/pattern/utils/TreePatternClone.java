/**
 * 
 */
package cn.edu.fudan.se.tree.pattern.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.fudan.se.code.change.tree.bean.TreeNode;
import cn.edu.fudan.se.code.change.tree.utils.ITreeNodeClone;
import cn.edu.fudan.se.tree.pattern.bean.TreePatternInstance;
import cn.edu.fudan.se.tree.pattern.bean.TreePattern;

/**
 * @author Lotay
 *
 */
public class TreePatternClone {
	private ITreeNodeClone treeNodeClone = null;

	public TreePatternClone(ITreeNodeClone treeNodeClone) {
		if (treeNodeClone == null) {
			System.err.println("The TreeNodeClone is null.");
		}
		this.treeNodeClone = treeNodeClone;
	}

	public TreePattern clone(TreePattern treePattern) {
		if (treePattern == null) {
			return null;
		}
		TreePattern clonePattern = new TreePattern();

		Map<TreeNode, List<TreePatternInstance>> patternInstances = treePattern
				.getPatternInstances();
		for (TreeNode patternTree : treePattern.getTreePatterns()) {
			Map<TreeNode, TreeNode> clonedNodesMap = new HashMap<TreeNode, TreeNode>();
			TreeNode clonedPatternNode = treeNodeClone.cloneWholeTree(
					patternTree, clonedNodesMap);
			if (clonedPatternNode != null) {
				clonePattern.addTreePattern(clonedPatternNode);
			} else {
				System.err.println("TreePatternClone: Clone Error 1.");
			}

			for (TreeNode patternInstance : patternInstances.keySet()) {
				List<TreePatternInstance> treePatternInstances = patternInstances
						.get(patternInstance);
				List<TreePatternInstance> cloneTreePatternInstances = new ArrayList<TreePatternInstance>();
				for (TreePatternInstance treePatternInstance : treePatternInstances) {
					Map<TreeNode, Map<TreeNode, TreeNode>> matchedInstanceNodes = treePatternInstance
							.getMatchedNodes();
					TreePatternInstance cloneTreePatternInstance = new TreePatternInstance();
					Map<TreeNode, TreeNode> orginalNodeMap = matchedInstanceNodes
							.get(patternTree);
					Map<TreeNode, TreeNode> cloneMatchedNodes = new HashMap<TreeNode, TreeNode>();
					for (TreeNode clonePNode : clonedNodesMap.keySet()) {
						TreeNode clonedTreeNode = clonedNodesMap.get(clonePNode);
						if (clonedTreeNode!=null) {
							cloneMatchedNodes.put(clonePNode, orginalNodeMap
									.get(clonedTreeNode));
						}else {
							System.err.println("TreePatternClone: Clone Error 2.");
						}
					}
					cloneTreePatternInstance.addMatchedNode(clonedPatternNode,
							cloneMatchedNodes);
					cloneTreePatternInstances.add(cloneTreePatternInstance);
				}
				clonePattern.addPatternInstances(patternInstance,
						cloneTreePatternInstances);
			}

		}

		return clonePattern;
	}

}