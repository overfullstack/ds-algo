package gfg.practice.tree.ConnectAtSameLevelCompleteTree;

/** Created by gakshintala on 6/13/16. */
public class ConnectAtSameLevel {
	public static void main(String[] args) {

		var Node6 = new Node(6, null, null);
		var Node5 = new Node(5, null, null);
		var Node4 = new Node(4, null, null);
		var Node3 = new Node(3, Node6, null);
		var Node2 = new Node(2, Node4, Node5);
		var Node1 = new Node(1, Node2, Node3);

		connectNodesAtSameLevel(Node1);
		printNodes(Node1);
		printNodes(Node2);
		printNodes(Node4);
	}

	private static void printNodes(Node node) {
		while (node != null) {
			System.out.print(node.val + " -> ");
			node = node.next;
		}
		System.out.println();
	}

	private static void connectNodesAtSameLevel(Node root) {
		if (root == null || (root.left == null && root.right == null)) {
			return;
		}
		if (root.left != null) {
			root.left.next = root.right;
		}
		if (root.right != null) {
			// Depending on root.nextRight which was connected as a part of parent recursion.
			root.right.next = (root.next == null) ? null : root.next.left;
		}

		connectNodesAtSameLevel(root.left);
		connectNodesAtSameLevel(root.right);
	}
}

class Node {
	public Node left;
	public Node right;
	public Node next;
	public int val;

	public Node(int val, Node left, Node right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}
