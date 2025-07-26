package gfg.regular.dp.LongestCommonSubSequence;

/** Created by gakshintala on 6/11/16. */
public class LongestCommonSubSequence {
	static void main() {
		var a = "AGGTAB";
		var b = "GXTXAYB";

		System.out.println(longestCommonSubSequence(a, b));
	}

	private static int longestCommonSubSequence(String a, String b) {
		var aLen = a.length();
		var bLen = b.length();
		var table = new int[aLen + 1][bLen + 1];

		for (var i = 0; i <= aLen; i++) {
			for (var j = 0; j <= bLen; j++) {
				if (i == 0 || j == 0) { // One of the strings is empty, so nothing in common
					table[i][j] = 0;
				} else {
					// if char matches
					if (a.charAt(i - 1) == b.charAt(j - 1)) {
						table[i][j] = table[i - 1][j - 1] + 1;
					} else { // Unlike substring, the match streak is not broken; we continue to build up
						// max of length if char not present in `a`, char not present in `b`
						// No `+1` as no match
						table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
					}
				}
			}
		}
		return table[aLen][bLen];
	}
}
