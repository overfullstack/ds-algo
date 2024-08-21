package gfg.practice.dp.CoinGame;

import java.util.Scanner;

/** Created by gakshintala on 6/10/16. */
public class CoinGame {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var len = scn.nextInt();
      var arr = new int[len];
      fillArray(arr, scn);
      System.out.println(maxValueInGame(arr));
    }
  }

  private static int maxValueInGame(int[] arr) {
    var len = arr.length;
    // Dealing in Tuples. This table is populated only half, tending towards top right
    var table = new int[len][len];
    // We start at gap=1, cause that's where game starts, which is max of two
    // It needs minimum of 4 coins for a game to get interesting or it is just maximum of both.
    for (var gap = 1; gap < len; gap++) {
      for (int i = 0, j = gap; j < len; i++, j++) {
        // Checking if bounds are valid, if valid return table results or 0, which eventually goes
        // into table
        var x = (i + 2 < j) ? table[i + 2][j] : 0;
        var y = (i + 1 < j - 1) ? table[i + 1][j - 1] : 0;
        var z = (i < j - 2) ? table[i][j - 2] : 0;

        // If you pick ith coin, the other users picks a coin making your nextRight pick be minimum
        // of two
        // possibilities i.e., if he picks i+1, we are left with i+2, j. If he picks j, we are left
        // with i+1,
        // j-1. Similarly, if we pick jth coin.
        table[i][j] = Math.max(arr[i] + Math.min(x, y), arr[j] + Math.min(y, z));
      }
    }
    return table[0][len - 1];
  }

  private static void fillArray(int[] arr, Scanner scn) {
    for (var i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
  }
}
