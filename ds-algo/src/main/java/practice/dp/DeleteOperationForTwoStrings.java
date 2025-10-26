package practice.dp;

/* 26 Oct 2025 16:29 */

public class DeleteOperationForTwoStrings {
	public int minDistance(String word1, String word2) {
		var commonSubsequenceLen = longestCommonSubsequence(word1, word2);
		// ! Delete all the letters except the common ones
		return word1.length() + word2.length() - 2 * commonSubsequenceLen;
	}

	private static int longestCommonSubsequence(String word1, String word2) {
		var table = new int[word1.length() + 1][word2.length() + 1];
		for (var i = 0; i <= word1.length(); i++) {
			for (var j = 0; j <= word2.length(); j++) {
				if (i == 0 || j == 0) {
					table[i][j] = 0;
				} else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					table[i][j] = 1 + table[i - 1][j - 1];
				} else {
					table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
				}
			}
		}
		return table[word1.length()][word2.length()];
	}

	static void main() {
		var obj = new DeleteOperationForTwoStrings();
		System.out.println(obj.minDistance("sea", "eat")); // 2
		System.out.println(obj.minDistance("leetcode", "etco")); // 4
	}
}
