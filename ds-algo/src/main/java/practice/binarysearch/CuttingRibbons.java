package practice.binarysearch;

import java.util.Arrays;

/* 27 Sep 2025 20:23 */

/** [3734 · Cutting Ribbons](https://www.lintcode.com/problem/3734/) */
public class CuttingRibbons {
	// * RightMost = MaxLength
	public int maxLength(int[] ribbons, int k) {
		var left = 1;
		var right = Arrays.stream(ribbons).max().orElseThrow();
		while (left <= right) {
			var mid = left + (right - left) / 2;
			if (ribbonCount(ribbons, mid) >= k) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return right;
	}

	private static int ribbonCount(int[] ribbons, int lengthToCut) {
		return Arrays.stream(ribbons).map(r -> r / lengthToCut).sum();
	}

	static void main() {
		var obj = new CuttingRibbons();
		System.out.println(obj.maxLength(new int[] {9, 7, 5}, 3)); // 5
		System.out.println(obj.maxLength(new int[] {9, 4, 1}, 3)); // 4
		System.out.println(obj.maxLength(new int[] {7, 5, 9}, 4)); // 4
		System.out.println(obj.maxLength(new int[] {5, 7, 9}, 22)); // 0
		System.out.println(obj.maxLength(new int[] {1, 2, 3}, 7)); // 0
		System.out.println(obj.maxLength(new int[] {1, 2, 3}, 6)); // 1
	}
}
