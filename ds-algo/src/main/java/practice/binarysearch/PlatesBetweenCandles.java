package practice.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** [2055. Plates Between Candles](https://leetcode.com/problems/plates-between-candles) */
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
			var leftmostCandleIdx = leftmostIdx(query[0], candleIndexes);
			var rightMostCandleIdx = rightmostIdx(query[1], candleIndexes);
			if (leftmostCandleIdx < rightMostCandleIdx) {
				result[i] =
						candlesBeforePlateIdx.get(rightMostCandleIdx)
								- candlesBeforePlateIdx.get(leftmostCandleIdx);
			}
		}
		return result;
	}

	private static int leftmostIdx(int idx, List<Integer> candleIdx) {
		var left = 0;
		var right = candleIdx.size() - 1;
		while (left < right) {
			var mid = left + (right - left) / 2;
			if (idx <= candleIdx.get(mid)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}

	private static int rightmostIdx(int idx, List<Integer> candleIdx) {
		var left = 0;
		var right = candleIdx.size() - 1;
		while (left <= right) {
			var mid = left + (right - left) / 2;
			if (idx >= candleIdx.get(mid)) {
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
		System.out.println(Arrays.stream(result).mapToObj(r -> r + " ").toList()); // [2, 3]
		s = "***|**|*****|**||**|*";
		queries = new int[][] {{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}};
		result = platesBetweenCandles.platesBetweenCandles(s, queries);
		System.out.println(Arrays.stream(result).mapToObj(r -> r + " ").toList()); // [9, 0, 0, 0, 0]
	}
}
