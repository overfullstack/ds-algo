package practice.dp;

/* 26 Oct 2025 17:19 */

/**
 * [712. Minimum ASCII Delete Sum for Two
 * Strings](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/)
 */
public class MinimumASCIIDeleteSumForTwoStrings {
	public int minimumDeleteSum(String s1, String s2) {
		var commonSubsequenceCost = commonSubsequenceCost(s1, s2);
		var s1Cost = s1.chars().sum();
		var s2Cost = s2.chars().sum();
		return s1Cost + s2Cost - 2 * commonSubsequenceCost;
	}

	private static int commonSubsequenceCost(String s1, String s2) {
		var dp = new int[s1.length() + 1][s2.length() + 1];
		for (var i = 0; i <= s1.length(); i++) {
			for (var j = 0; j <= s2.length(); j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = s1.charAt(i - 1) + dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}

	static void main() {
		var obj = new MinimumASCIIDeleteSumForTwoStrings();
		System.out.println(obj.minimumDeleteSum("sea", "eat")); // 231
		System.out.println(obj.minimumDeleteSum("delete", "leet")); // 403
	}
}
