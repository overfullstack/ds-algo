package g4g.Practice.DP.MinCoinChange;

import java.util.Scanner;

/** Created by gakshintala on 3/22/16. */
public class MinCoinChange {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var testCases = scn.nextInt();
    while (testCases-- > 0) {
      var sum = scn.nextInt();
      var len = scn.nextInt();
      var arr = readArray(scn, len);
      System.out.println(findMinCoinChange(arr, sum));
    }
  }

  private static int[] readArray(Scanner scn, int len) {
    var arr = new int[len];
    for (var i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
    return arr;
  }

  private static int findMinCoinChange(int[] coins, int total) {
    var table = new int[total + 1];
    table[0] = 0;
    for (var i = 1; i <= total; i++) {
      table[i] = Integer.MAX_VALUE;
    }

    // For every coin, looping through all the sums
    // Here we fill all table[] values for a coin
    for (var coin : coins) { // Infinite coins
      for (var j = coin; j <= total; j++) { // Min sum a coin can make starts with coin value
        if (table[j - coin] != Integer.MAX_VALUE) {
          table[j] = Math.min(table[j], table[j - coin] + 1); // Math.min(Excluding, Including + 1)
        }
      }
    }
    return (table[total] == Integer.MAX_VALUE) ? -1 : table[total];
  }
}
