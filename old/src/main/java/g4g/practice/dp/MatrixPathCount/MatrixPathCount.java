package g4g.practice.dp.MatrixPathCount;

import java.util.Scanner;

/** Created by gakshintala on 7/2/16. */
public class MatrixPathCount {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var row = scn.nextInt();
      var col = scn.nextInt();
      System.out.println(pathCountForMatrixTopToBottom(row, col));
    }
  }

  private static int pathCountForMatrixTopToBottom(int row, int col) {
    var table = new int[row][col];
    for (var i = 0; i < row; i++) {
      table[i][0] = 1;
    }

    for (var j = 0; j < col; j++) {
      table[0][j] = 1;
    }

    for (var i = 1; i < row; i++) {
      for (var j = 1; j < col; j++) {
        table[i][j] = table[i - 1][j] + table[i][j - 1]; // bottom + left
      }
    }

    return table[row - 1][col - 1];
  }
}
