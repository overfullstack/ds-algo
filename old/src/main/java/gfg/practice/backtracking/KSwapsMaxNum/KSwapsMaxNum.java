package gfg.practice.backtracking.KSwapsMaxNum;

import java.util.Scanner;

/** Created by gakshintala on 6/27/16. */
public class KSwapsMaxNum {
	static void main() {
		var scn = new Scanner(System.in);
		var tests = scn.nextInt();
		while (tests-- > 0) {
			var swaps = scn.nextInt();
			var str = scn.next();
			System.out.println(findMaxAfterKSwaps(str, swaps, Long.parseLong(str)));
		}
	}

	// Simply swapping the highest to left-end won't work.
	// Ex: 7899 for 2 swaps -> 9879 -> 9978. But real answer is 9987
	private static long findMaxAfterKSwaps(String str, int swaps, long max) {
		// Base Condition
		if (swaps > 0 && !isDigitsDescendingSorted(max)) {
			var len = str.length();
			// Loop, Checking combinations by swapping every char with the every greater char following
			// it.
			for (var i = 0; i < len - 1; i++) {
				for (var j = i + 1; j < len; j++) {

					// isValid Condition
					if (str.charAt(i) < str.charAt(j)) {
						str = swap(str, i, j);

						var curVal = Long.parseLong(str);
						if (curVal > max) {
							max = curVal;
							if (isDigitsDescendingSorted(max)) { // We can stop when all digits are in descending
								// order coz that is the max num possible with those set of digits
								return max;
							}
						}

						max = findMaxAfterKSwaps(str, swaps - 1, max);
						str = swap(str, i, j); // backtracking
					}
				}
			}
		}
		return max;
	}

	private static boolean isDigitsDescendingSorted(long num) {
		var digit1 = (int) (num % 10);
		while (num > 10) {
			num /= 10;
			var digit2 = (int) (num % 10);
			if (digit2 < digit1) {
				return false;
			}
			digit1 = digit2;
		}
		return true;
	}

	private static String swap(String str, int i, int j) {
		var strArr = str.toCharArray();
		var temp = strArr[i];
		strArr[i] = strArr[j];
		strArr[j] = temp;
		return new String(strArr);
	}
}
