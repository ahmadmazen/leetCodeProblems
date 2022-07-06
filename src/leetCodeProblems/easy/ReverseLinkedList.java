 package leetCodeProblems.easy;

import leetCodeProblems.medium.ListNode;

/*
 * Reverse a singly linked list.
 * 
 * Example:
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {
	/*
	 * Time complexity : O(n). Assume that n is the list's length, the time
	 * complexity is O(n)
	 * 
	 * Space complexity :O(1).
	 */
	public ListNode reverseList(ListNode head) {

		if (head == null) {
			return null;
		}

		ListNode previous = null;
		ListNode current = head;

		while (current != null) {
			ListNode tempNext = current.next;
			current.next = previous;
			previous = current;
			current = tempNext;
		}

		return previous;
	}

	/*
	 * Time complexity : O(n). Assume that nn is the list's length, the time
	 * complexity is O(n).
	 * 
	 * Space complexity : O(n). The extra space comes from implicit stack space
	 * due to recursion. The recursion could go up to n levels deep.
	 */
	public ListNode reverseList_recursively(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode p = reverseList_recursively(head.next);
	    head.next.next = head;
	    head.next = null;
	    return p;
	}

	public static void main(String[] args) {
		ReverseLinkedList rl = new ReverseLinkedList();
		ListNode l1 = new ListNode(1);
		ListNode l1n2 = new ListNode(2);
		ListNode l1n3 = new ListNode(4);
		l1.next = l1n2;
		l1n2.next = l1n3;
		// printListValues(l1);
		printListValues(rl.reverseList(l1));
	//	printListValues(rl.reverseList_recursively(l1));
		
		String s= "apple";
		System.out.println(s.substring(0, 1));
	}

	public static void printListValues(ListNode l) {
		String s = "";
		while (l != null) {
			s += l.val + (l.next != null ? "->" : "");
			l = l.next;
		}
		System.out.println(s);

	}

}
