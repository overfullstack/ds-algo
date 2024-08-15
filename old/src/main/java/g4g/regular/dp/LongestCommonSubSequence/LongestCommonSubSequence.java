package g4g.regular.dp.LongestCommonSubSequence;

/** Created by gakshintala on 6/11/16. */
public class LongestCommonSubSequence {
  public static void main(String[] args) {
    var a = "AGGTAB";
    var b = "GXTXAYB";

    System.out.println(longestCommonSubSequence(a, b));
  }

  private static int longestCommonSubSequence(String a, String b) {
    var aLen = a.length();
    var bLen = b.length();
    var table = new int[aLen + 1][bLen + 1];

    for (var i = 0; i <= aLen; i++) {
      for (var j = 0; j <= bLen; j++) {
        // This is required to avoid ArrayIndexOutOfBoundsException, in line 26. Otherwise, we have
        // to check
        // if i-1 > 0 before using that value.
        if (i == 0 || j == 0) {
          table[i][j] = 0;
        } else {
          // if char matches
          if (a.charAt(i - 1) == b.charAt(j - 1)) {
            table[i][j] = table[i - 1][j - 1] + 1;
          } else {
            // max of char not present in a, char not present in b
            table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
          }
        }
      }
    }
    return table[aLen][bLen];
  }
}
