package practice;

/* 27 Aug 2025 18:31 */

import java.util.Arrays;

public class KokoEatingBananas {
	public int minEatingSpeed(int[] piles, int h) {
		var left = 1;
		var right = Arrays.stream(piles).max().orElse(0);

		// ! Leftmost with inversely proportional condition
		while (left < right) {
			var mid = left + (right - left) / 2;
			var noOfHours = noOfHours(piles, mid);
			if (noOfHours <= h) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	private static int noOfHours(int[] piles, int speed) {
		return Arrays.stream(piles).map(pile -> pile / speed + (pile % speed == 0 ? 0 : 1)).sum();
	}

	static void main() {
		System.out.println(new KokoEatingBananas().minEatingSpeed(new int[] {3, 6, 7, 11}, 8)); // 4
		System.out.println(
				new KokoEatingBananas().minEatingSpeed(new int[] {30, 11, 23, 4, 20}, 5)); // 30
		System.out.println(
				new KokoEatingBananas().minEatingSpeed(new int[] {30, 11, 23, 4, 20}, 6)); // 23
	}
}
