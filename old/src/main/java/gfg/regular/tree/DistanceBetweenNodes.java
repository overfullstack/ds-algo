package gfg.regular.tree;

import ds.TreeNode;

/** Created by gakshintala on 4/22/16. */
public class DistanceBetweenNodes {

	static void main() {
		// Embedding levels of nodes in the ds itself
		TreeNode t1, t2, t3, t4, t5, t6, t7, t8;
		t8 = new TreeNode(8, null, null, 3);
		t7 = new TreeNode(7, null, null, 2);
		t6 = new TreeNode(6, null, null, 2);
		t5 = new TreeNode(5, null, null, 2);
		t4 = new TreeNode(4, t8, null, 2);
		t3 = new TreeNode(3, t6, t7, 1);
		t2 = new TreeNode(2, t4, t5, 1);
		t1 = new TreeNode(1, t2, t3, 0);

		distanceBetweenNodes(t1, t8, t7);
	}

	private static TreeNode distanceBetweenNodes(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || (root == p && root == q) || (root == p || root == q)) return root;

		var x = distanceBetweenNodes(root.left, p, q);
		if (x != null && x != p && x != q)
			return x; // Which means it originated from * Found Ancestor *

		var y = distanceBetweenNodes(root.right, p, q);
		if (y != null && y != p && y != q)
			return y; // Which means it originated from * Found Ancestor *

		// This is where both the nodes bubble-up meet at their LCA.
		if (x != null && y != null) {
			System.out.println(x.level + y.level - 2 * root.level);
			return root; // * Found Ancestor *
		}

		return x == null
				? y
				: x; // This covers both cases 1. If either is null, return non-null 2. If both are null,
		// return Null
	}
}
