package gfg.regular.ll.FlattenAndSortLL;

import ds.ListNode;

/** Created by gakshintala on 6/21/16. */
public class FlattenAndSortLL {

	private static ListNode flattenLinkedList(ListNode head) {
		if (head.next == null) return head;
		// Recurring to reach the end of top LL and then do bottom-up
		head.next = flattenLinkedList(head.next);
		return mergeLL(head, head.next);
	}

	private static ListNode mergeLL(ListNode ll1, ListNode ll2) {
		if (ll1 == null) return ll2;
		if (ll2 == null) return ll1;

		// Top-down way to attach values to resultNode in ascending order
		ListNode resultNode;
		if (ll1.val < ll2.val) {
			resultNode = ll1;
			resultNode.down = mergeLL(ll1.down, ll2);
		} else {
			resultNode = ll2;
			resultNode.down = mergeLL(ll1, ll2.down);
		}
		return resultNode;
	}

	private static void printSLL(ListNode head) {
		while (head != null) {
			System.out.print(head + " -> ");
			head = head.down;
		}
	}

	static void main() {
		var head = new ListNode(5);
		head.down = new ListNode(7);
		head.down.down = new ListNode(8);
		head.down.down.down = new ListNode(30);

		var node = new ListNode(10);
		head.next = node;
		node.down = new ListNode(20);

		node = new ListNode(19);
		head.next.next = node;
		node.down = new ListNode(22);
		node.down.down = new ListNode(50);

		node = new ListNode(19);
		head.next.next.next = node;
		node.down = new ListNode(28);
		node.down.down = new ListNode(35);
		node.down.down.down = new ListNode(40);
		node.down.down.down.down = new ListNode(45);

		printSLL(flattenLinkedList(head));
	}
}
