package leetCodeProblems.easy;

import leetCodeProblems.medium.ListNode;

/*
 * Merge two sorted linked lists and return it as a new list. 
 * The new list should be made by splicing together the nodes of the first two lists.
 * 
 * Example:
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedLists {
	/*
	 * Time complexity : O(n + m)
	 * 
	 * Because exactly one of l1 and l2 is incremented on each loop iteration, the
	 * while loop runs for a number of iterations equal to the sum of the lengths of
	 * the two lists. All other work is constant, so the overall complexity is
	 * linear.
	 * 
	 * Space complexity : O(1)
	 * 
	 * The iterative approach only allocates a few pointers, so it has a constant
	 * overall memory footprint.
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 != null && l2 == null) {
			return l1;
		} else if (l1 == null && l2 != null) {
			return l2;
		}

		ListNode preHead = new ListNode(0);
		ListNode current = preHead;

		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				current.next = l1;
				l1 = l1.next;
			} else {
				current.next = l2;
				l2 = l2.next;
			}

			current = current.next;
		}
		if (l1 != null) {
			current.next = l1;
		} else if (l2 != null) {
			current.next = l2;
		}

		return preHead.next;

	}

	public static void main(String[] args) {
		MergeTwoSortedLists mtl = new MergeTwoSortedLists();
		ListNode l1 = new ListNode(1);
		ListNode l1n2 = new ListNode(2);
		ListNode l1n3 = new ListNode(4);
		l1.next = l1n2;
		l1n2.next = l1n3;
		printListValues(l1);
//===================================
		ListNode l2 = new ListNode(1);
		ListNode l2n2 = new ListNode(3);
		ListNode l2n3 = new ListNode(4);

		l2.next = l2n2;
		l2n2.next = l2n3;

		printListValues(l2);
		printListValues(mtl.mergeTwoLists(l1, l2));
		printListValues(mtl.mergeTwoLists(null, null));
	}

	static void printListValues(ListNode l) {
		String s = "";
		while (l != null) {
			s += l.val + (l.next != null ? "->" : "");
			l = l.next;
		}
		System.out.println(s);

	}

}
