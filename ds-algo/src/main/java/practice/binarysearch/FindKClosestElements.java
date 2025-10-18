package practice.binarysearch;

/* 01 Sep 2025 21:35 */

import java.util.Arrays;
import java.util.List;

/** [658. Find K Closest Elements](https://leetcode.com/problems/find-k-closest-elements/) */
public class FindKClosestElements {
	// ! arr is sorted
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		var left = 0;
		var right = arr.length - k;
		while (left < right) {
			var mid = left + (right - left) / 2;
			if (x - arr[mid] <= arr[mid + k] - x) { // ! Leftmost where delta with `x` is same
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return Arrays.stream(arr, left, left + k).boxed().toList();
	}

	static void main() {
		var obj = new FindKClosestElements();
		System.out.println(obj.findClosestElements(new int[] {1, 2, 3, 4, 5}, 4, 3));
		System.out.println(obj.findClosestElements(new int[] {1, 1, 2, 3, 4, 5}, 4, -1));
	}
}
