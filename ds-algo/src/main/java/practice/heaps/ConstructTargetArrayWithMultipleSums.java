package practice.heaps;

/* 20 Oct 2025 12:04 */

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * [1354. Construct Target Array With Multiple
 * Sums](https://leetcode.com/problems/construct-target-array-with-multiple-sums/)
 */
public class ConstructTargetArrayWithMultipleSums {
	public boolean isPossible(int[] target) {
		if (target.length == 1) {
			return target[0] == 1;
		}
		final var maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
		// ! Going in reverse, from target to all 1s
		var sum = 0;
		for (var num : target) {
			maxHeap.add(num);
			sum += num;
		}
		while (maxHeap.peek() != 1) {
			final var cur = maxHeap.poll();
			// ! This happens only for 2 length arr. This avoids getting into `next == 0` below
			if (sum - cur == 1) {
				return true;
			}
			final var next = cur % (sum - cur);
			if (next == 0 || next == cur) {
				return false;
			}
			sum = sum - cur + next;
			maxHeap.add(next);
		}
		return true;
	}

	static void main() {
		final var constructTargetArrayWithMultipleSums = new ConstructTargetArrayWithMultipleSums();
		System.out.println(
				constructTargetArrayWithMultipleSums.isPossible(new int[] {9, 3, 5})); // true
		System.out.println(constructTargetArrayWithMultipleSums.isPossible(new int[] {2})); // false
		System.out.println(
				constructTargetArrayWithMultipleSums.isPossible(new int[] {1, 1, 1, 2})); // false
		System.out.println(
				constructTargetArrayWithMultipleSums.isPossible(new int[] {1, 1, 1})); // true
		System.out.println(constructTargetArrayWithMultipleSums.isPossible(new int[] {8, 5})); // true
		System.out.println(constructTargetArrayWithMultipleSums.isPossible(new int[] {8, 7})); // true
		System.out.println(
				constructTargetArrayWithMultipleSums.isPossible(new int[] {1, 1000000})); // true
	}
}
