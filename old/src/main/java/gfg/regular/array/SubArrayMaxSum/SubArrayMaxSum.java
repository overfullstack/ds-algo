package gfg.regular.array.SubArrayMaxSum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/** Created by gakshintala on 2/25/16. */
public class SubArrayMaxSum {

	static void main() {
		int[] arr = {-2, -3, 4, -1, -2, 1, 5, -3};
		final List<Integer> arrList = new ArrayList<>();
		IntStream.of(arr).forEach(arrList::add);
		System.out.println(
				findMaximumSubArrayContiguous(arrList).maxSum
						+ " "
						+ findMaximumSubArrayNonContiguous(arrList));
	}

	/**
	 * Kadane Algorithm.
	 *
	 * @param arr
	 * @return
	 */
	private static Subarray findMaximumSubArrayContiguous(List<Integer> arr) {
		var subarray = new Subarray(0, 0, 0);
		int curSum = 0, startIndex = 0, maxSum = 0;
		for (var i = 0; i < arr.size(); i++) {
			curSum += arr.get(i);
			if (curSum
					< 0) { // Nullifying the Negative portion of curSum by making it 0, and starting a fresh
				// subarray
				curSum = 0;
				startIndex = i + 1;
			} else if (curSum > maxSum) {
				maxSum = curSum;
				subarray = new Subarray(startIndex + 1, i + 1, maxSum);
			}
		}
		if (maxSum == 0) {
			subarray.maxSum = getMaxInArray(arr);
		}
		return subarray;
	}

	private static int findMaximumSubarrayContiguous2(List<Integer> arr) {
		int curSum = arr.get(0);
		int maxSoFar = arr.get(0);
		for (var i = 1; i < arr.size(); i++) {
			curSum = Math.max(curSum, curSum + arr.get(i));
			maxSoFar = Math.max(maxSoFar, curSum);
		}
		return maxSoFar;
	}

	private static int findMaximumSubArrayNonContiguous(List<Integer> arr) {
		var sum = arr.stream().filter(e -> e > 0).mapToInt(e -> e).sum();
		return (sum != 0) ? sum : arr.stream().mapToInt(e -> e).max().getAsInt();
	}

	private static int getMaxInArray(List<Integer> arr) {
		int max = arr.get(0);
		for (var i = 1; i < arr.size(); i++) {
			int val = arr.get(i);
			if (val > max) max = val;
		}
		return max;
	}
}

class Subarray {
	public int start;
	public int end;
	int maxSum;

	public Subarray(int start, int end, int maxSum) {
		this.start = start;
		this.end = end;
		this.maxSum = maxSum;
	}

	@Override
	public String toString() {
		return "(" + start + "," + end + "): " + maxSum;
	}
}
