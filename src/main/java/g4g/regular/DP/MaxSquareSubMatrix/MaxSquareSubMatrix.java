package g4g.regular.DP.MaxSquareSubMatrix;

/** Created by gakshintala on 6/10/16. */
public class MaxSquareSubMatrix {
  public static void main(String[] args) {
    int M[][] = {
      {0, 1, 1, 0, 1},
      {1, 1, 0, 1, 0},
      {0, 1, 1, 1, 0},
      {1, 1, 1, 1, 0},
      {1, 1, 1, 1, 1},
      {0, 0, 0, 0, 0}
    };

    System.out.println(maxSquareSubMatrix(M, 6, 5));
  }

  private static int maxSquareSubMatrix(int[][] m, int row, int col) {
    var table = new int[row][col];

    for (var i = 0; i < row; i++) {
      table[i][0] = m[i][0];
    }

    System.arraycopy(m[0], 0, table[0], 0, col);

    for (var i = 1; i < row; i++) {
      for (var j = 1; j < col; j++) {
        if (m[i][j] == 1) {
          // If any of these 3 places are zero it can't make a matrix, so result dim is only 1. Min
          // is
          // taken for a same reason. Also it can make a matrix till its visibility
          table[i][j] =
              Math.min(table[i - 1][j - 1], Math.min(table[i - 1][j], table[i][j - 1])) + 1;
        } else {
          table[i][j] = 0;
        }
      }
    }
    return findMaxInMatrix(table, row, col);
  }

  private static int findMaxInMatrix(int[][] table, int row, int col) {
    var max = table[0][0];
    for (var i = 0; i < row; i++) {
      for (var j = 0; j < col; j++) {
        max = Math.max(max, table[i][j]);
      }
    }
    return max;
  }
}
