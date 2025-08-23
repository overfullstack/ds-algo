package practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/** [964 · Food Set](https://www.lintcode.com/problem/964) */
public class FoodSet {
	public int getMinCalories(int[][] lunch, int[][] dinner, int t) {
		Arrays.sort(lunch, Comparator.comparingInt(l -> l[1]));
		Arrays.sort(dinner, Comparator.comparingInt(d -> d[1]));

		var minCalories = Integer.MAX_VALUE;
		var minHeap = new PriorityQueue<Integer>(Comparator.comparingInt(lIdx -> lunch[lIdx][0]));
		for (var lIdx = 0; lIdx < lunch.length; lIdx++) {
			minHeap.add(lIdx);
			if (lunch[lIdx][1] >= t && minCalories > lunch[lIdx][0]) {
				minCalories = lunch[lIdx][0];
			}
		}
		for (var d : dinner) {
			if (d[1] >= t && minCalories > d[0]) {
				minCalories = d[0];
			}
		}
		var lIdx = 0;
		var dIdx = dinner.length - 1;
		while (lIdx < lunch.length && dIdx >= 0) {
			var curT = lunch[lIdx][1] + dinner[dIdx][1];
			if (curT >= t) {
				while (!minHeap.isEmpty() && minHeap.peek() < lIdx) {
					minHeap.poll();
				}
        if (!minHeap.isEmpty()) {
          minCalories = Math.min(minCalories, dinner[dIdx][0] + lunch[minHeap.peek()][0]);
        }
				dIdx--;
			} else {
				lIdx++;
			}
		}
		return minCalories == Integer.MAX_VALUE ? -1 : minCalories;
	}
}
