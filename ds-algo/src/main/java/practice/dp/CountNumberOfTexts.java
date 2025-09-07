package practice.dp;

/* 06 Sep 2025 22:49 */

/** [2266. Count Number of Texts](https://leetcode.com/problems/count-number-of-texts/) */
public class CountNumberOfTexts {
	public int countTexts(String pressedKeys) {
		var mod = 1000000007;
		var dp = new int[pressedKeys.length() + 1];
		dp[0] = 1;
		for (var i = 1; i <= pressedKeys.length(); i++) {
			dp[i] = dp[i - 1] % mod;
			var ch = pressedKeys.charAt(i - 1);
			// ! Nested `if`s coz, for (i-3) we need to check (i-2) also
			if (i >= 2 && ch == pressedKeys.charAt(i - 2)) {
				dp[i] = (dp[i] + dp[i - 2]) % mod;
				if (i >= 3 && ch == pressedKeys.charAt(i - 3)) {
					dp[i] = (dp[i] + dp[i - 3]) % mod;
					if (i >= 4 && (ch == '7' || ch == '9') && ch == pressedKeys.charAt(i - 4)) {
						dp[i] = (dp[i] + dp[i - 4]) % mod;
					}
				}
			}
		}
		return dp[dp.length - 1];
	}

	static void main() {
		var c = new CountNumberOfTexts();
		System.out.println(c.countTexts("344644885")); // 8
		System.out.println(c.countTexts("444479999555588866")); // 3136
		System.out.println(c.countTexts("22233"));
		System.out.println(c.countTexts("222222222222222222222222222222222222"));
	}
}
