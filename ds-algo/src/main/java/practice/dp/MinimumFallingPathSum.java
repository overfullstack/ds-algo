package practice.dp;

import java.util.Arrays;

/* 26 Oct 2025 21:20 */

/** [931. Minimum Falling Path Sum](https://leetcode.com/problems/minimum-falling-path-sum/) */
public class MinimumFallingPathSum {
	public int minFallingPathSum(int[][] matrix) {
		var table = matrix[0].clone(); // ! Clone to avoid mutating input
		for (var row = 1; row < matrix.length; row++) {
			var prev = table[0]; // ! Store previous column value before overwriting
			for (var col = 0; col < matrix[0].length; col++) {
				var temp = table[col]; // ! Save current value before overwriting
				if (col == 0) {
					table[col] = matrix[row][col] + Math.min(table[col], table[col + 1]);
				} else if (col == matrix[0].length - 1) {
					table[col] = matrix[row][col] + Math.min(table[col], prev);
				} else {
					table[col] = matrix[row][col] + Math.min(table[col], Math.min(prev, table[col + 1]));
				}
				prev = temp; // ! Move to next column
			}
		}
		return Arrays.stream(table).min().orElseThrow();
	}

	static void main() {
		var l = new MinimumFallingPathSum();
		System.out.println(l.minFallingPathSum(new int[][] {{2, 1, 3}, {6, 5, 4}, {7, 8, 9}})); // 13
		System.out.println(l.minFallingPathSum(new int[][] {{-19, 57}, {-40, -5}})); // -59
	}
}
