package practice;

/* 01 Sep 2025 21:35 */

import java.util.Arrays;
import java.util.List;

/** [658. Find K Closest Elements](https://leetcode.com/problems/find-k-closest-elements/) */
public class FindKClosestElements {
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		var left = 0;
		var right = arr.length - k;
		// ! Search for start for the k window
		// ! The window is `mid..<mid+k`, left and right help to move the window left or right
		// ! `left..right` shirks `mid..<mid+k` window starts inside `left..right` but can extend beyond
		// ! We keep moving window towards left or right based on which bound value is closer to x
		// ! to minimize the distance between `x` and window bound values
		// ! Imagine the window is oscillating around `x` with decreasing amplitude 
    // ! Trying to find even closer window with lower distances, and finally stops at the leftmost
		while (left < right) {
			var mid = left + (right - left) / 2;
			// ! This is measuring distance from lower and upper bound values (NOT indices)
			if (x - arr[mid] > arr[mid + k] - x) {
				// ! Move window to right. But if we step on a farther element on the right
				// ! it automatically makes `x` closer to lower bound so window shifts left again
				left = mid + 1;
			} else {
				right = mid;
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
