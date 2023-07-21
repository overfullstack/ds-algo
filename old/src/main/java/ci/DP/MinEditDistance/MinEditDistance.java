package ci.DP.MinEditDistance;

/** Created by gakshintala on 3/22/16. */
public class MinEditDistance {

  public static void main(String[] args) {
    var str1 = "sunday".toCharArray();
    var str2 = "saturday".toCharArray();
    System.out.println(findMinEditDistance(str1, str2));
  }

  private static int findMinEditDistance(char[] str1, char[] str2) {
    var len1 = str1.length;
    var len2 = str2.length;

    var table = new int[len1 + 1][len2 + 1];

    for (var i = 0; i <= len1; i++) {
      table[i][0] = i; // Operations required if 0 characters are present
    }

    for (var i = 0; i <= len2; i++) {
      table[0][i] = i;
    }

    for (var i = 1;
        i <= len1;
        i++) { // Note how we start from 1, and compare i-1, this is done to rely on the base cases
      // table[i][0], table[0][i]
      for (var j = 1; j <= len2; j++) {
        if (str1[i - 1] == str2[j - 1]) {
          table[i][j] = table[i - 1][j - 1]; // This is same as replace.
        } else {
          // Math.min(Insert, Delete, Replace)
          table[i][j] =
              Math.min(Math.min(table[i - 1][j], table[i][j - 1]), table[i - 1][j - 1]) + 1;
        }
      }
    }
    return table[len1][len2];
  }
}
