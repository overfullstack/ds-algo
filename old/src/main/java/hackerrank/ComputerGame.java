package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Created by gakshintala on 12/21/15. THIS IS INCOMPLETE */
public class ComputerGame {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var n = scn.nextInt();

    List<Long> arr1 = new ArrayList<>();
    List<Long> arr2 = new ArrayList<>();

    for (var i = 0; i < n; i++) arr1.add((long) scn.nextInt());
    for (var i = 0; i < n; i++) arr2.add((long) scn.nextInt());
    int i = 0, count = 0;
    while (i < n - count) {
      var j = 0;
      while (j < n - count) {
        if (gcd(arr1.get(i), arr2.get(j)) != 1) {
          arr1.remove(i);
          arr2.remove(j);
          count++;
          j = 0;
        } else j++;
      }
      i++;
    }
    System.out.println(count);
  }

  private static long gcd(long a, long b) {
    if (a % b == 0) return b;
    return gcd(b, a % b);
  }
}
