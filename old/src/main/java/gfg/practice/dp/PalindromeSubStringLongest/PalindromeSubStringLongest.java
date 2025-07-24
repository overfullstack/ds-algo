package gfg.practice.dp.PalindromeSubStringLongest;

import java.util.Scanner;

/** Created by gakshintala on 6/22/16. */
public class PalindromeSubStringLongest {
	static void main() {
		var scn = new Scanner(System.in);
		var tests = scn.nextInt();
		while (tests-- > 0) {
			var str = scn.next();
			printLongestPalindrome(str);
		}
	}

	private static void printLongestPalindrome(String str) {
		var len = str.length();
		var table = new boolean[len][len];
		for (var i = 0; i < len; i++) {
			table[i][i] = true;
		}
		int maxGap = 0, start = 0;
		for (var gap = 1; gap < len; gap++) {
			for (int i = 0, j = gap; j < len; i++, j++) {
				if (str.charAt(i) == str.charAt(j)) {
					if (gap == 1) {
						table[i][j] = true;
					} else if (table[i + 1][
							j - 1]) { // This is the substring which starts a char after and ends a char before.
						table[i][j] = true;
					}
					// Storing results
					if (table[i][j] && (gap > maxGap)) {
						start = i;
						maxGap = gap;
					}
				}
			}
		}
		System.out.println(str.substring(start, start + maxGap + 1));
	}
}
