package practice.binarysearch;

/* 27 Aug 2025 18:31 */

import java.util.Arrays;

/** [875. Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/) */
public class KokoEatingBananas {
	public int minEatingSpeed(int[] piles, int h) {
		var left = 1;
		var right = Arrays.stream(piles).max().orElse(0);

		// ! MinNoOfHours = Leftmost
		while (left < right) {
			var mid = left + (right - left) / 2;
			if (isMinimumSpeed(piles, mid, h)) { // ! Ceiling for speed
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}

	private static boolean isMinimumSpeed(int[] piles, int minSpeed, int h) {
		var noOfHours =
				Arrays.stream(piles).map(pile -> pile / minSpeed + (pile % minSpeed == 0 ? 0 : 1)).sum();
		return noOfHours <= h;
	}

	static void main() {
		System.out.println(new KokoEatingBananas().minEatingSpeed(new int[] {3, 6, 7, 11}, 8)); // 4
		System.out.println(
				new KokoEatingBananas().minEatingSpeed(new int[] {30, 11, 23, 4, 20}, 5)); // 30
		System.out.println(
				new KokoEatingBananas().minEatingSpeed(new int[] {30, 11, 23, 4, 20}, 6)); // 23
	}
}
