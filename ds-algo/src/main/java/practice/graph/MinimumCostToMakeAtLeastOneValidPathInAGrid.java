package practice.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/* 04 Sep 2025 08:39 */

/**
 * [1368. Minimum Cost to Make at Least One Valid Path in a Grid](https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/)
 */
public class MinimumCostToMakeAtLeastOneValidPathInAGrid {
  public int minCost(int[][] grid) {
    var pq = new PriorityQueue<>(Comparator.<int[]>comparingInt(c -> c[2]));
    var minCost = new int[grid.length][grid[0].length];
    for (var c : minCost) {
      Arrays.fill(c, Integer.MAX_VALUE);
    }
    pq.add(new int[] {0, 0, 0});
    minCost[0][0] = 0;
    while (!pq.isEmpty()) {
      var cell = pq.poll();
      var row = cell[0];
      var col = cell[1];
      var cost = cell[2];
      if (cost < minCost[row][col]) {
        minCost[row][col] = cost;
        for (var direction : directions) {
          var nextRow = row + direction[0];
          var nextCol = col + direction[1];
          var nextCost = (direction[2] == grid[row][col]) ? cost : cost + 1;
          if (isValid(nextRow, nextCol, grid) && nextCost < minCost[nextRow][nextCol]) {
            pq.add(new int[]{nextRow, nextCol, nextCost});
          }
        }
      }
    }
    return minCost[grid.length - 1][grid[0].length - 1];
  }

  private static boolean isValid(int row, int col, int[][] grid) {
    return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
  }

  // 1: right, 2: left, 3: down, 4: up
  private static final int[][] directions =
      new int[][] {{0, 1, 1}, {0, -1, 2}, {1, 0, 3}, {-1, 0, 4}};

  static void main() {
    var obj = new MinimumCostToMakeAtLeastOneValidPathInAGrid();
    System.out.println(obj.minCost(new int[][] {{1,1,1,1},{2,2,2,2},{1,1,1,1},{2,2,2,2}})); // 3
    System.out.println(obj.minCost(new int[][] {{1,1,3},{3,2,2},{1,1,4}})); // 0
    System.out.println(obj.minCost(new int[][] {{1, 2}, {4, 3}})); // 1
  }
}
