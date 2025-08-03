package gfg.regular.array.KthSmallest;

import static ds.Utils.swap;

/** Created by gakshintala on 6/11/16. */
public class KthSmallest {
	static void main() {
		int[] arr = {7, 10, 4, 3, 20, 15};
		var k = 5;
		System.out.println(quickSelect(arr, 0, arr.length - 1, k));
	}

	private static int quickSelect(int[] arr, int l, int r, int k) {
		if (k > 0 && k <= r - l + 1) {
			final var pos = partition(arr, l, r);
			final var relativePosFromLeft = pos - l + 1;
			if (relativePosFromLeft == k) {
				return arr[pos];
			}
			if (relativePosFromLeft > k) {
				// we can consider from pos-1, as the current element in pos has found its fixed position
				// and cannot
				// be the kth element.
				return quickSelect(arr, l, pos - 1, k);
			}
			// since all the elements below pos are already less, elements between l and pos are pos-l+1
			return quickSelect(arr, pos + 1, r, k - relativePosFromLeft);
		}
		return -1;
	}

	// Alternate way to partition, more elegant
	private static int partition(int[] arr, int l, int r) {
		var i = l;
		// Shifting all the lesser elements to left
		for (var j = l; j < r; j++) {
			if (arr[j]
					<= arr[
							r]) { // If first element is greater than arr[r], we move ahead until we find a lesser
				// element.
				swap(arr, i, j);
				i++;
			}
		}
		swap(arr, i, r);
		return i;
	}
}
