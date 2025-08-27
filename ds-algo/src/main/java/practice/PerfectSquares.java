package practice;

/* 27 Aug 2025 13:09 */

import java.util.Arrays;

public class PerfectSquares {
  public int numSquares(int n) {
    var dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    for (var i = 1; i <= n; i++) {
      var sqrt = (int) Math.sqrt(i);
      if (sqrt * sqrt == i) {
        dp[i] = 1;
      } else {
        for (var j = 1; j * j <= i; j++) {
          dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
        }
      }
    }
    return dp[n];
  }
}
