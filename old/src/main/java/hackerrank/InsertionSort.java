package hackerrank;

import java.util.Scanner;

public class InsertionSort {

	static void main() {
		var a = readIntoArray();
		sort(a);
		var n = a.length;
		var minDiffIndex = new int[n - 1];
		var minDiff = Math.abs(a[1] - a[0]);
		var k = 0;
		minDiffIndex[k++] = 1;
		for (var i = 2; i < n; i++) {
			var diff = Math.abs(a[i] - a[i - 1]);
			if (minDiff > diff) {
				minDiff = diff;
				k = 0;
				minDiffIndex[k++] = i;
			} else if (minDiff == diff) minDiffIndex[k++] = i;
		}

		for (var i = 0; i < k; i++) {
			System.out.print(" " + a[minDiffIndex[i] - 1] + " " + a[minDiffIndex[i]]);
		}
	}

	public static long[] readIntoArray() {
		var scn = new Scanner(System.in);
		var n = scn.nextInt();
		var a = new long[n];

		for (var i = 0; i < n; i++) {
			a[i] = scn.nextInt();
		}

		return a;
	}

	public static void sort(long[] a) {
		for (var i = 1; i < a.length; i++) {
			var j = i - 1;
			var temp = a[i];
			var flag = false;
			for (; j >= 0; j--) {
				if (temp < a[j]) {
					flag = true;
					a[j + 1] = a[j];
				} else break;
			}
			if (flag) {
				a[j + 1] = temp;
			}
		}
	}
}
