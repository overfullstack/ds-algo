package g4g.practice.arrays.SubArrayMaxAbsSumDiff;

import java.util.Scanner;

/** Created by gakshintala on 6/15/16. */
public class SubArrayMaxAbsSumDiff {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var testCases = scn.nextInt();
    while (testCases-- > 0) {
      var len = scn.nextInt();
      var arr = readArray(scn, len);
      System.out.println(maxAbsSumDiff(arr));
    }
  }

  private static int maxAbsSumDiff(int[] arr) {
    var leftMin = constructLeftMinSum(arr);
    var rightMin = constructRightMinSum(arr);
    // Constructing leftMin on array with inverse sign gives leftMax with inverse sign, invert it
    // again.
    var leftMax = invertSign(constructLeftMinSum(invertSign(arr)));
    var rightMax = invertSign(constructRightMinSum(invertSign(arr)));
    var len = arr.length;
    var maxDiff = Integer.MIN_VALUE;
    // Max diff can be found by difference between min on one side and max on another.
    for (var i = 0; i < len; i++) {
      maxDiff =
          Math.max(
              maxDiff,
              Math.max(Math.abs(leftMin[i] - rightMax[i]), Math.abs(leftMax[i] - rightMin[i])));
    }
    return maxDiff;
  }

  private static int[] invertSign(int[] a) {
    var len = a.length;
    var inverse = new int[len];
    for (var i = 0; i < len; i++) {
      inverse[i] = -a[i];
    }
    return inverse;
  }

  private static int[] constructRightMinSum(int[] arr) {
    var len = arr.length;
    var rightMin = new int[len];
    int curSum = rightMin[len - 1] = arr[len - 1], minSum = arr[len - 1];
    // We proceed with negative sum as well, so nullifying when sum<0.
    for (var i = len - 2; i >= 0; i--) {
      curSum = Math.min(arr[i], curSum + arr[i]);
      minSum = Math.min(minSum, curSum);
      rightMin[i] = minSum;
    }
    return rightMin;
  }

  private static int[] constructLeftMinSum(int[] arr) {
    var len = arr.length;
    var leftMin = new int[len];
    int curSum = leftMin[0] = arr[0], minSum = arr[0];
    for (var i = 1; i < len; i++) {
      curSum = Math.min(arr[i], curSum + arr[i]); // Since negative numbers are involved
      minSum = Math.min(minSum, curSum);
      leftMin[i] = minSum;
    }
    return leftMin;
  }

  private static int[] readArray(Scanner scn, int len) {
    var arr = new int[len];
    for (var i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
    return arr;
  }
}
