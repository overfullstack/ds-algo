package practice;

/* 31 Aug 2025 18:34 */

import ds.TreeNode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * [1161. Maximum Level Sum of a Binary
 * Tree](https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/)
 */
public class MaximumLevelSumOfABinaryTree {
	public int maxLevelSum(TreeNode root) {
		var currentLevel = List.of(root);
		var maxSum = root.val;
		var maxSumLevel = 1;
		var level = 1;
		while (!currentLevel.isEmpty()) {
			level++;
			var nextLevel =
					currentLevel.stream()
							.flatMap(n -> Stream.of(n.left, n.right).filter(Objects::nonNull))
							.toList();
			var nextLevelSum =
					nextLevel.isEmpty() ? Integer.MIN_VALUE : nextLevel.stream().mapToInt(n -> n.val).sum();
			if (nextLevelSum > maxSum) {
				maxSum = nextLevelSum;
				maxSumLevel = level;
			}
			currentLevel = nextLevel;
		}
		return maxSumLevel;
	}
}
