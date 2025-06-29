package cci.tree;

import ds.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/** Created by gakshintala on 3/30/16. */
public class TreeBFS {
	public static void main(String[] args) {
		TreeNode t1, t2, t3, t4, t5, t6, t7, t8;
		t8 = new TreeNode(8, null, null);
		t7 = new TreeNode(7, null, null);
		t6 = new TreeNode(6, null, null);
		t5 = new TreeNode(5, null, null);
		t4 = new TreeNode(4, t8, null);
		t3 = new TreeNode(3, t6, t7);
		t2 = new TreeNode(2, t4, t5);
		t1 = new TreeNode(1, t2, t3);

		bfs(t1);
	}

	private static void bfs(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			var t = q.poll();

			System.out.print(t + " ");
			if (t.left != null) q.add(t.left);
			if (t.right != null) q.add(t.right);
		}
	}
}
