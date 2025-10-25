package practice.heaps;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/* 25 Oct 2025 18:48 */

/** [1792. Maximum Average Pass Ratio](https://leetcode.com/problems/maximum-average-pass-ratio/) */
public class MaximumAveragePassRatio {
	public double maxAverageRatio(int[][] classes, int extraStudents) {
		var maxProfitHeap =
				new PriorityQueue<>(
						Comparator.<int[]>comparingDouble(
										c -> (double) (c[0] + 1) / (c[1] + 1) - ((double) c[0] / c[1]))
								.reversed());
		Collections.addAll(maxProfitHeap, classes);
		while (extraStudents > 0) {
			extraStudents--;
			var cls = maxProfitHeap.poll();
			cls[0]++;
			cls[1]++;
			maxProfitHeap.add(cls);
		}
		return Arrays.stream(classes).mapToDouble(c -> (double) c[0] / c[1]).average().orElseThrow();
	}

	static void main() {
		var l = new MaximumAveragePassRatio();
		System.out.println(l.maxAverageRatio(new int[][] {{1, 2}, {3, 5}, {2, 2}}, 2)); // 0.78333
		System.out.println(
				l.maxAverageRatio(new int[][] {{2, 4}, {3, 9}, {4, 5}, {2, 10}}, 4)); // 0.53485
	}
}
