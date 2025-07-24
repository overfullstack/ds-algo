package gfg.regular.dp.LongestCommonSubString;

/** Created by gakshint on 17/04/18. */
public class LongestCommonSubString {
	static void main() {
		var a = "OldSite:GeeksforGeeks.org";
		var b = "NewSite:GeeksQuiz.com";

		System.out.println(longestCommonSubSequence(a, b));
	}

	private static int longestCommonSubSequence(String a, String b) {
		var aLen = a.length();
		var bLen = b.length();
		var table = new int[aLen + 1][bLen + 1];
		var result = Integer.MIN_VALUE;

		for (var i = 0; i <= aLen; i++) {
			for (var j = 0; j <= bLen; j++) {
				// This is required to avoid ArrayIndexOutOfBoundsException, in line 26. Otherwise, we have
				// to check
				// if i-1 > 0 before using that value.
				if (i == 0 || j == 0) {
					table[i][j] = 0;
				} else {
					// if char matches
					if (a.charAt(i - 1) == b.charAt(j - 1)) {
						table[i][j] = table[i - 1][j - 1] + 1;
						result = Math.max(result, table[i][j]);
					} else {
						table[i][j] = 0;
					}
				}
			}
		}
		return result;
	}
}
