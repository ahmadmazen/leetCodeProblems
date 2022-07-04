package leetCodeProblems.medium;

import common.TreeNode;

/*
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Example 1:
 * Input: root = [2,1,3]
 * Output: true
 * Example 2:
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 * 
 * 
 * */
public class IsValidBST {
	// recursive approach Depth first search
	// Time complexity O(n) in worst case O(n) because we might visit all the nodes
	// of the tree,
	// space complexity O(n) because of the stack used with recursion

	public boolean isValidBST(TreeNode root) {

		if (root == null) {
			return true;
		}

		return isNodeValInRange(root, Long.MIN_VALUE, Long.MAX_VALUE);

	}

	private boolean isNodeValInRange(TreeNode node, long min, long max) {
		if (node == null) {
			return true;
		} else if (node.val <= min || node.val >= max) {
			return false;
		}
		return isNodeValInRange(node.left, min, node.val) && isNodeValInRange(node.right, node.val, max);
	}
	
	

}
