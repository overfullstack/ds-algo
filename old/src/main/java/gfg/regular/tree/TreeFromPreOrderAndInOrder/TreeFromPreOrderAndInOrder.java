package gfg.regular.tree.TreeFromPreOrderAndInOrder;

import ds.TreeNode;
import ds.Utils;

/** Created by gakshintala on 6/12/16. */
public class TreeFromPreOrderAndInOrder {
	static void main() {
		char[] pre = {'A', 'B', 'D', 'E', 'C', 'F'};
		char[] in = {'D', 'B', 'E', 'A', 'F', 'C'};

		Utils.treeInorder(treeFromPreOrderAndInOrder(in, pre, 0, pre.length, new Index(0)));
	}

	private static TreeNode treeFromPreOrderAndInOrder(
			char[] in, char[] pre, int inOrderLeft, int inOrderRight, Index preOrderIndex) {
		// This is similar to BST from Preorder. Preorder drives the recursion. Since this is not BST,
		// instead of max-min, we have inOrderLeft-inOrderRight, and they are determined using Inorder.
		// This base check can also be replaced by `if (inOrderLeft < inOrderRight)`,
		// and place optimization outside of it
		if (preOrderIndex.index >= pre.length) {
			return null;
		}
		// Which means we landed in a leaf-node.
		if (inOrderLeft == inOrderRight) {
			return new TreeNode(pre[preOrderIndex.index++]);
		}
		var val = pre[preOrderIndex.index];
		var valInOrderIndex = findInInOrder(val, in, inOrderLeft, inOrderRight);
		// If condition for checking inOrderLeft-inOrderRight range can be omitted due to optimization
		// above
		var root = new TreeNode(val);
		preOrderIndex.index++;
		root.left =
				treeFromPreOrderAndInOrder(in, pre, inOrderLeft, valInOrderIndex - 1, preOrderIndex);
		root.right =
				treeFromPreOrderAndInOrder(in, pre, valInOrderIndex + 1, inOrderRight, preOrderIndex);
		return root;
	}

	private static int findInInOrder(int val, char[] in, int left, int right) {
		for (var i = left; i <= right; i++) {
			if (in[i] == val) return i;
		}
		return -1;
	}
}

class Index {
	public int index;

	public Index(int index) {
		this.index = index;
	}
}
