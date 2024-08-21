package gfg.regular.dp.MinCostPath;

/** Created by gakshintala on 7/2/16. */
public class MinCostPath {
  public static void main(String[] args) {
    int mat[][] = {{1, 2, 3}, {4, 8, 2}, {1, 5, 3}};
    System.out.println(minCostPathFromTopToBottom(mat, 3, 3));
  }

  private static int minCostPathFromTopToBottom(int[][] mat, int row, int col) {
    var table = new int[row][col];
    table[0][0] = mat[0][0];
    for (var i = 1; i < row; i++) {
      table[i][0] = table[i - 1][0] + mat[i][0];
    }

    for (var j = 1; j < col; j++) {
      table[0][j] = table[0][j - 1] + mat[0][j];
    }

    for (var i = 1; i < row; i++) {
      for (var j = 1; j < col; j++) {
        table[i][j] =
            Math.min(Math.min(table[i - 1][j], table[i][j - 1]), table[i - 1][j - 1]) + mat[i][j];
      }
    }

    return table[row - 1][col - 1];
  }
}
