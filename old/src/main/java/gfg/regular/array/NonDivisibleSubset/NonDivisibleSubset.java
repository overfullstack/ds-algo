package gfg.regular.array.NonDivisibleSubset;

/** Created by Gopala Akshintala on 3/13/17. */
public class NonDivisibleSubset {
	static void main() {
		int[] arr = {3, 7, 2, 9, 1};
		var k = 3;
		System.out.println(maxLenOfNonDivisibleSubset(arr, k));
	}

	private static int maxLenOfNonDivisibleSubset(int[] arr, int k) {
		var len = arr.length;
		var reminderArr = new int[len];

		for (int value : arr) {
			reminderArr[value % k]++;
		}

		if (k % 2 == 0) {
			reminderArr[k / 2] = Math.min(reminderArr[k / 2], 1);
		}

		var result = Math.min(reminderArr[0], 1);
		for (var i = 1; i <= k / 2; i++) {
			result +=
					Math.max(reminderArr[i], reminderArr[k - i]); // we can either include these or those
		}
		return result;
	}
}
