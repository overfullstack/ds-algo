package practice;

/* 02 Sep 2025 17:32 */

/**
 * [331. Verify Preorder Serialization of a Binary
 * Tree](https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/)
 */
public class VerifyPreorderSerializationOfABinaryTree {
	public boolean isValidSerialization(String preorder) {
		var nodes = preorder.split(",");
		var edgeDiff = 1;
		for (var node : nodes) {
			edgeDiff--; // Decrement for every node
			if (edgeDiff < 0) {
				return false;
			}
			if (!node.equals("#")) {
				// ! Each non-leaf node adds 2 edges, which should be negated when they are traversed
				edgeDiff += 2;
			}
		}
		return edgeDiff == 0;
	}
}
