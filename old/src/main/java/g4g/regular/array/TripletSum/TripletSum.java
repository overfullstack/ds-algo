package g4g.regular.array.TripletSum;

import java.util.Arrays;
import java.util.Scanner;

/** Created by gakshintala on 6/16/16. */
public class TripletSum {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var len = scn.nextInt();
      var sum = scn.nextInt();
      var arr = new int[len];
      fillArray(arr, scn);
      printTriplets(arr, sum);
      System.out.println();
    }
  }

  private static void fillArray(int[] arr, Scanner scn) {
    for (var i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
  }

  private static void printTriplets(int[] a, int sum) {
    var len = a.length;
    Arrays.sort(a);
    // <len-2 coz inner loop takes care of testing with rest two len-2, len-1
    for (var i = 0; i < len - 2; i++) {
      var l =
          i + 1; // We carry from i+1 because, the previous numbers are already tested with i+1 th
      // number
      // and no point in testing them again.
      var r = len - 1;
      while (l < r) {
        var curSum = a[i] + a[l] + a[r];
        if (curSum == sum) {
          // System.out.println("(" + a[i] + "," + a[l] + "," + a[r] + ")");
          System.out.print(1);
          return;
        } else if (curSum < sum) {
          l++;
        } else {
          r--;
        }
      }
    }
    System.out.print(0);
  }
}
