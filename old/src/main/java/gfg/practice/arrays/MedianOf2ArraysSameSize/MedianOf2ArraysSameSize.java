package gfg.practice.arrays.MedianOf2ArraysSameSize;

import java.util.Arrays;
import java.util.Scanner;

/** Created by gakshintala on 7/2/16. */
public class MedianOf2ArraysSameSize {
	static void main() {
		var scn = new Scanner(System.in);
		var tests = scn.nextInt();
		while (tests-- > 0) {
			var len = scn.nextInt();
			var arr1 = new int[len];
			fillArray(arr1, scn);
			var arr2 = new int[len];
			fillArray(arr2, scn);
			System.out.println(medianOf2Arrays(arr1, arr2));
		}
	}

	private static int medianOf2Arrays(int[] arr1, int[] arr2) {
		var len = arr1.length;
		if (len <= 0) {
			return -1;
		}
		if (len == 1) {
			return (arr1[0] + arr2[0]) / 2;
		}
		if (len == 2) {
			return (Math.max(arr1[0], arr2[0]) + Math.min(arr1[1], arr2[1])) / 2;
		}

		var m1 = medianOfArray(arr1);
		var m2 = medianOfArray(arr2);

		if (m1 == m2) {
			return m1;
		}

		if (m1 > m2) { // arr1 has bigger elements, balance it out by talking left of arr1 and right of
			// arr2
			if (len % 2 == 0) {
				return medianOf2Arrays(
						Arrays.copyOfRange(arr1, 0, len / 2 + 1),
						Arrays.copyOfRange(arr2, len / 2 - 1, len)); // Adding extra elements len + 1, len - 1
			}
			return medianOf2Arrays(
					Arrays.copyOfRange(arr1, 0, len / 2), Arrays.copyOfRange(arr2, len / 2, len));
		} else {
			if (len % 2 == 0) {
				return medianOf2Arrays(
						Arrays.copyOfRange(arr2, 0, len / 2 + 1), Arrays.copyOfRange(arr1, len / 2 - 1, len));
			}
			return medianOf2Arrays(
					Arrays.copyOfRange(arr2, 0, len / 2), Arrays.copyOfRange(arr1, len / 2, len));
		}
	}

	private static int medianOfArray(int[] arr) {
		var len = arr.length; // length instead of length - 1, left shall hv extra element if even.
		var mid = len / 2;
		if (len % 2 == 1) {
			return arr[mid];
		} else {
			return (arr[mid] + arr[mid - 1]) / 2; // mid-1 as left has extra element.
		}
	}

	private static void fillArray(int[] arr, Scanner scn) {
		for (var i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}
	}
}
