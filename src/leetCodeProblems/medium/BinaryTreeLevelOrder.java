package leetCodeProblems.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import common.CommonUtils;
import common.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class BinaryTreeLevelOrder {

	/* solved it right one shot using BFS with queue
	 * Time complexity : O(N) since each node is processed exactly once.
	 * Space complexity : O(N) to keep the output structure which
	 * contains N node values.
	 * 
	 */
	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		// BFS
		while (!queue.isEmpty()) {
			int queueSize = queue.size();
			List<Integer> levelList = new ArrayList<Integer>();
			for (int i = 0; i < queueSize; i++) {

				TreeNode currentNode = queue.poll();

				if (currentNode.left != null) {
					queue.add(currentNode.left);
				}
				if (currentNode.right != null) {
					queue.add(currentNode.right);
				}
				levelList.add(currentNode.val);
			}
			result.add(levelList);

		}
		return result;

	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		// TreeNode r = CommonUtils.insertLevelOrderToBinaryTree(new int[] {3,
		// 9,20,0,0,15,7}, root, 1);
		// CommonUtils.print2D(root);
		levelOrder(root).forEach(elm -> System.out.println(elm));
	}
	
}
