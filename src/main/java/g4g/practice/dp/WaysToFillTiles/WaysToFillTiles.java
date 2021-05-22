package g4g.practice.dp.WaysToFillTiles;

import java.util.Scanner;

/** Created by gakshintala on 6/20/16. */
public class WaysToFillTiles {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var gridDim = scn.nextInt();
      System.out.println(numberOfWaysToFillGrid(gridDim));
    }
  }

  private static long numberOfWaysToFillGrid(int gridDim) {
    var table = new long[gridDim + 1];
    table[0] = 0;
    for (var i = 1; i <= gridDim; i++) {
      if (i < 4) {
        table[i] = 1;
      } else if (i == 4) {
        table[i] = 2; // Either vertically or horizontally
      } else {
        // Fill Horizontal, u r left with i-1 grid to fill
        // Fill Vertical, u r left with i-4 grid to fill, cause the grids parallel to vertically
        // filled one
        // can only be filled in one way (imagine)
        table[i] = table[i - 1] + table[i - 4];
      }
    }
    return table[gridDim];
  }
}
