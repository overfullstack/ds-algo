package practice.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RussianDolls {
	public int maxEnvelopes(int[][] envelopes) {
		final var sortedEnvelopes =
				Arrays.stream(envelopes)
						.sorted(Comparator.<int[]>comparingInt(o -> o[0]).thenComparingInt(o -> -o[1]))
						.toList();
		final var heights = sortedEnvelopes.stream().mapToInt(o -> o[1]).toArray();
		return findLISSize(heights);
	}

	private static int findLISSize(int[] heights) {
		var tails = new ArrayList<Integer>();
		for (var height : heights) {
			var pos = findInsertionPOS(tails, height);
			if (pos == tails.size()) {
				tails.add(height);
			} else {
				tails.set(pos, height);
			}
		}
		return tails.size();
	}

	// * leftmost tail greater than or equal to num
	private static int findInsertionPOS(List<Integer> tails, int num) {
		var left = 0;
		var right = tails.size();
		while (left < right) {
			final var mid = left + (right - left) / 2;
			if (tails.get(mid) >= num) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}
}
