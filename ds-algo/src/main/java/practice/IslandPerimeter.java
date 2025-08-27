package practice;

/* 27 Aug 2025 08:50 */

public class IslandPerimeter {
  private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

  public int islandPerimeter(int[][] grid) {
    var countPerimeter = 0;
    for (var row = 0; row < grid.length; row++) {
      for (var col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == 1) {
          countPerimeter += countPerimeter(row, col, grid);
        }
      }
    }
    return countPerimeter;
  }

  private static int countPerimeter(int row, int col, int[][] grid) {
    var edgeCount = 0;
    grid[row][col] = -1;
    for (var direction : directions) {
      var nextRow = direction[0] + row;
      var nextCol = direction[1] + col;
      if (!isValid(nextRow, nextCol, grid) || grid[nextRow][nextCol] == 0) {
        edgeCount++;
      } else if (grid[nextRow][nextCol] == 1) { // ! isValid as per above check
        edgeCount += countPerimeter(nextRow, nextCol, grid);
      }
    }
    return edgeCount;
  }

  private static boolean isValid(int row, int col, int[][] grid) {
    return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
  }
}
