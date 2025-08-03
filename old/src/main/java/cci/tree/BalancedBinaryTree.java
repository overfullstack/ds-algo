package cci.tree;

import ds.TreeNode;

/** Created by gakshintala on 3/30/16. */
public class BalancedBinaryTree {
	static void main() {
		TreeNode t1, t2, t3, t4, t5;
		t5 = new TreeNode(5, null, null);
		t4 = new TreeNode(4, t5, null);
		t3 = new TreeNode(3, null, null);
		t2 = new TreeNode(2, t4, null);
		t1 = new TreeNode(1, t2, t3);

		System.out.println(isTreeBalanced(t1) != -1);
	}

	private static int isTreeBalanced(TreeNode root) {
		if (root == null) {
			return 0;
		}

		var leftHeight = isTreeBalanced(root.left);
		var rightHeight = isTreeBalanced(root.right);

		if (Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		}

		return Math.max(leftHeight, rightHeight) + 1;
	}
}
