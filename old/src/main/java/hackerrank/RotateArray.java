package hackerrank;

import java.util.Arrays;

/** Created by gakshintala on 3/14/16. */
public class RotateArray {
	public static void main(String[] args) {
		int[] a = {1, 1, 2, 3, 5};
		System.out.println(Arrays.toString(rotateArray(a, 42)));
	}

	private static int[] rotateArray(int[] a, int k) {
		var len = a.length;
		if (len == 0) return a;
		if (k > len) k = k % len;
		if (k == 0) return a;

		for (var origin = 0; origin < gcd(len, k); origin++) {
			var curIndex = origin;
			var nextIndex = findIndex(origin, k, len);
			var temp = a[curIndex];
			while (true) {
				a[curIndex] = a[nextIndex];
				curIndex = nextIndex;
				nextIndex = findIndex(nextIndex, k, len);
				if (nextIndex == origin) break;
			}
			a[curIndex] = temp;
		}
		return a;
	}

	private static int findIndex(int i, int k, int len) {
		return (i - k >= 0 ? i - k : (i - k) + len);
	}

	public static int gcd(int a, int b) {
		while (true) {
			if (a % b == 0) return b;
			return gcd(b, a % b);
		}
	}
}
