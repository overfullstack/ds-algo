package practice.dp;

/* 26 Oct 2025 08:23 */

import static java.lang.IO.println;

/**
 * [740. Delete and Earn](https://leetcode.com/problems/delete-and-earn/)
 */
public class DeleteAndEarn {
  public int deleteAndEarn(int[] nums) {
    var buckets = new int[1 + (int) Math.pow(10, 4)];
    for (var num : nums) {
      buckets[num] += num;
    }
    var dp = new int[buckets.length];
    dp[0] = 0; // ! or buckets[0], which is 0
    dp[1] = buckets[1]; // ! No need of `maxOf` as it's either 0 or positive
    for (var i = 2; i < dp.length; i++) {
      dp[i] = Math.max(dp[i - 1], buckets[i] + dp[i - 2]);
    }
    return dp[dp.length - 1];
  }

  static void main() {
    var obj = new DeleteAndEarn();
    println(obj.deleteAndEarn(new int[] {3, 4, 2})); // 6
    println(obj.deleteAndEarn(new int[] {2, 2, 3, 3, 3, 4})); // 9
  }
}
