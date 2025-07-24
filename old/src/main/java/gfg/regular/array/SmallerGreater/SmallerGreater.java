package gfg.regular.array.SmallerGreater;

/** Created by gakshintala on 4/14/16. */
public class SmallerGreater {
	static void main() {
		int[] arr = {5, 1, 4, 3, 6, 8, 10, 7, 9};
		System.out.println(findIndex(arr));
	}

	private static int findIndex(int[] arr) {
		var len = arr.length;
		var leftMax = fillLeftMax(arr, len);

		var rightMin = arr[len - 1];
		for (var i = len - 2;
				i >= 0;
				i--) { // Traversing right to left tracking rightMin and using leftMax array.
			rightMin = Math.min(arr[i + 1], rightMin);
			if (arr[i] < rightMin && arr[i] > leftMax[i]) {
				return i;
			}
		}
		return -1;
	}

	private static int[] fillLeftMax(int[] arr, int len) {
		var leftMax = new int[arr.length];
		leftMax[0] = arr[0];
		for (var i = 1; i < len; i++) {
			leftMax[i] = Math.max(arr[i - 1], leftMax[i - 1]);
		}
		return leftMax;
	}
}
