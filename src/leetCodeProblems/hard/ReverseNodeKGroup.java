package leetCodeProblems.hard;

import common.CommonUtils;
import leetCodeProblems.medium.ListNode;

/*
	 * Given a linked list, reverse the nodes of a linked list k at a time and
	 * return its modified list.
	 * 
	 * k is a positive integer and is less than or equal to the length of the linked
	 * list. If the number of nodes is not a multiple of k then left-out nodes in
	 * the end should remain as it is.
	 * 
	 * Example:
	 * 
	 * Given this linked list: 1->2->3->4->5
	 * 
	 * For k = 2, you should return: 2->1->4->3->5
	 * 
	 * For k = 3, you should return: 3->2->1->4->5
	 * 
	 * Note:
	 * 
	 * Only constant extra memory is allowed. You may not alter the values in the
	 * list's nodes, only nodes itself may be changed.
	 */
public class ReverseNodeKGroup {
	/*
	 * Time Complexity: O(N) since we process each node exactly twice. Once when
	 * we are counting the number of nodes in each recursive call, and then once
	 * when we are actually reversing the sub-list. Space Complexity: O(1)O(1).
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode new_head = null;
		ListNode ptr = head;

		ListNode ktail = null;

		while (ptr != null) {

			// start counting the k nodes
			// find the next head of the next k nodes
			int count = 0;

			while (count < k && ptr != null) {
				ptr = ptr.next; // will be moved to the head of the next k nodes at the final iterations
				count += 1;
			}
			// then we found k group, start reversing them
			if (count == k) {

				ListNode revHead = reverseLinkedList(head, k);

				if (new_head == null) {
					new_head = revHead;
				}

				if (ktail != null) {
					ktail.next = revHead;
				}

				ktail = head;
				head = ptr;
			}
		}
		if (ktail != null) {
			ktail.next = head;
		}
		return new_head == null ? head : new_head;

	}

	public ListNode reverseLinkedList(ListNode head, int k) {

		ListNode new_head = null;
		ListNode ptr = head;
		while (k > 0) {
			// keep track of the next node
			ListNode next_node = ptr.next;

			// insert the current node at the beginning of new reversed list
			ptr.next = new_head;
			new_head = ptr;

			ptr = next_node; // jumb to the next node in the original list
			k--;
		}

		return new_head;

	}

	public static void main(String[] args) {
		ReverseNodeKGroup rng = new ReverseNodeKGroup();
		ListNode head = CommonUtils.createLinkedListFromArray(new int[] { 1, 2, 3, 4, 5 });
		CommonUtils.display(rng.reverseKGroup(head, 2));
		System.out.println("");
		head = CommonUtils.createLinkedListFromArray(new int[] { 1, 2, 3, 4, 5 });

		CommonUtils.display(rng.reverseKGroup(head, 3));

	}
}
