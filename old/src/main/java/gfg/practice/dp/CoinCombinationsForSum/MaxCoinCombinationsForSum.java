package gfg.practice.dp.CoinCombinationsForSum;

import java.util.Scanner;

/** Created by gakshintala on 4/22/16. */
public class MaxCoinCombinationsForSum {
	static void main() {
		var scn = new Scanner(System.in);
		var testCases = scn.nextInt();
		while (testCases-- > 0) {
			var len = scn.nextInt();
			var arr = readArray(scn, len);
			var sum = scn.nextInt();
			System.out.println(maximumPossibleCombinations(arr, sum));
		}
	}

	private static int maximumPossibleCombinations(int[] coins, int sum) {
		var table = new int[sum + 1];
		table[0] = 1; // for 0 sum, 1 solution - Don't include any coin
		// For every coin, loop through all the sums
		for (var coin : coins) { // Because we have unlimited coins, we can use a coin for all the sum.
			// If you include this coin, a minimum sum you make starts from that coin
			for (var j = coin; j <= sum; j++) { // Only coins <= sum allowed
				table[j] += table[j - coin]; // including the coin
			}
		}
		return table[sum];
	}

	private static int[] readArray(Scanner scn, int len) {
		var arr = new int[len];
		for (var i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}
		return arr;
	}
}
