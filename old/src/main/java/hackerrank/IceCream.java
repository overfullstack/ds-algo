package hackerrank;

import java.util.Arrays;
import java.util.Scanner;

public class IceCream {

	static void main() {
		var scn = new Scanner(System.in);
		var testCases = scn.nextInt();
		for (var c = 0; c < testCases; c++) {
			var sum = scn.nextInt();
			var n = scn.nextInt();

			var a = new int[n];
			readIntoArray(scn, n, a);
			var aSort = sort(a, n);
			int index1 = 0, index2 = 0;

			for (var i = 0; i <= n / 2; i++) {
				index1 = i;
				var diff = sum - aSort[i];
				index2 = search(aSort, i, diff);
				if (index2 != -1) break;
			}
			index1 = find(a, aSort[index1], -1);
			index2 = find(a, aSort[index2], index1 - 1);

			if (index1 < index2) System.out.println(index1 + " " + index2);
			else System.out.println(index2 + " " + index1);
		}
	}

	public static int find(int[] a, int val, int index1) {
		for (var i = 0; i < a.length; i++) {
			if (i != index1 && a[i] == val) return i + 1;
		}

		return -1;
	}

	public static int search(int[] aSort, int currentIndex, int diff) {
		int low = 0, high = aSort.length - 1;
		while (low <= high) {
			var valIndex = (low + high) / 2;

			var val = aSort[valIndex];
			if (diff == val) return valIndex;
			else if (diff < val) high = valIndex - 1;
			else low = valIndex + 1;
		}

		return -1;
	}

	public static void readIntoArray(Scanner scn, int n, int[] a) {
		for (var i = 0; i < n; i++) {
			a[i] = scn.nextInt();
		}
	}

	public static int[] sort(int[] a, int n) {
		var aSort = Arrays.copyOf(a, n);
		for (var i = 1; i < n; i++) {
			var temp = aSort[i];
			var flag = false;
			var j = i - 1;
			for (; j >= 0; j--) {
				if (temp < aSort[j]) {
					flag = true;
					aSort[j + 1] = aSort[j];
				} else break;
			}
			if (flag) aSort[j + 1] = temp;
		}

		return aSort;
	}
}
