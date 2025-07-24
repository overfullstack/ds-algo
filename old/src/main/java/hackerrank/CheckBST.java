package hackerrank;

/** Created by gakshintala on 2/29/16. */
public class CheckBST {

	static int lastNode = Integer.MIN_VALUE;

	static void main() {
		var left = new TreeNode(1, null, null);
		var right = new TreeNode(3, null, null);
		var root = new TreeNode(2, left, right);

		System.out.println(check(root));
	}

	private static boolean check(TreeNode root) {
		if (root == null) return true;
		if (!check(root.left)) return false;
		if (root.value <= lastNode) return false;
		lastNode = root.value;
		return check(root.right);
	}
}

class TreeNode {
	int value;
	TreeNode left;
	TreeNode right;

	TreeNode(int value, TreeNode left, TreeNode right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
}
