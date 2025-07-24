package gfg.regular.array.PairWithSum;

import static gfg.ds.Utils.quickSort;

/** Created by gakshintala on 6/10/16. */
public class PairWithSum {
	static void main() {
		var sum = 16;
		int[] arr = {1, 4, 45, 6, 10, -8};
		quickSort(arr);

		var left = 0;
		var right = arr.length - 1;

		while (left < right) {
			var lrSum = arr[left] + arr[right];
			if (lrSum == sum) {
				System.out.println("(" + arr[left] + "," + arr[right] + ")");
				break;
			} else if (lrSum < sum) left++;
			else right--;
		}
	}
}
