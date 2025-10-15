package practice.heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/** [964 · Food Set](https://www.lintcode.com/problem/964) */
public class FoodSet {
	public int getMinCalories(int[][] lunch, int[][] dinner, int minDeliciousDegree) {
    // ! Sort lunch and dinner by delicious degree
    Arrays.sort(lunch, Comparator.comparingInt(l -> l[1])); 
		Arrays.sort(dinner, Comparator.comparingInt(d -> d[1]));

		var minHeapForLunchCalories =
				new PriorityQueue<Integer>(Comparator.comparingInt(lIdx -> lunch[lIdx][0]));
		for (var lIdx = 0; lIdx < lunch.length; lIdx++) {
			minHeapForLunchCalories.add(lIdx);
		}
		var minLunchCalories =
				Arrays.stream(lunch)
						.filter(l -> l[1] >= minDeliciousDegree)
						.mapToInt(l -> l[0])
						.min()
						.orElse(Integer.MAX_VALUE);
		var minDinnerCalories =
				Arrays.stream(dinner)
						.filter(d -> d[1] >= minDeliciousDegree)
						.mapToInt(d -> d[0])
						.min()
						.orElse(Integer.MAX_VALUE);
		var minCalories = Math.min(minLunchCalories, minDinnerCalories);

		var lIdx = 0; // ! Min delicious lunch
		var dIdx = dinner.length - 1; // ! Max delicious dinner
		while (lIdx < lunch.length && dIdx >= 0) {
			var curDeliciousDegree = lunch[lIdx][1] + dinner[dIdx][1];
			if (curDeliciousDegree >= minDeliciousDegree) {
				while (!minHeapForLunchCalories.isEmpty() && minHeapForLunchCalories.peek() < lIdx) {
					// ! lunch entries before these are lesser delicious than `lIdx`
					// ! poll them even if they have lesser calories
					// ! as they can't meet `minDeliciousDegree` even when paired with max delicious `dIdx`
					minHeapForLunchCalories.poll();
				}
				// ! Record `minCalories` for each lunch-dinner pair that meets the delicious degree
				if (!minHeapForLunchCalories.isEmpty()) {
					minCalories =
							Math.min(minCalories, dinner[dIdx][0] + lunch[minHeapForLunchCalories.peek()][0]);
				}
				dIdx--; // ! Decrease dinner delicious degree and check if we can find better pair
			} else {
				lIdx++; // ! Increase lunch delicious degree and check if we can find better pair
			}
		}
		return minCalories == Integer.MAX_VALUE ? -1 : minCalories;
	}

	static void main() {
		FoodSet fs = new FoodSet();
		int minCalories =
				fs.getMinCalories(
						new int[][] {{10, 10}, {20, 20}}, new int[][] {{20, 30}, {30, 30}}, 40); // 30
		System.out.println(minCalories);
	}
}
