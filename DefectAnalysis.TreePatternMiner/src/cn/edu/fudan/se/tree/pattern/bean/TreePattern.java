/**
 * 
 */
package cn.edu.fudan.se.tree.pattern.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.fudan.se.code.change.tree.bean.CodeTreeNode;
import cn.edu.fudan.se.code.change.tree.bean.TreeNode;
import cn.edu.fudan.se.tree.pattern.test.TreeNodeTest;

/**
 * @author Lotay
 *
 */
public class TreePattern {
	/**
	 * The treePattern is the pattern, which is a list of TreeNode.
	 * 
	 * The patternInstances is the instances containing the treePattern, the key
	 * of patternInstances is the Tree instance, the value is a list of matched
	 * node for the instance, each one is PatternInstanceNode.
	 * */
	private List<TreeNode> treePatterns = new ArrayList<TreeNode>();
	private Map<TreeNode, List<TreePatternInstance>> patternInstances = new HashMap<TreeNode, List<TreePatternInstance>>();

	public List<TreeNode> getTreePatterns() {
		return treePatterns;
	}

	public void setTreePatterns(List<TreeNode> treePatterns) {
		this.treePatterns = treePatterns;
	}

	public Map<TreeNode, List<TreePatternInstance>> getPatternInstances() {
		return patternInstances;
	}

	public void setPatternInstances(
			Map<TreeNode, List<TreePatternInstance>> patternInstances) {
		this.patternInstances = patternInstances;
	}

	public void addTreePattern(TreeNode patternNode) {
		this.treePatterns.add(patternNode);
	}

	public void addTreePatterns(Collection<TreeNode> patternNodes) {
		for (TreeNode patternNode : patternNodes) {
			this.addTreePattern(patternNode);
		}
	}

	public void addPatternInstances(TreeNode instanceTree,
			List<TreePatternInstance> treePatternInstances) {
		this.patternInstances.put(instanceTree, treePatternInstances);
	}
	
	public int getFrequency() {
		return this.patternInstances.size();
	}

	public void addPatternInstance(TreeNode instanceTree,
			TreePatternInstance treePatternInstance) {
		if (instanceTree != null) {
			List<TreePatternInstance> instanceNodes = this.patternInstances
					.get(instanceTree);
			if (instanceNodes == null) {
				instanceNodes = new ArrayList<TreePatternInstance>();
				this.patternInstances.put(instanceTree, instanceNodes);
			}
			instanceNodes.add(treePatternInstance);
		}
	}

	@Override
	public String toString() {
		StringBuilder resultString = new StringBuilder("treePatterns:\n");
		for (TreeNode treeNode : treePatterns) {
			if (treeNode instanceof TreeNodeTest) {
				resultString.append(((TreeNodeTest) treeNode).toWholeString()+"\n");
			}else if (treeNode instanceof CodeTreeNode){
				resultString.append(((CodeTreeNode) treeNode).toWholeString()+"\n");
			}
		}
		resultString.append("patternInstance:\n");
		for (TreeNode treeNode : patternInstances.keySet()) {
			resultString.append(treeNode+"\n");
			for (TreePatternInstance instanceNode : patternInstances.get(treeNode)) {
				resultString.append("\t"+instanceNode+"\n");
			}
		}
		return  resultString.toString()+ "\n\n";
	}
}
