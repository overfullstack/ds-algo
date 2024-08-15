package g4g.regular.array.DoubletSum;

import java.util.Arrays;

/** Created by gakshintala on 6/16/16. */
public class DoubletSum {
  public static void main(String[] args) {
    int A[] = {1, 4, 45, 6, 10, 8};
    var n = 16;
    printPairs(A, n);
  }

  private static void printPairs(int[] a, int sum) {
    Arrays.sort(a);
    var len = a.length;
    int l = 0, r = len - 1;
    while (l < r) {
      if (a[l] + a[r] == sum) {
        System.out.println("(" + a[l] + "," + a[r] + ")");
        return;
      } else if (a[l] + a[r] < sum) {
        l++;
      } else {
        r--;
      }
    }
  }
}
