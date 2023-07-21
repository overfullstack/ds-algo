package g4g.practice.arrays.BitonicSubArray;

import java.util.Scanner;

/** Created by gakshintala on 6/15/16. */
public class BitonicSubArray {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var len = scn.nextInt();
      var arr = new int[len];
      fillArray(arr, scn);
      System.out.println(bitonicSubArray(arr));
    }
  }

  private static void fillArray(int[] arr, Scanner scn) {
    for (var i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
  }

  private static int bitonicSubArray(int[] arr) {
    var len = arr.length;
    var inc = new int[len];
    var dec = new int[len];
    inc[0] = dec[len - 1] = 1; // By default every element is a sequence with length 1
    // Calculate consecutive increment and decrement sequences
    for (var i = 1; i < len; i++) {
      inc[i] = (arr[i] > arr[i - 1]) ? inc[i - 1] + 1 : 1;
    }
    for (var i = len - 2; i >= 0; i--) {
      dec[i] = (arr[i] > arr[i + 1]) ? dec[i + 1] + 1 : 1;
    }

    var max = inc[0] + dec[0] - 1; // -1 cause current element is included twice.
    for (var i = 1; i < len; i++) {
      max = Math.max(max, inc[i] + dec[i] - 1);
    }
    return max;
  }
}
