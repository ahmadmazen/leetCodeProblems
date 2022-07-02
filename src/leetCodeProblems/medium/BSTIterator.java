package leetCodeProblems.medium;

import java.util.Stack;

import common.TreeNode;

/*
 * Good article
 * https://leetcode.com/problems/binary-search-tree-iterator/solution/
 * 
 * Approach one : Flattening BST using array
 * Complexity analysis:
 * Time complexity : O(N)O(N) is the time taken by the constructor for the iterator. 
 * The problem statement only asks us to analyze the complexity of the two functions, 
 * however, when implementing a class, it's important to also note the time it takes to initialize a new object of the class
 * and in this case it would be linear in terms of the number of nodes in the BST. 
 * In addition to the space occupied by the new array we initialized, 
 * the recursion stack for the inorder traversal also occupies space but that is limited to O(h) 
 * where h is the height of the tree.
 * --> next() would take O(1)
 * --> hasNext() would take O(1)
 * 
 * Space complexity : O(N) since we create a new array to contain all the nodes of the BST. 
 * This doesn't comply with the requirement specified in the problem statement 
 * that the maximum space complexity of either of the functions should be O(h)
 *  where h is the height of the tree and for a well balanced BST, the height is usually logN. 
 *  So, we get great time complexities but we had to compromise on the space. 
 *  Note that the new array is used for both the function calls and hence the space complexity for both the calls is O(N)
 *  
 *  
 *  Approach 2: Controlled Recursion
 * 
 */
public class BSTIterator {
	/*
	 * Approach one List<Integer> flattenedBST; int index = -1;
	 */
	// approach two
	Stack<TreeNode> bstStack;

	public BSTIterator(TreeNode root) {

		/*
		 * approach one this.flattenedBST = new ArrayList<Integer>(); 
		 * _inorder(root);
		 */
		// approach two
		this.bstStack = new Stack<TreeNode>();
		this._leftmostInorder(root);
	}

	private void _leftmostInorder(TreeNode root) {

		while (root != null) {
			this.bstStack.push(root);
			root = root.left;
		}
	}

	/*
	 * approach one 
	 * private void _inorder(TreeNode root) {
	 *  if(root == null) {
	 * return; 
	 * } 
	 * _inorder(root.left); 
	 * this.flattenedBST.add(root.val);
	 * _inorder(root.right); }
	 */

	/** @return the next smallest number */
	public int next() {
		/* approach one
		return this.flattenedBST.get(++index);
		*/
		//approach two
		TreeNode topmostNode = this.bstStack.pop();
		if(topmostNode.right != null) {
			this._leftmostInorder(topmostNode.right);
		}
	    return topmostNode.val;
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		/* approach one
		return index + 1 < this.flattenedBST.size();
		*/
		//approach two
		return this.bstStack.size() > 0;
	}

	public static void main(String[] args) {
		/*
		 * BSTIterator iterator = new BSTIterator(root); iterator.next(); // return 3
		 * iterator.next(); // return 7 iterator.hasNext(); // return true
		 * iterator.next(); // return 9 iterator.hasNext(); // return true
		 * iterator.next(); // return 15 iterator.hasNext(); // return true
		 * iterator.next(); // return 20 iterator.hasNext(); // return false
		 */
		TreeNode rootTest = new TreeNode(7);
		rootTest.setLeft(new TreeNode(3));
		rootTest.setRight(new TreeNode(15));
		rootTest.getRight().setLeft(new TreeNode(9));
		rootTest.getRight().setRight(new TreeNode(20));

		BSTIterator iterator = new BSTIterator(rootTest);
		// iterator.flattenedBST.forEach(n-> System.out.println(n));
		System.out.println(iterator.next()); // return 3
		System.out.println(iterator.next()); // return 7
		System.out.println(iterator.hasNext()); // return true
		System.out.println(iterator.next()); // return 9
		System.out.println(iterator.hasNext()); // return true
		System.out.println(iterator.next()); // return 15
		System.out.println(iterator.hasNext()); // return true
		System.out.println(iterator.next()); // return 20
		System.out.println(iterator.hasNext()); // return false

	}
}

/**
 * Your BSTIterator object will be instantiated and called as such: BSTIterator
 * obj = new BSTIterator(root); int param_1 = obj.next(); boolean param_2 =
 * obj.hasNext();
 */