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
		var commonSubStrLen = Integer.MIN_VALUE;

		for (var i = 0; i <= aLen; i++) {
			for (var j = 0; j <= bLen; j++) {
				if (i == 0 || j == 0) { // One of the strings is empty, so nothing in common
					table[i][j] = 0;
				} else {
					if (a.charAt(i - 1) == b.charAt(j - 1)) {
						table[i][j] = table[i - 1][j - 1] + 1;
						commonSubStrLen = Math.max(commonSubStrLen, table[i][j]); // Record the match streak
					} else { // Substring match streak is broken, restart
						table[i][j] = 0;
					}
				}
			}
		}
		return commonSubStrLen;
	}
}
