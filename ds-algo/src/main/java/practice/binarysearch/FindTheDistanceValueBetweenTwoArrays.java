package practice.binarysearch;

import java.util.Arrays;

/* 24 Oct 2025 17:34 */

/**
 * [1385. Find the Distance Value Between Two
 * Arrays](https://leetcode.com/problems/find-the-distance-value-between-two-arrays/)
 */
public class FindTheDistanceValueBetweenTwoArrays {
	public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
		Arrays.sort(arr2); // ! Sort
		// ! num in arr1 is Invalid if any num in arr2 is in range [num - d, num + d]
		return arr1.length
				- (int) Arrays.stream(arr1).filter(num -> inRange(num - d, num + d, arr2)).count();
	}

	private static boolean inRange(int from, int to, int[] arr) {
		var left = 0;
		var right = arr.length - 1;
		while (left <= right) {
			var mid = left + (right - left) / 2;
			if (from <= arr[mid] && arr[mid] <= to) {
				return true;
			}
			if (from > arr[mid]) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return false;
	}

	static void main() {
		var findTheDistanceValueBetweenTwoArrays = new FindTheDistanceValueBetweenTwoArrays();
		System.out.println(
				findTheDistanceValueBetweenTwoArrays.findTheDistanceValue(
						new int[] {-3, 10, 2, 8, 0, 10}, new int[] {-9, -1, -4, -9, -8}, 9)); // 2
		System.out.println(
				findTheDistanceValueBetweenTwoArrays.findTheDistanceValue(
						new int[] {4, 5, 8}, new int[] {10, 9, 1, 8}, 2)); // 2
		System.out.println(
				findTheDistanceValueBetweenTwoArrays.findTheDistanceValue(
						new int[] {1, 4, 2, 3}, new int[] {-4, -3, 6, 10, 20, 30}, 3)); // 2
		System.out.println(
				findTheDistanceValueBetweenTwoArrays.findTheDistanceValue(
						new int[] {2, 1, 100, 3}, new int[] {-5, -2, 10, -3, 7}, 6)); // 1
	}
}
