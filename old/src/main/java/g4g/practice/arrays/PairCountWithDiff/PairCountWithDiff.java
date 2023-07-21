package g4g.practice.arrays.PairCountWithDiff;

import java.util.Arrays;
import java.util.Scanner;

/** Created by gakshintala on 7/3/16. */
public class PairCountWithDiff {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var len = scn.nextInt();
      var diffReq = scn.nextInt();
      var arr = new int[len];
      fillArray(arr, scn);
      System.out.println(pairCountWithDiff(arr, diffReq));
    }
  }

  private static int pairCountWithDiff(int[] arr, int diffReq) {
    Arrays.sort(arr);
    int l = 0, r = 1, count = 0;
    var len = arr.length;
    while (r < len) {
      var diff = arr[r] - arr[l];
      if (diff == diffReq) {
        // Uncomment below and comment rest in this block, if distinct pairs are required. Also need
        // a bit
        // more work to check if nextRight combo after increment is same or different
        /*count++
        r++;l++; */

        int tempL = l, tempR = r;
        l++;
        r++;
        // If we have duplicates
        while (arr[l - 1] == arr[l]) {
          l++;
        }
        while (r < len && arr[r - 1] == arr[r]) {
          r++;
        }
        count += ((l - tempL) * (r - tempR)); // Pairs with all duplicates
      } else if (diff > diffReq) {
        l++; // Even if l=r, it will be taken care in nextRight iteration under diff<diffReq case
      } else {
        r++;
      }
    }
    return count;
  }

  private static void fillArray(int[] arr, Scanner scn) {
    for (var i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
  }
}
