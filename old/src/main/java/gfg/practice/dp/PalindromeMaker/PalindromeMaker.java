package gfg.practice.dp.PalindromeMaker;

import java.util.Scanner;

/** Created by gakshintala on 6/20/16. */
public class PalindromeMaker {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var str = scn.next();
      System.out.println(minCharsToInsertForPalindrome(str));
    }
  }

  private static int minCharsToInsertForPalindrome(String str) {
    var len = str.length();
    var table = new int[len][len];

    for (var gap = 1; gap < len; gap++) {
      for (int i = 0, j = gap; j < len; i++, j++) {
        if (str.charAt(i) == str.charAt(j)) {
          table[i][j] = table[i + 1][j - 1];
        } else {
          table[i][j] = Math.min(table[i + 1][j], table[i][j - 1]) + 1;
        }
      }
    }
    return table[0][len - 1];
  }
}
