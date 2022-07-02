package common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import leetCodeProblems.medium.ListNode;

public class CommonUtils {
	static final int COUNT = 10; 
	public static void printListValues(ListNode l) {
		String s = "";
		while (l != null) {
			s += l.val + (l.next != null ? "->" : "");
			l = l.next;
		}
		System.out.println(s);

	}

	public static ListNode addNodeToLinkedList(int val, ListNode next) {

		ListNode node = new ListNode(val, next);
		return node;
	}

	/*
	 * We traverse array from end and insert every element at the beginning of the
	 * list.
	 */
	public static ListNode createLinkedListFromArray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}

		ListNode root = null;
		for (int i = arr.length - 1; i >= 0; i--) {
			root = insert(root, arr[i]);
		}
		return root;
	}

	public static ListNode insert(ListNode root, int item) {
		ListNode temp = new ListNode(item);
		temp.next = root;
		root = temp;
		return root;
	}

	public static void display(ListNode root) {
		while (root != null) {
			System.out.print(root.val + " ");
			root = root.next;
		}

	}

	public static void main(String[] args) {
		int arr[] = { 1, 2, 3, 4, 5 };
		int n = arr.length;
		ListNode root = createLinkedListFromArray(arr);
		display(root);
	}

	public static int[][] switchOneZeros(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 1) {
					matrix[i][j] = 0;
				} else {
					matrix[i][j] = 1;
				}

			}
		}
		return matrix;
	}

	public static void print2DArray(int[][] matrix) {
		System.out.println(Arrays.deepToString(matrix).replace("[", "{").replace("]", "}"));
	}

	/*
	 * convert list<list<integer>> into 2D array using stream Api
	 */
	public static int[][] toArray(List<List<Integer>> list) {
		int[][] newGrid = list.stream().map(l -> l.stream().mapToInt(Integer::intValue).toArray())
				.toArray(int[][]::new);

		return newGrid;

	}

	// Function to insert nodes in level order
	public static TreeNode insertLevelOrderToBinaryTree(int[] arr, TreeNode root, int i) {
		// Base case for recursion
		if (i < arr.length) {
			TreeNode temp = new TreeNode(arr[i]);
			root = temp;

			// insert left child
			root.left = insertLevelOrderToBinaryTree(arr, root.left, 2 * i + 1);

			// insert right child
			root.right = insertLevelOrderToBinaryTree(arr, root.right, 2 * i + 2);
		}
		return root;
	}

	// Function to print tree nodes in InOrder fashion
	public static void printTreeNodesInOrder(TreeNode root) {
		if (root != null) {
			printTreeNodesInOrder(root.left);
			System.out.print(root.val + " ");
			printTreeNodesInOrder(root.right);
		}
	}

	// Function to print binary tree in 2D
	// It does reverse inorder traversal
	 static void print2DUtil(TreeNode root, int space) {
		// Base case
		if (root == null)
			return;

		// Increase distance between levels
		space += COUNT;

		// Process right child first
		print2DUtil(root.right, space);

		// Print current node after space
		// count
		System.out.print("\n");
		for (int i = COUNT; i < space; i++)
			System.out.print(" ");
		System.out.print(root.val + "\n");

		// Process left child
		print2DUtil(root.left, space);
	}

	// Wrapper over print2DUtil()
	public static void print2D(TreeNode root) {
		// Pass initial space count as 0
		print2DUtil(root, 0);
	}
	//Merge two arrays using stream API
	public  <T> Object[]  mergeTwoArrays(T[] arr1, T[] arr2) {
		return Stream.of(arr1, arr2).flatMap(Stream::of).toArray();
		
	}
	

}
