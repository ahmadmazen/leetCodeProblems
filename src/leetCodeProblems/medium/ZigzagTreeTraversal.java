package leetCodeProblems.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import common.CommonUtils;
import common.TreeNode;

public class ZigzagTreeTraversal {
	/*
	 * Time Complexity: \mathcal{O}(N)O(N), where NN is the number of nodes in the
	 * tree.
	 * 
	 * We visit each node once and only once.
	 * 
	 * In addition, the insertion operation on either end of the deque takes a
	 * constant time, rather than using the array/list data structure where the
	 * inserting at the head could take the \mathcal{O}(K)O(K) time where KK is the
	 * length of the list.
	 * 
	 * Space Complexity: O(N) where N is the number of nodes in the
	 * tree.
	 * 
	 * The main memory consumption of the algorithm is the node_queue that we use
	 * for the loop, apart from the array that we use to keep the final output.
	 * 
	 * As one can see, at any given moment, the node_queue would hold the nodes that
	 * are at most across two levels. Therefore, at most, the size of the queue
	 * would be no more than 2 L2⋅, assuming LL is the maximum number of
	 * nodes that might reside on the same level. Since we have a binary tree, the
	 * level that contains the most nodes could occur to consist all the leave nodes
	 * in a full binary tree, which is roughly L = \frac{N}{2}L= 2 N ​ . As a
	 * result, we have the space complexity of 2 \cdot \frac{N}{2} = N2⋅ 2 N ​ =N in
	 * the worst case.
	 */
	public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		if (root == null) {
			return new ArrayList<List<Integer>>();
		}

		List<List<Integer>> result = new ArrayList<List<Integer>>();

		LinkedList<TreeNode> node_queue = new LinkedList<TreeNode>();
		// add the root element
		node_queue.addLast(root);
		node_queue.addLast(null);// delimeter to mark between the levels in the tree

		boolean is_left_order = true;
		// store the value of each level in deque data structure which allow us to add
		// to the end or to the first
		LinkedList<Integer> level_list = new LinkedList<Integer>();

		while (node_queue.size() > 0) {
			TreeNode curr_node = node_queue.pollFirst();
			if (curr_node != null) {

				if (is_left_order) { // then add to the tail of the dequeue
					level_list.addLast(curr_node.val);
				} else {
					level_list.addFirst(curr_node.val);
				}
				if (curr_node.left != null) {
					node_queue.addLast(curr_node.left);
				}
				if (curr_node.right != null) {
					node_queue.addLast(curr_node.right);
				}

			} else { // means this is the delimiter we finished one level
				result.add(level_list);
				level_list = new LinkedList<Integer>();
				if (node_queue.size() > 0) {
					node_queue.addLast(null); // delimiter
				}
				is_left_order = !is_left_order; // reverse the orders

			}

		}

		return result;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		TreeNode rootLeft = new TreeNode(9);
		root = CommonUtils.insertLevelOrderToBinaryTree(new int[] { 3, 9, 20, 15, 7 }, rootLeft, 0);
		//CommonUtils.printTreeNodesInOrder(root);
		CommonUtils.print2D(root);
		// root.left(rootLeft);
	}

}
