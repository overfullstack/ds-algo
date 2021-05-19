package g4g.Practice.Arrays.RelativeSort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Created by gakshintala on 6/22/16. INCOMPLETE */
public class RelativeSort {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var len1 = scn.nextInt();
      var len2 = scn.nextInt();

      var a1 = new int[len1];
      fillArray(a1, scn);

      var a2 = new int[len2];
      fillArray(a2, scn);

      relativeSort(a1, a2);
    }
  }

  private static void relativeSort(int[] a1, int[] a2) {
    List<Integer> a1List = new ArrayList<>();
    for (var e : a1) {
      a1List.add(e);
    }
  }

  private static void fillArray(int[] arr, Scanner scn) {
    for (var i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
  }
}
