package gfg.practice.arrays.LongestConsecutiveSubsequence;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/** Created by gakshintala on 6/20/16. */
public class LongestConsecutiveSubsequence {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var len = scn.nextInt();
      var arr = new int[len];
      fillArray(arr, scn);
      System.out.println(longestConsecutiveSubsequenceOptimistic(arr));
    }
  }

  private static int longestConsecutiveSubsequenceOptimistic(int[] arr) {
    var set = Arrays.stream(arr).boxed().collect(Collectors.toSet());
    var maxSubsequenceLen = 0;
    for (var ele : arr) {
      if (!set.contains(ele - 1)) {
        var j = ele;
        while (set.contains(j)) {
          j++;
        }
        maxSubsequenceLen = Math.max(maxSubsequenceLen, j - ele);
      }
    }
    return maxSubsequenceLen;
  }

  private static int longestConsecutiveSubsequence(int[] arr) {
    Arrays.sort(arr);
    var len = arr.length;
    var max = 1;
    for (var i = 1; i < len; i++) {
      var count = 1;
      for (var j = i; j > 0; j--) {
        if (arr[j] == arr[j - 1] + 1) {
          count++;
        } else {
          break;
        }
      }
      max = Math.max(max, count);
    }
    return max;
  }

  private static void fillArray(int[] arr, Scanner scn) {
    for (var i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
  }
}
