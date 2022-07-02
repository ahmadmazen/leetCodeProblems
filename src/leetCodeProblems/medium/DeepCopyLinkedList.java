package leetCodeProblems.medium;

import java.util.HashMap;
import java.util.Map;

/*
 * problem definition
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 * 
 */
public class DeepCopyLinkedList {
	/*
	 * approach 1 with the help of hashTable time complexity O(n), space complexity
	 * O(n) since we used auxiliary space
	 */
	public Node copyRandomList_withHashtable(Node head) {

		Map<Node, Node> original_to_copy_map = new HashMap<>();

		Node original = head;

		while (original != null) {
			Node clone = new Node(original.val);
			original_to_copy_map.put(original, clone);
			original = original.next;
		}

		original = head;

		while (original != null) {
			Node clone = original_to_copy_map.get(original);
			if (original.next != null) {
				clone.next = original_to_copy_map.get(original.next);
			}
			if (original.random != null) {
				clone.random = original_to_copy_map.get(original.random);
			}
			original = original.next;
		}
		return original_to_copy_map.get(head);

	}

	/*
	 * second approach: is to copy each original node after the original, so the
	 * list will be: originalNode1 -> copyNode1 -> originalNode2-> copyNode2 ...etc
	 * then you will adjust the next & random pointers in the copied nodes finally
	 * you will set the original nodes (next & random pointers) to their original
	 * back time complexity is O(n), space complexity is O(1) since we don't use any
	 * auxiliary space
	 * 
	 */
	public Node copyRandomList(Node head) {
		if(head == null) {
			return null;
		}
		Node current = head, temp = null;
//clone each node and wire the copied node next to its original : A-> A'-> B-> B'
		while(current != null) {
			Node clone = new Node(current.val);
			Node original_next = current.next;
			current.next = clone;
			current = original_next;
		}
		current = head; //set current back to the head
		//adjust random pointer of the cloned nodes
		while(current != null) {
			if(current.next != null) {
				current.next.random = current.random != null ? current.random.next : null;	
			}
			
		    current = (current.next) != null ? current.next.next : null;	
		}
		
		
		//split the origianl list from the cloned
		// set two pointers while traversing, one for the original and other for clone
		Node original = head;
		Node clone = head.next; 
		temp = clone;
		while(original != null && clone != null) {
			original.next = (original.next)!= null ? original.next.next : null;
			clone.next = (clone.next)!= null ? clone.next.next : null;
			
			original = original.next;
			clone = clone.next;
			
		}
		
		return temp;
	}

	public static void main(String[] args) {
		DeepCopyLinkedList cll = new DeepCopyLinkedList();
		Node l1n1 = new Node(7);
		Node l1n2 = new Node(13);
		Node l1n3 = new Node(11);
		Node l1n4 = new Node(10);
		Node l1n5 = new Node(1);
		l1n1.next = l1n2;

		l1n2.next = l1n3;
		l1n2.random = l1n1;
		l1n3.next = l1n4;
		l1n3.random = l1n5;
		l1n4.next = l1n5;
		l1n4.random = l1n3;
		l1n5.random = l1n1;
		// printListValues(l1n1);
		printValueRandomPairs(l1n1);
		Node cloned = cll.copyRandomList(l1n1);
		printValueRandomPairs(cloned);
//===================================
		Node l2 = new Node(5);
		Node l2n2 = new Node(6);
		Node l2n3 = new Node(4);

		l2.next = l2n2;
		l2n2.next = l2n3;

		// printListValues(l2);
		// printListValues(cll.copyRandomList(head)(l1, l2));
	}

	static void printListValues(Node l) {
		while (l != null) {
			System.out.println(l.val);
			l = l.next;
		}

	}

	static void printValueRandomPairs(Node l) {
		String s = "[";
		while (l != null) {
			s += "[" + l.val + "," + (l.random != null ? String.valueOf(l.random.val) : "null") + "]";
			s += l.next != null ? "," : "";
			l = l.next;
		}
		s += "]";
		System.out.println(s);

	}
}
