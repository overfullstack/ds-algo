package practice;

import java.util.Arrays;

/* 26 Aug 2025 20:50 */

/**
 * 
 */
public class NumberOfEnclaves {

  private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  public int numEnclaves(int[][] grid) {
    for (var row = 0; row < grid.length; row++) {
      floodFill(row, 0, grid);
      floodFill(row, grid[0].length - 1, grid);
    }

    for (var col = 0; col < grid[0].length; col++) {
      floodFill(0, col, grid);
      floodFill(grid.length - 1, col, grid);
    }

    var closedIslandCount = 0;
    for (var row = 0; row < grid.length; row++) {
      for (var col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == 1) {
          closedIslandCount++;
        }
      }
    }
    return closedIslandCount;
  }

  private static void floodFill(int row, int col, int[][] grid) {
    if (grid[row][col] != 1) { // ! Fill only land cells
      return;
    }
    grid[row][col] = 0;
    Arrays.stream(directions)
        .map(d -> new int[]{d[0] + row, d[1] + col})
        .filter(nextCell -> isValid(nextCell, grid) && grid[nextCell[0]][nextCell[1]] == 1)
        .forEach(landCell -> floodFill(landCell[0], landCell[1], grid));
  }

  private static boolean isValid(int[] cell, int[][] grid) {
    return cell[0] >= 0 && cell[0] < grid.length && cell[1] >= 0 && cell[1] < grid[0].length;
  }

  static void main() {
    var grid = new int[][]{{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
    var enclaves = new NumberOfEnclaves().numEnclaves(grid);
    System.out.println(enclaves); // 3
  }
}
