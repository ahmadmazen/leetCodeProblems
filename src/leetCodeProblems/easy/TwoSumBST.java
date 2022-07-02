package leetCodeProblems.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//Two sum using Binary search tree
public class TwoSumBST {
	// Root of BST
	Node root;

	// Constructor
	TwoSumBST() {
		root = null;
	}

	class Node {
		int value;
		Node left;
		Node right;

		Node(int x) {
			value = x;
		}
	}

	/*
	 * Given a Binary Search Tree and a target number, return true if there exist
	 * two elements in the BST such that their sum is equal to the given target.
	 * 
	 * Example 1: Complexity Analysis
	 * 
	 * Time complexity : O(n). The entire tree is traversed only once in the worst
	 * case. Here, nn refers to the number of nodes in the given tree.
	 * 
	 * Space complexity : O(n). The size of the setset can grow upto nn in the worst
	 * case.
	 */
	//// using hash table
	public boolean findTarget(Node root, int k) {
		Set<Integer> table = new HashSet<>();
		return traverse(root, table, k);

	}

	public boolean traverse(Node root, Set<Integer> table, int k) {
		Node current = root;
		if (current == null) {
			return false;
		}
		int comp = k - current.value;
		if (table.contains(comp)) {
			return true;
		}
		table.add(current.value);
		return traverse(current.left, table, k) || traverse(current.right, table, k);

	}

	// using BFS and hash-set with the help of Queue..
	/*
	 * Complexity Analysis
	 * 
	 * Time complexity : O(n). We need to traverse over the whole tree once in
	 * the worst case. Here, n refers to the number of nodes in the given tree.
	 * 
	 * Space complexity :O(n). The size of the sets can grow at most up to n.
	 */
	public boolean findTarget_BFS(Node root, int k) {
		Set<Integer> table = new HashSet<>();
		java.util.Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			if (queue.peek() != null) {
				Node node = queue.remove();
				if (table.contains(k - node.value)) {
					return true;
				}
				table.add(node.value);
				queue.add(node.right);
				queue.add(node.left);
			} else {
				queue.remove();
			}
		}
		return false;
	}
	//using BST inOrder that sort the values ascending then using two pointers to check if we can add up to 
	//target
	/*
	 * same Complexity analysis
	 */
	public boolean findTarget_BTS(Node root, int k) {
		List<Integer> l = new ArrayList<>();
		inOrderToList(l, root);
		int left = 0;
		int right = l.size() - 1;
		while(left < right) {
			int sum = left + right;
			if(sum == k) {
				return true;
			}
			if(sum < k) {
				left++;
			}else {
				right--;
			}
		}
		
		return false;
	}
	public void inOrderToList(List l, Node root) {
		Node current = root;
		if(current != null) {
			inOrderToList(l, current.left);
			l.add(current.value);
			inOrderToList(l, current.right);
			l.add(current.value);
		}
		
		
	}

	public Node search(Node root, int target) {
		// base case
		if (root == null || root.value == target) {
			return root;
		}
		if (target > root.value) {
			return search(root.right, target);
		} else {
			return search(root.left, target);
		}
	}

	private Node insertNode(Node root, int value) {
		if (root == null) {
			root = new Node(value);
			return root;

		}
		if (root.value < value) {
			root.right = insertNode(root.right, value);
		} else if (root.value > value) {
			root.left = insertNode(root.left, value);
		}

		return root;

	}

	public void insert(int value) {
		root = insertNode(root, value);
	}

	private void inOrderValue(Node root) {
		if (root != null) {
			inOrderValue(root.left);
			System.out.println(root.value);
			inOrderValue(root.right);

		}

	}

	public void inOrder() {
		inOrderValue(root);
	}

	public static void main(String[] args) {
		TwoSumBST tree = new TwoSumBST();
		/*
		 * tree.insert(50); tree.insert(30); tree.insert(20); tree.insert(40);
		 * tree.insert(70); tree.insert(60); tree.insert(80); tree.inOrder();
		 */

		tree.insert(5);
		tree.insert(3);
		tree.insert(2);
		tree.insert(4);
		tree.insert(6);
		tree.insert(7);

		// tree.inOrder();
//		System.out.println(tree.findTarget(tree.root, 9));
//		System.out.println(tree.findTarget(tree.root, 28));
//		System.out.println(tree.findTarget(tree.root, 12));
//		System.out.println(tree.findTarget(tree.root, 13));
//		System.out.println(tree.findTarget_BFS(tree.root, 28));
		System.out.println(tree.findTarget_BTS(tree.root, 28));
		System.out.println(tree.findTarget_BTS(tree.root, 13));

	}

}
