package gfg.regular.tree.PrintCousins;

import ds.TreeNode;

/** Created by gakshintala on 7/5/16. */
public class PrintCousins {
	static void main() {
		TreeNode t1, t2, t3, t4, t5, t6, t7;
		t7 = new TreeNode(7, null, null);
		t6 = new TreeNode(6, null, null);
		t5 = new TreeNode(5, null, null);
		t4 = new TreeNode(4, null, null);
		t3 = new TreeNode(3, t6, t7);
		t2 = new TreeNode(2, t4, t5);
		t1 = new TreeNode(1, t2, t3);

		printCousins(t1, 4, getLevel(t1, 4, 0));
	}

	private static int getLevel(TreeNode root, int val, int level) {
		if (root == null) {
			return -1;
		}
		if (root.val == val) {
			return level;
		}
		var valLevel = getLevel(root.left, val, level + 1);
		if (valLevel != -1) {
			return valLevel;
		}
		return getLevel(root.right, val, level + 1);
	}

	// The idea is to print all nodes at a level, skipping the one holding val as child
	private static void printCousins(TreeNode root, int val, int level) {
		if (root == null || level < 1) {
			return;
		}
		if (level == 1) {
			if (root.left.val == val || root.right.val == val) {
				// This means We just tapped into the root that contains our value as one of the children,
				// skip the sibling and print all others
				return;
			}
			if (root.left != null) System.out.print(root.left.val + " ");
			if (root.right != null) System.out.print(root.right.val + " ");
		}
		if (level > 1) {
			printCousins(root.left, val, level - 1);
			printCousins(root.right, val, level - 1);
		}
	}
}
