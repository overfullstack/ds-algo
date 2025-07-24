package cci.tree;

import ds.tree.TreeNode;
import java.util.LinkedList;
import java.util.List;

/** Created by gakshintala on 3/30/16. */
public class LinkedListPerLevel {
	static void main() {
		TreeNode t1, t2, t3, t4, t5, t6, t7, t8;
		t8 = new TreeNode(8, null, null);
		t7 = new TreeNode(7, null, null);
		t6 = new TreeNode(6, null, null);
		t5 = new TreeNode(5, null, null);
		t4 = new TreeNode(4, t8, null);
		t3 = new TreeNode(3, t6, t7);
		t2 = new TreeNode(2, t4, t5);
		t1 = new TreeNode(1, t2, t3);

		var levelLinkedList = convertToLevelLinkedList(t1);
		for (var l : levelLinkedList) {
			System.out.println(l);
		}
	}

	private static List<List<TreeNode>> convertToLevelLinkedList(TreeNode root) {
		List<List<TreeNode>> lll = new LinkedList<>();
		List<TreeNode> ll = new LinkedList<>();
		ll.add(root);
		lll.add(ll);
		while (!ll.isEmpty()) {
			// Using one list as parent, insert into another list
			var parent = ll;
			ll = new LinkedList<>();
			for (var t : parent) {
				if (t.left != null) ll.add(t.left);
				if (t.right != null) ll.add(t.right);
			}
			lll.add(ll);
		}
		return lll;
	}
}
