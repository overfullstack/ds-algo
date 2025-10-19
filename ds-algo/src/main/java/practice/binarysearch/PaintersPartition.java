package practice.binarysearch;

/* 19 Oct 2025 13:12 */

import java.util.ArrayList;
import java.util.List;

/**
 * [Painters Partition](https://www.naukri.com/code360/problems/painter-s-partition-problem_1089557)
 */
public class PaintersPartition {
	public static int findLargestMinDistance(ArrayList<Integer> boards, int k) {
		var left = boards.stream().mapToInt(i -> i).max().orElseThrow();
		var right = boards.stream().mapToInt(i -> i).sum();
		while (left < right) {
			var mid = left + (right - left) / 2;
			if (partitionCount(mid, boards) <= k) { // ! k is Ceiling
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}

	private static int partitionCount(int area, List<Integer> boards) {
		var sum = 0;
		var k = 0;
		for (var board : boards) {
			sum += board;
			if (sum > area) {
				sum = board;
				k++;
			}
		}
		return k + 1;
	}

	static void main() {
		var paintersPartition = new PaintersPartition();
		System.out.println(
				paintersPartition.findLargestMinDistance(
						new ArrayList<>(List.of(2, 1, 5, 6, 2, 3)), 2)); // 11
		System.out.println(
				paintersPartition.findLargestMinDistance(
						new ArrayList<>(List.of(10, 20, 30, 40)), 2)); // 60
		System.out.println(
				paintersPartition.findLargestMinDistance(new ArrayList<>(List.of(48, 90)), 2)); // 90
	}
}
