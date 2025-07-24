package gfg.practice.dp.PalindromeSubSequenceLongest;

import java.util.Scanner;

/** Created by gakshintala on 4/20/16. */
public class PalindromeSubSequenceLongest {
	static void main() {
		var scn = new Scanner(System.in);
		var tests = scn.nextInt();
		while (tests-- > 0) {
			var str = scn.next();
			System.out.println(longestPalindromeSequence(str.toCharArray()));
		}
	}

	private static int longestPalindromeSequence(char[] seq) {
		var n = seq.length;
		var table = new int[n][n];
		// Fill the diagonal in the matrix, only upper half shall be used.
		for (var i = 0; i < n; i++) {
			table[i][i] = 1;
		}
		// Calculating through increasing windows, chars both on x and y axis.
		for (var gap = 1; gap <= n; gap++) {
			for (int i = 0, j = gap; j < n; i++, j++) {
				if (seq[i] == seq[j]) {
					table[i][j] = table[i + 1][j - 1] + 2;
				} else {
					table[i][j] = Math.max(table[i][j - 1], table[i + 1][j]);
				}
			}
		}
		// Matrix keep building top-right (based on how you see it) direction and result stored in 0,n-1
		return table[0][n - 1];
	}
}
