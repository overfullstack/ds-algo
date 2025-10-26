package practice.dp;

/* Created by gakshintala on 6/11/16. */

/** [1143. Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/) */
public class LongestCommonSubsequence {
	public int longestCommonSubsequence(String text1, String text2) {
		var table = new int[text1.length() + 1][text2.length() + 1];
		for (var i = 0; i <= text1.length(); i++) {
			for (var j = 0; j <= text2.length(); j++) {
				if (i == 0 || j == 0) { // One of the strings is empty, so nothing in common
					table[i][j] = 0;
				} else {
					// if char matches
					if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
						table[i][j] = 1 + table[i - 1][j - 1];
					} else { // Unlike substring, the match streak is not broken; we continue to build up
						// max of length if char not present in `a`, char not present in `b`
						// No `+1` as no match
						table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
					}
				}
			}
		}
		return table[text1.length()][text2.length()];
	}

	static void main() {
		var obj = new LongestCommonSubsequence();
		System.out.println(obj.longestCommonSubsequence("abcde", "ace")); // 3
		System.out.println(obj.longestCommonSubsequence("abc", "abc")); // 3
		System.out.println(obj.longestCommonSubsequence("abc", "def")); // 0
	}
}
