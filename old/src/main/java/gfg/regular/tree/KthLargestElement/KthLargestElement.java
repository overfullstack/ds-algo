package gfg.regular.tree.KthLargestElement;

import gfg.ds.TreeNode;

/** Created by Gopala Akshintala on 2/25/17. */
public class KthLargestElement {
	private static int k = 3;

	static void main() {
		var bstNode7 = new TreeNode(7, null, null);
		var bstNode5 = new TreeNode(5, null, null);
		var bstNode3 = new TreeNode(3, null, null);
		var bstNode1 = new TreeNode(1, null, null);
		var bstNode6 = new TreeNode(6, bstNode5, bstNode7);
		var bstNode2 = new TreeNode(2, bstNode1, bstNode3);
		var bstNode4 = new TreeNode(4, bstNode2, bstNode6);

		reverseInorderTraversal(bstNode4);
		k = 3;
		kthLargestInBST(bstNode4, 1);
	}

	private static void reverseInorderTraversal(TreeNode root) {
		if (root == null || k <= 0) return;

		reverseInorderTraversal(root.right);
		k--;
		if (k == 0) {
			System.out.println(root.val);
			return;
		}
		reverseInorderTraversal(root.left);
	}

	private static int kthLargestInBST(TreeNode root, int count) {
		if (root == null || k <= 0) {
			return -1;
		}

		count = kthLargestInBST(root.right, count);
		if (count == k) {
			System.out.println(root);
			return 0;
		}
		return kthLargestInBST(root.left, count + 1);
	}
}
