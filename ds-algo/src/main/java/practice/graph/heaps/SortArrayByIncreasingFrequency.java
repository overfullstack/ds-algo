package practice.graph.heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/* 07 Sep 2025 14:00 */

/**
 * [1636. Sort Array by Increasing
 * Frequency](https://leetcode.com/problems/sort-array-by-increasing-frequency)
 */
public class SortArrayByIncreasingFrequency {
	public int[] frequencySort(int[] nums) {
		var freqMap = new HashMap<Integer, Integer>();
		for (var num : nums) {
			freqMap.merge(num, 1, (old, _) -> old + 1);
		}
		var minHeap =
				new PriorityQueue<>(
						Comparator.<Map.Entry<Integer, Integer>>comparingInt(Map.Entry::getValue)
								.thenComparing(
										Comparator.<Map.Entry<Integer, Integer>>comparingInt(Map.Entry::getKey)
												.reversed()));
		minHeap.addAll(freqMap.entrySet());
		var result = new int[nums.length];
		var i = 0;
		while (!minHeap.isEmpty()) {
			var entry = minHeap.poll();
			var num = entry.getKey();
			var freq = entry.getValue();
			Arrays.fill(result, i, i + freq, num);
			i += freq;
		}
		return result;
	}

	static void main() {
		var sol = new SortArrayByIncreasingFrequency();
		System.out.println(Arrays.toString(sol.frequencySort(new int[] {1, 1, 2, 2, 2, 3})));
		System.out.println(Arrays.toString(sol.frequencySort(new int[] {2, 3, 1, 3, 2})));
		System.out.println(
				Arrays.toString(sol.frequencySort(new int[] {-1, 1, -6, 4, 5, -6, 1, 4, 1})));
	}
}
