package practice.binarysearch;

/* 22 Oct 2025 07:13 */

/**
 * [1287. Element Appearing More Than 25% In Sorted
 * Array](https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/)
 */
public class ElementAppearingMoreThan25_InSortedArray {
	public int findSpecialInteger(int[] arr) {
		if (arr.length == 0) {
			return -1;
		}
		if (arr.length < 4) {
			return arr[0];
		}
		var windowLen = arr.length / 4;
		var pos = new double[] {0, 1 / 4d, 1 / 2d, 3 / 4d, 1};
		for (var i = 1; i < pos.length - 1; i++) {
			var leftmostIdx =
					leftmostIdx(
							arr[(int) (arr.length * pos[i])],
							(int) (arr.length * pos[i - 1]),
							(int) (arr.length * pos[i + 1]),
							arr);
			if (arr[leftmostIdx + windowLen] == arr[leftmostIdx]) { // ! No `-1` as we need more than 25%
				return arr[leftmostIdx];
			}
		}
		return -1;
	}

	private static int leftmostIdx(int value, int left, int right, int[] arr) {
		while (left < right) {
			var mid = left + (right - left) / 2;
			if (value <= arr[mid]) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}

	static void main() {
		var elementAppearingMoreThan25_InSortedArray = new ElementAppearingMoreThan25_InSortedArray();
		System.out.println(
				elementAppearingMoreThan25_InSortedArray.findSpecialInteger(
						new int[] {1, 2, 2, 6, 6, 6, 6, 7, 10})); // 6
		System.out.println(
				elementAppearingMoreThan25_InSortedArray.findSpecialInteger(new int[] {1, 1})); // 1
	}
}
