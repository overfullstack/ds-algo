package cci.tree;

import ds.TreeNode;

/** Created by gakshintala on 3/21/16. */
public class IsTreeBST {
	static void main() {
		var treeNode1 = new TreeNode(1, null, null);
		var treeNode3 = new TreeNode(3, null, null);
		var treeNode2 = new TreeNode(2, treeNode1, treeNode3);
		System.out.println(isBST(treeNode2, Integer.MIN_VALUE, Integer.MAX_VALUE));
	}

	private static boolean isBST(TreeNode root, int min, int max) {
		if (root == null) {
			return true;
		}

		if (root.val <= min || root.val > max) {
			return false;
		}

		return (isBST(root.left, min, root.val) && isBST(root.right, root.val, max));
	}
}
