package gfg.practice.arrays.MajorityElement;

import java.util.Scanner;

/** Created by gakshintala on 7/3/16. */
public class MajorityElement {
	static void main() {
		var scn = new Scanner(System.in);
		var tests = scn.nextInt();
		while (tests-- > 0) {
			var len = scn.nextInt();
			var arr = new int[len];
			fillArray(arr, scn);
			printMajorityElement(arr);
		}
	}

	private static void printMajorityElement(int[] arr) {
		int count = 1, curEle = arr[0];
		// Only Majority element can have non-zero count.
		for (var i = 1; i < arr.length; i++) {
			if (arr[i] == curEle) {
				count++;
			} else {
				count--;
			}
			if (count == 0) {
				curEle = arr[i];
				count = 1;
			}
		}
		if (isElementMajority(
				curEle, arr)) { // As there is a case where no majority element exits, we are verifying.
			System.out.println(curEle);
		} else {
			System.out.println("NO Majority Element");
		}
	}

	private static boolean isElementMajority(int curEle, int[] arr) {
		var count = 0;
		for (var ele : arr) {
			if (ele == curEle) {
				count++;
			}
		}
		return count > (arr.length / 2);
	}

	private static void fillArray(int[] arr, Scanner scn) {
		for (var i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}
	}
}
