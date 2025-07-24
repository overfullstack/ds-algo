package gfg.practice.tree.ConnectAtSameLevelForInCompleteTree;

/** Created by gakshintala on 6/13/16. */
public class ConnectAtSameLevelForIncompleteTree {
	static void main() {

		var node11 = new Node(11, null, null);
		var node10 = new Node(10, null, null);
		var node9 = new Node(9, null, null);
		var node8 = new Node(8, null, null);
		var node7 = new Node(7, node10, node11);
		var node6 = new Node(6, null, null);
		var node5 = new Node(5, null, null);
		var node4 = new Node(4, node8, node9);
		var node3 = new Node(3, node6, node7);
		var node2 = new Node(2, node4, node5);
		var node1 = new Node(1, node2, node3);

		connect(node1);
		printNodes(node1);
		printNodes(node2);
		printNodes(node4);
		printNodes(node8);
	}

	private static void printNodes(Node node) {
		while (node != null) {
			System.out.print(node.val + " -> ");
			node = node.nextRight;
		}
		System.out.println();
	}

	private static void connect(Node root) {
		if (root == null) {
			return;
		}
		// Deal with horizontal right most side (Not tree right) of the tree first, making sure all
		// those have nextRight pointers.
		// Move in a zig-zag fashion: (root->left) -> (left->nextRight most) -> (root->left)
		if (root.nextRight != null) {
			connect(root.nextRight);
		}

		if (root.left != null) { // If no nextRight found, making connections in next level.
			if (root.right != null) {
				root.left.nextRight = root.right;
				root.right.nextRight =
						getNext(root); // For this to happen, all the right side of our tree must be linked.
			} else {
				root.left.nextRight = getNext(root);
			}
			connect(
					root.left); // Sweep from left to right horizontally on same level, for trampoline effect,
			// in the next recursion we shall go to right on the same level
		} else if (root.right != null) {
			root.right.nextRight = getNext(root);
			connect(root.right);
		} else {
			connect(getNext(root));
		}
	}

	private static Node getNext(Node root) {
		while (root.nextRight != null) {
			if (root.nextRight.left != null) {
				return root.nextRight.left;
			} else if (root.nextRight.right != null) {
				return root.nextRight.right;
			}
			root = root.nextRight;
		}
		return null;
	}
}

class Node {
	public Node left;
	public Node right;
	public Node nextRight;
	public int val;

	public Node(int val, Node left, Node right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}
