package gfg.practice.arrays.TwiceMinGTMax;

import java.util.Scanner;

/** Created by gakshintala on 6/20/16. */
public class TwiceMinGTMax {
	public static void main(String[] args) {
		var scn = new Scanner(System.in);
		var tests = scn.nextInt();
		while (tests-- > 0) {
			var len = scn.nextInt();
			var arr = new int[len];
			fillArray(arr, scn);
			System.out.println(minElementsToRemove(arr));
		}
	}

	private static int minElementsToRemove(int[] arr) {
		var len = arr.length;
		int maxSubArrayStart = -1, maxSubArrayEnd = 0;
		for (var start = 0; start < len; start++) {
			int min = arr[start], max = arr[start];
			for (var end = start + 1; end < len; end++) {
				min = Math.min(min, arr[end]);
				max = Math.max(max, arr[end]);

				// If this violates, we just encountered a new max or min at the end. We cannot have a
				// contiguous sub-array accommodating this new element. So we trim on the opposite side (no
				// problem
				// since we have longest subarray count constantly stored) until we reach subarray that
				// fulfills the
				// contract.
				if (2 * min <= max) {
					break;
				}

				// This is constantly updated whenever the above contract is satisfied. We cannot place it
				// in above
				// break statement as sometimes, it may not break at all.
				if (end - start > maxSubArrayEnd - maxSubArrayStart || maxSubArrayStart == -1) {
					maxSubArrayStart = start;
					maxSubArrayEnd = end;
				}
			}
		}
		if (maxSubArrayStart == -1) {
			return len;
		}
		return len - (maxSubArrayEnd - maxSubArrayStart + 1);
	}

	private static void fillArray(int[] arr, Scanner scn) {
		for (var i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}
	}
}
