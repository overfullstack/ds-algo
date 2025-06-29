package gfg.practice.arrays.SubArrayWithSum;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/** Created by gakshintala on 6/29/16. */
public class SubArrayWithSum {
	public static void main(String[] args) {
		var scn = new Scanner(System.in);
		var tests = scn.nextInt();
		while (tests-- > 0) {
			var len = scn.nextInt();
			var sum = scn.nextInt();
			var arr = new int[len];
			fillArray(arr, scn);
			printIndicesOfSubArray(arr, sum);
			printIndicesOfSubArrayWithNegativeNums(arr, sum);
		}
	}

	private static void printIndicesOfSubArray(int[] arr, int sum) {
		int curSum = arr[0], start = 0, len = arr.length;
		var foundFlag = false;
		// i <= len because, curSum made by last digit also need to go through trimming. So iterate one
		// extra time
		// after all the digits are over.
		for (var i = 1; i <= len; i++) {
			// Trim from start till curSum falls below sum. start < i - 1 is to avoid trimming from empty
			// list
			// Although this is unsorted, trimming from start coz, the including indices from start till
			// here anyway
			// couldn't make them sum, so excluding them.
			while (curSum > sum && start < i - 1) {
				curSum -= arr[start];
				start++;
			}
			if (curSum == sum) {
				foundFlag = true;
				System.out.println(start + 1 + " " + i); // Print 1-based indices
				break;
			}
			// Just to avoid this in the last iteration.
			if (i != len) {
				curSum += arr[i];
			}
		}
		if (!foundFlag) {
			System.out.println(-1);
		}
	}

	private static void printIndicesOfSubArrayWithNegativeNums(int[] arr, int sum) {
		int curSum = arr[0], start = 0, len = arr.length;
		var foundFlag = false;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(curSum, 0);
		// No need for i<=len coz, last digit case shall be checked in the last iteration itself
		for (var i = 1; i < len; i++) {
			curSum += arr[i];
			if (map.get(curSum - sum) != null) {
				// Found subArray from 0 to some point which can make curSum - sum, since it's curSum now,
				// we crossed a point
				// curSum-sum and from that point to current point shall make sum.
				foundFlag = true;
				System.out.println(
						(map.get(curSum - sum) + 2)
								+ " "
								+ i
								+ 1); // Print 1-based indices, + 2 as it is exclusive
				break;
			}
			map.put(curSum, i);
		}
		if (!foundFlag) {
			System.out.println(-1);
		}
	}

	private static void fillArray(int[] arr, Scanner scn) {
		for (var i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}
	}
}
