package g4g.Regular.DP.PathsWithKCoins;

import java.util.Arrays;

/** Created by gakshintala on 4/12/16. */
public class PathsWithKCoins {
  public static void main(String[] args) {
    var k = 12;
    int[][] mat = {{1, 2, 3}, {4, 6, 5}, {3, 2, 1}};
    var table = new int[3][3][k + 1];
    for (var arr1 : table) {
      for (var arr : arr1) {
        Arrays.fill(arr, -1);
      }
    }
    System.out.println(countPaths(mat, k, 2, 2, table));
  }

  private static int countPaths(int[][] mat, int k, int m, int n, int[][][] table) {
    if (m < 0 || n < 0) return 0;
    if (m == 0 && n == 0) return (k - mat[0][0] == 0) ? 1 : 0;

    // Storing previous results and using them
    if (table[m][n][k] != -1) return table[m][n][k];

    // Bottom-up approach, we can enter (m, n) through (m-1, n) and (m, n-1)
    table[m][n][k] =
        countPaths(mat, k - mat[m][n], m - 1, n, table)
            + countPaths(mat, k - mat[m][n], m, n - 1, table);

    return table[m][n][k];
  }
}
