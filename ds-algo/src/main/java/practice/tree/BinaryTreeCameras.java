package practice.tree;

import static java.lang.IO.println;
import static practice.tree.BinaryTreeCameras.State.MONITORED_NO_CAM;
import static practice.tree.BinaryTreeCameras.State.CAM_INSTALLED;
import static practice.tree.BinaryTreeCameras.State.NOT_MONITORED;

import ds.tree.TreeNode;
import ds.util.Pair;
import java.util.Arrays;

/** [968. Binary Tree Cameras](https://leetcode.com/problems/binary-tree-cameras/) */
public class BinaryTreeCameras {
	enum State {
    CAM_INSTALLED,
		MONITORED_NO_CAM,
		NOT_MONITORED;
	}

	public int minCameraCover(TreeNode root) {
		if (root == null) {
			return 0;
		}
		var result = solve(root);
		return result.first() + (result.second() == NOT_MONITORED ? 1 : 0);
	}

	private static Pair<Integer, State> solve(TreeNode root) {
		if (root == null) {
			return Pair.of(0, MONITORED_NO_CAM);
		}
		var left = solve(root.left);
		var right = solve(root.right);
		var cameras = left.first() + right.first();
		if (left.second() == MONITORED_NO_CAM && right.second() == MONITORED_NO_CAM) {
			return Pair.of(cameras, NOT_MONITORED);
		}
		if (left.second() == NOT_MONITORED || right.second() == NOT_MONITORED) {
			return Pair.of(1 + cameras, CAM_INSTALLED);
		}
		return Pair.of(cameras, MONITORED_NO_CAM);
	}

	static void main() {
		var binaryTreeCameras = new BinaryTreeCameras();
		println(
				binaryTreeCameras.minCameraCover(
						TreeNode.levelOrderToIncompleteTree(Arrays.asList(0, 0, null, 0, 0)))); // 1
		println(
				binaryTreeCameras.minCameraCover(
						TreeNode.levelOrderToIncompleteTree(
								Arrays.asList(0, 0, null, 0, null, 0, null, null, 0)))); // 2
	}
}
