package hackerrank;

import java.util.Scanner;

/** Created by gakshintala on 12/9/15. */
public class Encryption {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var str = scn.next();
    var l = str.length();

    var row = (int) Math.sqrt(l);
    var col = row;
    if (row * col < l) {
      col++;
      if (row * col < l) row++;
    }
    var c = 0;
    var mat = new char[row][col];

    up:
    for (var i = 0; i < row; i++)
      for (var j = 0; j < col; j++) {
        if (c == l) break up;
        mat[i][j] = str.charAt(c++);
      }

    for (var i = 0; i < col; i++) {
      for (var j = 0; j < row; j++) if (mat[j][i] != '\u0000') System.out.print(mat[j][i]);
      System.out.print(" ");
    }
  }
}
