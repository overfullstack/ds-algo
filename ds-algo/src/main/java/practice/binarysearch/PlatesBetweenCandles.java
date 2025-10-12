package practice.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [2055. Plates Between Candles](https://leetcode.com/problems/plates-between-candles)
 */
public class PlatesBetweenCandles {
	public int[] platesBetweenCandles(String s, int[][] queries) {
		var candlesBeforePlateIdx = new ArrayList<Integer>();
		var candleIndexes = new ArrayList<Integer>();
		var plateCount = 0;
		for (var i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '|') {
				candlesBeforePlateIdx.add(plateCount);
				candleIndexes.add(i);
			} else {
				// ! We cannot base on `idx` diff due to consecutive candles, need to count '*' explicitly
				plateCount++;
			}
		}
		var result = new int[queries.length];
		for (var i = 0; i < queries.length; i++) {
			var query = queries[i];
			var lowerCeiling = leftmostIdx(query[0], candleIndexes);
			var higherFloor = rightmostIdx(query[1], candleIndexes);
			if (lowerCeiling < higherFloor) {
				if (lowerCeiling == -1) {
					lowerCeiling = 0;
				}
				result[i] =
						candlesBeforePlateIdx.get(higherFloor) - candlesBeforePlateIdx.get(lowerCeiling);
			}
		}
		return result;
	}

	// ! Ceiling - leftmost >= value
	private static int leftmostIdx(int value, List<Integer> candleIdx) {
		var left = 0;
		var right = candleIdx.size() - 1;
		while (left < right) {
			var mid = left + (right - left) / 2;
			if (candleIdx.get(mid) >= value) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}

	// ! Floor - rightmost <= value
	private static int rightmostIdx(int value, List<Integer> candleIdx) {
		var left = 0;
		var right = candleIdx.size() - 1;
		while (left <= right) {
			var mid = left + (right - left) / 2;
			if (candleIdx.get(mid) <= value) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return right;
	}

	static void main() {
		var platesBetweenCandles = new PlatesBetweenCandles();
		var s = "**|**|***|";
		var queries = new int[][] {{2, 5}, {5, 9}};
		var result = platesBetweenCandles.platesBetweenCandles(s, queries);
		System.out.println(Arrays.stream(result).mapToObj(r -> r + " ").toList());
		s = "***|**|*****|**||**|*";
		queries = new int[][] {{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}};
		result = platesBetweenCandles.platesBetweenCandles(s, queries);
		System.out.println(Arrays.stream(result).mapToObj(r -> r + " ").toList());
	}
}
