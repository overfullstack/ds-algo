package gfg.regular.tree.SwappedNodes;

import gfg.ds.TreeNode;

/**
 * Created by gakshintala on 4/13/16.
 *
 * <p>Not complete
 */
public class SwappedNodes {
	public static void main(String[] args) {
		var bstNode7 = new TreeNode(7, null, null);
		var bstNode6 = new TreeNode(5, null, null);
		var bstNode5 = new TreeNode(3, null, null);
		var bstNode4 = new TreeNode(2, null, null);
		var bstNode3 = new TreeNode(6, bstNode6, bstNode7);
		var bstNode2 = new TreeNode(1, bstNode4, bstNode5);
		var bstNode1 = new TreeNode(4, bstNode2, bstNode3);

		printInorder(bstNode1);
		System.out.println();

		var refs = correctSwappedNodes(bstNode1, null, new TreeNode[3]);

		if (refs[0] == null && refs[1] == null && refs[2] == null) {
			System.out.println("Tree doesn't contain any swapped Nodes");
		} else if (refs[2] == null) swap(refs[0], refs[1]);
		else swap(refs[0], refs[2]);

		printInorder(bstNode1);
	}

	private static void swap(TreeNode first, TreeNode last) {
		var temp = first.val;
		first.val = last.val;
		last.val = temp;
	}

	private static TreeNode[] correctSwappedNodes(TreeNode root, TreeNode parent, TreeNode[] refs) {
		if (root == null) {
			return refs;
		}

		// Inorder traversal
		correctSwappedNodes(
				root.left, parent, refs); // Parent node of left most node in left tree will be null

		if (parent != null && root.val < parent.val) {
			if (refs[0]
					== null) { // ref[1] is if adjacent nodes are swapped, in that case we wont find another
				// anomaly
				refs[0] = root;
				refs[1] = parent;
			} else {
				refs[2] = root;
				return refs;
			}
		}
		parent =
				root; // For root.left there is backing out, but not for root.right so we send current root
		// as parent.
		correctSwappedNodes(root.right, parent, refs);
		return refs;
	}

	private static void printInorder(TreeNode root) {
		if (root == null) return;
		printInorder(root.left);
		System.out.print(root.val + " ");
		printInorder(root.right);
	}
}
