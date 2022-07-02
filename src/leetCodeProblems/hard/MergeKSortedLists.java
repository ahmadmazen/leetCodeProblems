package leetCodeProblems.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import common.CommonUtils;
import leetCodeProblems.medium.ListNode;

public class MergeKSortedLists {
	/*
	 * Approach 1: Brute Force - Traverse all the linked lists and collect the
	 * values of the nodes into an array. - Sort and iterate over this array to
	 * Create a new sorted linked list
	 * 
	 * Time complexity : O(N\log N)O(NlogN) where NN is the total number of nodes.
	 * 
	 * Collecting all the values costs O(N)O(N) time. A stable sorting algorithm
	 * costs O(N log N) time. Iterating for creating the linked list costs O(N)
	 * time. Space complexity : O(N).
	 * 
	 * Sorting cost O(N) space (depends on the algorithm you choose : it's
	 * mergesort). Creating a new linked list costs O(N) space.
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		List<Integer> l = new ArrayList<>();
		// Collecting all the values costs O(N) time.
		// N is the total number of nodes.
		for (ListNode node : lists) {
			while (node != null) {
				l.add(node.val);
				node = node.next;
			}
		}

		Collections.sort(l); // A stable sorting algorithm costs O(NlogN) time. mergesort used here
		System.out.println("after sorted = " + Arrays.toString(l.toArray()));

		ListNode ptr = new ListNode(-1);
		ListNode merged = ptr;
		// Iterating for creating the linked list costs O(N)O(N) time.
		for (Integer n : l) {
			ptr.next = new ListNode(n);
			ptr = ptr.next;

		}
		return merged.next;
	}

	/*
	 * approach 2: as long as they are sorted, so we need to compare O(n) space.
	 * Time complexity : O(kN) where k is the number of linked lists.
	 * 
	 * Almost every selection of node in final linked costs O(k) {k-1} times
	 * comparison) . There are N nodes in the final linked list. Space complexity :
	 * 
	 * O(n) Creating a new linked list costs O(n) space. O(1) It's not hard to apply
	 * in-place method - connect selected nodes instead of creating new nodes to
	 * fill the new linked list.
	 */
	public ListNode mergeKLists_compare_nSpace(ListNode[] lists) {
		int min_index = 0;
		ListNode head = new ListNode(0);
		ListNode h = head;
		while (true) {
			boolean isBreak = true;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < lists.length; i++) {
				if (lists[i] != null) {
					if (lists[i].val < min) {
						min_index = i;
						min = lists[i].val;
					}
					isBreak = false;
				}

			}
			if (isBreak) {
				break;
			}
			ListNode a = new ListNode(lists[min_index].val);
			h.next = a;
			h = h.next;
			lists[min_index] = lists[min_index].next;
		}
		h.next = null;
		return head.next;
	}
	/*
	 * same approach comparison but improve to : O(1) space, same as the previous
	 * but with no extra space
	 */

	public ListNode mergeKLists_compareOneByOne(ListNode[] lists) {
		int min_index = 0;
		ListNode head = new ListNode(0);
		ListNode h = head;
		while (true) {
			boolean isBreak = true;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < lists.length; i++) {
				if (lists[i] != null) {
					if (lists[i].val < min) {
						min_index = i;
						min = lists[i].val;
					}
					isBreak = false;
				}

			}
			if (isBreak) {
				break;
			}
			h.next = lists[min_index];
			h = h.next;
			lists[min_index] = lists[min_index].next; // move the linked list to next element
		}
		h.next = null;
		return head.next;
	}

	/*
	 * same last approach comparison but optimizing the time complexiy by using
	 * priority Queue Java Priority Queue is implemented using Heap Data Structures
	 * and Heap has O(log(n)) time complexity to insert and delete element. So: Time
	 * complexity : O(N log k) where k is the number of linked lists.
	 * 
	 * The comparison cost will be reduced to O(log k) for every pop and insertion
	 * to priority queue. But finding the node with the smallest value just costs
	 * O(1) time. There are N nodes in the final linked list. Space complexity :
	 * O(n) Creating a new linked list costs O(n) space. O(k)The code present
	 * applies in-place method which cost O(1) space. And the priority queue (often
	 * implemented with heaps) costs O(k) space (it's far less than N in most
	 * situations).
	 * 
	 */
	public ListNode mergeKLists_pq(ListNode[] lists) {

		PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>((node1, node2) -> node1.val - node2.val);
		// add the head node of each linked lists to the pq
		for (ListNode n : lists) {
			pq.add(n);
		}
		// traverse through the lists
		ListNode head = new ListNode(-1);
		ListNode pointer = head;
		while (!pq.isEmpty()) {

			ListNode node = pq.poll();
			pointer.next = node;
			pointer = pointer.next;
			if (node.next != null) {
				pq.add(node.next);
			}
		}
		return head.next;
	}

	/*
	 * Approach 4: Merge lists one by one Algorithm
	 * 
	 * Convert merge k lists problem to merge 2 lists {k-1) times. Here is the merge
	 * 2 lists problem page.
	 * https://leetcode.com/problems/merge-two-sorted-lists/description/
	 * 
	 * Time complexity : O(kN) where k is the number of linked lists.
	 * 
	 * We can merge two sorted linked list in O(n) time where n is the total number
	 * of nodes in two lists. Sum up the merge process and we can get: O(kN). Space
	 * complexity : O(1) We can merge two sorted linked list in O(1) space.
	 */
	// merge two lists will be called from the main method mergeKlists
	private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			return null;
		}
		if (l1 != null && l2 == null) {
			return l1;
		} else if (l1 == null && l2 != null) {
			return l2;
		}

		ListNode head = new ListNode(-1);
		ListNode pointer = head;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				pointer.next = l1;
				l1 = l1.next;
			} else {
				pointer.next = l2;
				l2 = l2.next;
			}
			pointer = pointer.next;
		}
		if (l1 != null) {
			pointer.next = l1;
		} else if (l2 != null) {
			pointer.next = l2;
		}
		return head.next;

	}

	public ListNode mergeKLists_byMergingTwoListsOneByOne(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		if (lists.length == 1) {
			return lists[0];
		}

		// merge first two lists
		ListNode head = mergeTwoLists(lists[0], lists[1]);

		for (int i = 2; i < lists.length; i++) {
			head = mergeTwoLists(head, lists[i]);
		}
		return head;
	}

	/*
	 * Approach 5: Merge two lists with Divide And Conquer 
	 * Time complexity : O(Nlogk) where k is the number of linked lists.
	 * 
	 * We can merge two sorted linked list in O(n) time where n is the total
	 * number of nodes in two lists.
	 * Sum up the merge process and we can get: O(Nlogk) 
	 * Space complexity : O(1)
	 */
	public ListNode mergeKLists_divideAndConquer(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}
		int interval = 1;
		while (interval < lists.length) {
		//	System.out.println(lists.length);
			for (int i = 0; i + interval < lists.length; i = i + interval * 2) {
				lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
			}
			interval *= 2;
		}

		return lists[0];
	}

	public static void main(String[] args) {
		MergeKSortedLists mkl = new MergeKSortedLists();
		ListNode head1 = CommonUtils.createLinkedListFromArray(new int[] { 1, 3, 7, 9, 10 });
		ListNode head2 = CommonUtils.createLinkedListFromArray(new int[] { 2, 5, 6, 15, 20 });
		ListNode head3 = CommonUtils.createLinkedListFromArray(new int[] { 3, 4, 12, 18, 19 });
		ListNode[] lists = new ListNode[] { head1, head2, head3 };

		// CommonUtils.printListValues(mkl.mergeKLists(lists));
		// CommonUtils.printListValues(mkl.mergeKLists_compareOneByOne(lists));
		// CommonUtils.printListValues(mkl.mergeKLists_pq(lists));
		//CommonUtils.printListValues(mkl.mergeKLists_byMergingTwoListsOneByOne(lists));
		CommonUtils.printListValues(mkl.mergeKLists_divideAndConquer(lists));

	}

}
