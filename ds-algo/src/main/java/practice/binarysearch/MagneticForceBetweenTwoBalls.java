package practice.binarysearch;

import java.util.Arrays;
import java.util.stream.IntStream;

/* 24 Oct 2025 21:14 */

/**
 * [1552. Magnetic Force Between Two
 * Balls](https://leetcode.com/problems/magnetic-force-between-two-balls/)
 */
public class MagneticForceBetweenTwoBalls {
	public int maxDistance(int[] position, int m) {
		Arrays.sort(position);
		var left =
				IntStream.range(1, position.length)
						.map(i -> position[i] - position[i - 1])
						.min()
						.orElseThrow();
		var right = (position[position.length - 1] - position[0]) / (m - 1);
		while (left <= right) {
			var mid = left + (right - left) / 2;
			if (isDistanceMaximum(mid, position, m)) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return right;
	}

	// ! Minimum is maximized => Place them as far as possible
	private static boolean isDistanceMaximum(int minDistance, int[] position, int m) {
		var ballCount = 1;
		var prev = position[0];
		for (var i = 1; i < position.length; i++) {
			// ! Distance between any two balls can be `minDistance` or more
			if (position[i] - prev >= minDistance) {
				ballCount++;
				prev = position[i];
			}
		}
		return ballCount >= m;
	}

	static void main() {
		var m = new MagneticForceBetweenTwoBalls();
		System.out.println(m.maxDistance(new int[] {1, 2, 3, 4, 7}, 3)); // 3
		System.out.println(m.maxDistance(new int[] {79, 74, 57, 22}, 4)); // 5
		System.out.println(m.maxDistance(new int[] {5, 4, 3, 2, 1, 1000000000}, 2)); // 999999999
	}
}
