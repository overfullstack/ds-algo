package gfg.regular.ll.MergeAlternatively;

import ds.ListNode;
import ds.Utils;

/** Created by gakshintala on 6/12/16. */
public class MergeAlternatively {
	static void main() {
		ListNode k1, k2, k3;
		k3 = new ListNode(5);
		k2 = new ListNode(3, k3);
		k1 = new ListNode(1, k2);

		ListNode l1, l2, l3, l4, l5;
		l5 = new ListNode(8);
		l4 = new ListNode(7, l5);
		l3 = new ListNode(6, l4);
		l2 = new ListNode(4, l3);
		l1 = new ListNode(2, l2);

		mergeAlternatively(k1, l1);
		Utils.printSLL(k1);
	}

	private static void mergeAlternatively(ListNode k, ListNode l) {
		ListNode kNext, lNext;
		while (l != null && k != null) {
			// Save nextRight pointers
			kNext = k.next;
			lNext = l.next;

			// link alternatively
			k.next = l;
			l.next = kNext;

			// Move to corresponding nextRight nodes in list
			k = kNext;
			l = lNext;
		}
	}
}
