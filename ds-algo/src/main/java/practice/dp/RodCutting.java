package practice.dp;

/* 15 Oct 2025 10:26 */

/** [Rod cutting](https://www.naukri.com/code360/problems/rod-cutting-problem_800284) */
public class RodCutting {
	public static int cutRod(int[] price, int n) {
		var dp = new int[price.length + 1];
		for (var len = 1; len <= price.length; len++) {
			for (var cutLen = 1; cutLen <= len; cutLen++)
				dp[len] = Math.max(dp[len], price[cutLen - 1] + dp[len - cutLen]);
		}
		return dp[dp.length - 1];
	}

	static void main() {
		System.out.println(cutRod(new int[] {2, 5, 7, 8, 10}, 5)); // 12
		System.out.println(cutRod(new int[] {3, 5, 8, 9, 10, 17, 17, 20}, 8)); // 24
	}
}
