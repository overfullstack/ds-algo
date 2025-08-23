package gfg.regular.ll.CloneLL;

import ds.ListNode;

/** Created by gakshintala on 6/21/16. */
public class CloneLL {
	static void main() {
		var head = new ListNode(1);
		head.add(2);
		head.add(3);
		head.add(4);
		head.add(5);

		head.random = head.next.next;
		head.next.random = head.next.next.next;
		head.next.next.random = head.next.next.next.next;
		head.next.next.next.random = head.next.next.next.next.next;
		head.next.next.next.next.random = head.next;

		System.out.println("**Original: ");
		print(head);

		var clonedHead = cloneLL(head);
		System.out.println("**Cloned: ");
		print(clonedHead);
	}

	private static ListNode cloneLL(ListNode head) {
		insertClonesAlternatively(head);
		assignRandomNodesToClones(head);

		var cloneHead = head.next;
		separateOriginalAndClone(head, cloneHead);

		return cloneHead;
	}

	private static void separateOriginalAndClone(ListNode head, ListNode cloneHead) {
		while (true) {
			head.next = head.next.next;
			head = head.next;

			if (head == null) {
				break;
			}

			cloneHead.next = cloneHead.next.next;
			cloneHead = cloneHead.next;
		}
	}

	private static void assignRandomNodesToClones(ListNode head) {
		while (head != null) {
			// head.nextRight points to current clone and head.random.nextRight will point to clone of
			// original head.random
			head.next.random = (head.random == null) ? null : head.random.next;
			head = head.next.next;
		}
	}

	private static void insertClonesAlternatively(ListNode head) {
		while (head != null) {
			var cloneNode = new ListNode(head.val);
			cloneNode.next = head.next;
			head.next = cloneNode;
			head = cloneNode.next;
		}
	}

	public static void print(ListNode node) {
		while (node != null) {
			System.out.println("(" + node.val + "," + node.random + ")");
			node = node.next;
		}
	}
}
