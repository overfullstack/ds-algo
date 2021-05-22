package g4g.regular.DP.SubSetSum;

/** Created by gakshintala on 3/21/16. */
public class SubSetSum {
  public static void main(String[] args) {
    System.out.println(isSumPresent(new int[] {3, 34, 4, 12, 5, 2}, 9));
    System.out.println(isSumPresent(new int[] {3, 34, 4, 12, 5, 2}, 30));
  }

  private static String isSumPresent(int[] set, int sum) {
    var table = new boolean[sum + 1];
    table[0] = true;

    for (var num : set) {
      for (int j = sum; j >= num; j--) { // No need to check if num > sum
        table[j] |= table[j - num];
      }
    }
    return table[sum] ? "YES" : "NO";
  }

  private static boolean isSumPresent2(int[] set, int sum) {
    var len = set.length;
    var table = new boolean[sum + 1][len + 1];
    // Sum 0 can be achieved by empty subset in all cases
    for (var i = 0; i <= len; i++) {
      table[0][i] = true;
    }

    // Starting from 1, coz [0][0] case is covered above for empty subset and sum=0.
    for (var i = 1; i <= sum; i++) {
      for (var j = 1; j <= len; j++) {
        table[i][j] =
            table[i][j - 1]; // Exclude case - if we exclude this, check if we make this sum with
        // other elements
        if (set[j - 1]
            <= i) { // If current number we are checking is less than or equal to sum we need
          table[i][j] |=
              table[i - set[j - 1]][j]; // Include case table[i-set[j-1]][j] - if we include this,
          // check if we can make the rest of sum
        }
      }
    }

    return table[sum][len];
  }
}
