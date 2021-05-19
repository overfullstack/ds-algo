package g4g.Practice.Arrays.TripletSumZero;

import java.util.Arrays;
import java.util.Scanner;

/** Created by Gopala Akshintala on 3/3/17. */
public class TripletSumZero {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var len = scn.nextInt();
      var arr = new int[len];
      fillArray(arr, scn);
      System.out.println(tripletSumZero(arr, len) ? 1 : 0);
    }
  }

  private static boolean tripletSumZero(int[] arr, int len) {
    Arrays.sort(arr);
    for (var i = 0; i < len; i++) {
      var left = i + 1;
      var right = len - 1;
      while (left < right) {
        var sum = arr[i] + arr[left] + arr[right];
        if (sum == 0) {
          return true;
        }
        if (sum < 0) {
          left++;
        } else {
          right--;
        }
      }
    }
    return false;
  }

  private static void fillArray(int[] arr, Scanner scn) {
    for (var i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
  }
}
