package gfg.practice.dp.LongestIncreasingSubSequence;

import java.util.Arrays;
import java.util.Scanner;

/** Created by gakshintala on 6/11/16. */
public class LongestIncreasingSubSequence {
	public static void main(String[] args) {
		var scn = new Scanner(System.in);
		var testCases = scn.nextInt();
		while (testCases-- > 0) {
			var len = scn.nextInt();
			if (len == 0) { // This is only for a test case having len = 0
				System.out.println(0);
				continue;
			}
			var arr = readArray(scn, len);
			System.out.println(longestIncreasingSubSequence(arr));
		}
	}

	private static int longestIncreasingSubSequence(int[] arr) {
		var len = arr.length;
		var table = new int[len];
		table[0] = 1;
		for (var i = 1; i < len; i++) {
			table[i] = 1;
			for (var j = 0; j < i; j++) {
				// If you find an element less than the current element we are checking for, see if the
				// increasing
				// subsequence it contributes is max or not
				if (arr[j] < arr[i]) {
					table[i] = Math.max(table[i], table[j] + 1);
				}
			}
		}
		return Arrays.stream(table).max().getAsInt();
	}

	private static int[] readArray(Scanner scn, int len) {
		var arr = new int[len];
		for (var i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}
		return arr;
	}
}
