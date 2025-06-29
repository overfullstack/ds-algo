package cci.tree;

import ds.tree.TreeNode;

/** Created by gakshintala on 3/21/16. */
public class IsTreeBST {
	public static void main(String[] args) {
		var treeNode1 = new TreeNode(1, null, null);
		var treeNode3 = new TreeNode(3, null, null);
		var treeNode2 = new TreeNode(2, treeNode1, treeNode3);
		System.out.println(isBST(treeNode2, Integer.MIN_VALUE, Integer.MAX_VALUE));
	}

	private static boolean isBST(TreeNode root, int min, int max) {
		if (root == null) {
			return true;
		}

		if (root.value <= min || root.value > max) {
			return false;
		}

		return (isBST(root.left, min, root.value) && isBST(root.right, root.value, max));
	}
}
