package leetCodeProblems.linkedlist;

import leetCodeProblems.medium.ListNode;

public class AddTwoNumbers {
	/*
	 * You are given two non-empty linked lists representing two non-negative
	 * integers. The digits are stored in reverse order and each of their nodes
	 * contain a single digit. Add the two numbers and return it as a linked list.
	 * 
	 * You may assume the two numbers do not contain any leading zero, except the
	 * number 0 itself.
	 * 
	 * Example:
	 * 
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8 Explanation: 342 +
	 * 465 = 807.
	 */
     //https://leetcode.com/problems/add-two-numbers/solution/
	/*
	 * Complexity Analysis
	 * 
	 * Time complexity : O(max(m,n)). Assume that m and n represents
	 * the length of l1 and l2 respectively, the algorithm above iterates at
	 * most max(m, n) times.
	 * 
	 * Space complexity : O(max(m,n)). The length of the new list is at
	 * most max(m,n) + 1.
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

		ListNode dummyHead = new ListNode(0);
		ListNode p = l1, q = l2, curr = dummyHead;
		int carry = 0;
		while (p != null || q != null) {
			int x = (p != null) ? p.val : 0;
			int y = (q != null) ? q.val : 0;
			int sum = carry + x + y;
			carry = sum / 10;
			curr.next = new ListNode(sum % 10);
			curr = curr.next;
			if (p != null)
				p = p.next;
			if (q != null)
				q = q.next;
		}
		if (carry > 0) {
			curr.next = new ListNode(carry);
		}
		return dummyHead.next;
	}

	public static void main(String[] args) {
		AddTwoNumbers atn = new AddTwoNumbers();
		ListNode l1 = new ListNode(2);
		ListNode l1n2 = new ListNode(4);
		ListNode l1n3 = new ListNode(3);
		l1.next = l1n2;
		l1n2.next = l1n3;
//		printListValues(l1);
//===================================
		ListNode l2 = new ListNode(5);
		ListNode l2n2 = new ListNode(6);
		ListNode l2n3 = new ListNode(4);

		l2.next = l2n2;
		l2n2.next = l2n3;

		// printListValues(l2);
		printListValues(atn.addTwoNumbers(l1, l2));
	}
	static void printListValues(ListNode l) {
	while(l != null) {
			System.out.println(l.val);
			l = l.next;
		}

	}
}
