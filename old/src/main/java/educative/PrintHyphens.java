package educative;

import java.util.*;

class PrintHyphens {
	public static String repeat(String str, int pValue) {
		String out = "";
		for (int i = 0; i < pValue; i++) {
			out += str;
		}
		return out;
	}
}

class FBVersion {
	public static int v;

	static boolean isBadVersion(int s) {
		return s >= v;
	}

	static int[] firstBadVersion(int n) {
		int[] result = new int[2];
		int first = 1;
		int last = n;
		int calls = 0;

		while (first <= last) {
			int mid = first + (last - first) / 2;

			if (isBadVersion(mid)) {
				last = mid - 1;
			} else {
				first = mid + 1;
			}

			calls += 1;
		}

		result[0] = first;
		result[1] = calls;
		return result;
	}

	// Driver code
	public static void main(String args[]) {
		int[] testCaseVersions = new int[] {38, 13, 29, 40, 23};
		int[] firstBadVersion = new int[] {28, 10, 10, 28, 19};

		for (int i = 0; i < testCaseVersions.length; i++) {
			v = firstBadVersion[i];

			if (i > 0) {
				System.out.println();
			}

			System.out.println(i + 1 + ".\tNumber of versions: " + testCaseVersions[i]);
			int[] result = firstBadVersion(testCaseVersions[i]);
			System.out.println(
					"\n\tFirst bad version: " + result[0] + ". Found in " + result[1] + " API calls.");
			System.out.println(new String(new char[100]).replace('\0', '-'));
		}
	}
}
