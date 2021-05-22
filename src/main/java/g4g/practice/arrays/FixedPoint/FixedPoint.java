package g4g.practice.arrays.FixedPoint;

import java.util.Scanner;

// TBD
public class FixedPoint {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var len = scn.nextInt();
      var arr = new int[len];
      fillArray(arr, scn);
      System.out.println(findFixedPoint(arr, 0, len - 1));
    }
  }

  private static int findFixedPoint(int[] arr, int left, int right) {
    if (left > right) {
      return -1;
    }
    var mid = (left + right) / 2;
    if (mid == arr[mid]) {
      return mid;
    }
    if (mid < arr[mid]) {
      return findFixedPoint(arr, left, mid - 1);
    }
    return findFixedPoint(arr, mid + 1, right);
  }

  private static void fillArray(int[] arr, Scanner scn) {
    for (var i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
  }
}
