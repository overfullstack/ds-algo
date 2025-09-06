package practice.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/** [964 · Food Set](https://www.lintcode.com/problem/964) */
public class FoodSet {
	public int getMinCalories(int[][] lunch, int[][] dinner, int minDeliciousDegree) {
		Arrays.sort(lunch, Comparator.comparingInt(l -> l[1])); // ! Sort by delicious degree
		Arrays.sort(dinner, Comparator.comparingInt(d -> d[1]));

		var minHeapForLunchCalories =
				new PriorityQueue<Integer>(Comparator.comparingInt(lIdx -> lunch[lIdx][0]));
		var minCalories = Integer.MAX_VALUE;
		for (var lIdx = 0; lIdx < lunch.length; lIdx++) {
			minHeapForLunchCalories.add(lIdx);
			if (lunch[lIdx][1] >= minDeliciousDegree && minCalories > lunch[lIdx][0]) {
				minCalories = lunch[lIdx][0];
			}
		}
		for (var d : dinner) {
			if (d[1] >= minDeliciousDegree && minCalories > d[0]) {
				minCalories = d[0];
			}
		}
		var lIdx = 0;
		var dIdx = dinner.length - 1;
		while (lIdx < lunch.length && dIdx >= 0) {
			var curDeliciousDegree = lunch[lIdx][1] + dinner[dIdx][1];
			if (curDeliciousDegree >= minDeliciousDegree) {
				// ! Can't pick these minCalories from lunch, as they reach maxDeliciousDegree
				// ! even when paired with the max delicious dinner
				// ! Increasing calories for lunch trading-off for delicious degree.
				while (!minHeapForLunchCalories.isEmpty() && minHeapForLunchCalories.peek() < lIdx) {
					minHeapForLunchCalories.poll();
				}
				// ! Record `minCalories` for each lunch-dinner pair that meets the delicious degree
				if (!minHeapForLunchCalories.isEmpty()) {
					minCalories =
							Math.min(minCalories, dinner[dIdx][0] + lunch[minHeapForLunchCalories.peek()][0]);
				}
				dIdx--; // ! Decrease dinner delicious degree and check if we can find better pair
			} else {
				lIdx++;
			}
		}
		return minCalories == Integer.MAX_VALUE ? -1 : minCalories;
	}
}
