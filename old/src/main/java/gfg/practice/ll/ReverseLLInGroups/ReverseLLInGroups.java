package gfg.practice.ll.ReverseLLInGroups;

import static gfg.ds.Utils.printSLL;

import gfg.ds.SLLNode;
import java.util.Scanner;

/** Created by Gopala Akshintala on 6/2/17. */
public class ReverseLLInGroups {
	public static void main(String[] args) {
		var scn = new Scanner(System.in);
		var tests = scn.nextInt();
		while (tests-- > 0) {
			var len = scn.nextInt();
			var sllNode = new SLLNode(scn.nextInt());
			var head = sllNode;
			while (len-- > 1) {
				sllNode.next = new SLLNode(scn.nextInt());
				sllNode = sllNode.next;
			}
			var group = scn.nextInt();
			printSLL(reverseLLInBlocks(head, group));
		}
	}

	private static SLLNode reverseLLInBlocks(SLLNode head, int group) {
		SLLNode prev = null, next = head, cur;
		var count = 0;
		while (count < group && next != null) { // reverse current group.
			cur = next;
			next = next.next;
			cur.next = prev;
			prev = cur;
			count++;
		}
		if (next != null) {
			head.next = reverseLLInBlocks(next, group); // next points to head of next group.
		}
		return prev; // prev would point to reversed end of current group (which is the start of
		// reversed group).
	}
}
