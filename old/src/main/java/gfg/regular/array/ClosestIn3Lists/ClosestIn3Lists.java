package gfg.regular.array.ClosestIn3Lists;

/** Created by gakshintala on 6/11/16. */
public class ClosestIn3Lists {
	static void main() {
		int[] a = {1, 4, 10};
		int[] b = {2, 15, 20};
		int[] c = {10, 12};

		printClosestElements(a, b, c);
	}

	private static void printClosestElements(int[] a, int[] b, int[] c) {
		int i = 0, j = 0, k = 0;
		var diff = Integer.MAX_VALUE;
		int aResult = a[0], bResult = b[0], cResult = c[0];
		// If one of the list spills over the end, ignore the other lists as the elements as all of them
		// must be less closer.
		while (i < a.length && j < b.length && k < c.length) {
			// A set of number is Closest when the diff between their min and max is minimum
			var minOf3 = Math.min(a[i], Math.min(b[j], c[k]));
			var maxOf3 = Math.max(a[i], Math.max(b[j], c[k]));

			if (maxOf3 - minOf3 < diff) {
				diff = maxOf3 - minOf3;
				aResult = a[i];
				bResult = b[j];
				cResult = c[k];
			}
			// Which means all 3 elements are same
			if (diff == 0) {
				break;
			}

			// Incrementing the list with min number to decrease the diff
			if (minOf3 == a[i]) i++;
			if (minOf3 == b[j]) j++;
			if (minOf3 == c[k]) k++;
		}
		System.out.println(aResult + " " + bResult + " " + cResult);
	}
}
