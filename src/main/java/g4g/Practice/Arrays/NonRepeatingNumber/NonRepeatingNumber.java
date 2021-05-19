package g4g.Practice.Arrays.NonRepeatingNumber;

import java.util.Scanner;

/** Created by gakshintala on 6/22/16. */
public class NonRepeatingNumber {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var len = scn.nextInt();
      var arr = new int[len];
      fillArray(arr, scn);
      System.out.println(nonRepeatingNumber(arr));
    }
  }

  private static int nonRepeatingNumber(int[] arr) {
    var xor = 0;
    for (var ele : arr) {
      xor ^= ele;
    }
    return xor;
  }

  private static void fillArray(int[] arr, Scanner scn) {
    for (var i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
  }
}
