package gfg.regular.dp.ZigZagSubsequence;

import java.util.Arrays;

/** Created by gakshintala on 6/13/16. */
public class ZigZagSubsequence {
  public static void main(String[] args) {
    int arr[] = {10, 22, 9, 33, 49, 50, 31, 60};
    System.out.println(longestZigZagSubsequence(arr));
  }

  private static int longestZigZagSubsequence(int[] arr) {
    var table = new int[2][arr.length];
    table[0][0] = table[1][0] = 1;
    for (var i = 1; i < arr.length; i++) {
      table[0][i] = table[1][i] = 1;
      for (var j = 0; j < i; j++) {
        if (arr[j] < arr[i]) {
          // We just found a decrement, so compare with last time increment with that current
          // element + 1
          table[0][i] = Math.max(table[0][i], table[1][j] + 1);
        } else {
          table[1][i] = Math.max(table[1][i], table[0][j] + 1);
        }
      }
    }
    return Math.max(
        Arrays.stream(table[0]).max().getAsInt(), Arrays.stream(table[1]).max().getAsInt());
  }
}
