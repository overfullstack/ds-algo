package practice;

/* 06 Oct 2025 15:59 */

/**
 * [427. Construct Quad Tree](https://leetcode.com/problems/construct-quad-tree/)
 */
public class ConstructQuadTree {
  public Node construct(int[][] grid) {
    return solve(0, 0, grid.length, grid);
  }
  
  // ! Top-down halving the `sideLen` until it reaches single cell
  private static Node solve(int startRow, int startCol, int sideLen, int[][] grid) {
    if (sideLen == 1) {
      return new Node(grid[startRow][startCol] == 1, true, null, null, null, null);
    }
    var halfSideLen = sideLen / 2;
    var topLeft = solve(startRow, startCol, halfSideLen, grid);
    var topRight = solve(startRow, startCol + halfSideLen, halfSideLen, grid);
    var bottomLeft = solve(startRow + halfSideLen, startCol, halfSideLen, grid);
    var bottomRight = solve(startRow + halfSideLen, startCol + halfSideLen, halfSideLen, grid);
    // ! Merge leaf nodes with same val
    if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf && topLeft.val == topRight.val
        && topRight.val == bottomLeft.val && bottomLeft.val == bottomRight.val) {
      return new Node(topLeft.val, true, null, null, null, null);
    } else {
      return new Node(grid[startRow][startCol] == 1, false, topLeft, topRight, bottomLeft, bottomRight);
    }
  }

  static void main() {
    var grid = new int[][] {
      {1, 1, 1, 1, 0, 0, 0, 0},
      {1, 1, 1, 1, 0, 0, 0, 0},
      {1, 1, 1, 1, 1, 1, 1, 1},
      {1, 1, 1, 1, 1, 1, 1, 1},
      {1, 1, 1, 1, 0, 0, 0, 0},
      {1, 1, 1, 1, 0, 0, 0, 0},
      {1, 1, 1, 1, 0, 0, 0, 0},
      {1, 1, 1, 1, 0, 0, 0, 0}
    };
    var constructQuadTree = new ConstructQuadTree();
    System.out.println(constructQuadTree.construct(grid));
  }

  private static class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
      this.val = false;
      this.isLeaf = false;
      this.topLeft = null;
      this.topRight = null;
      this.bottomLeft = null;
      this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
      this.val = val;
      this.isLeaf = isLeaf;
      this.topLeft = null;
      this.topRight = null;
      this.bottomLeft = null;
      this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
      this.val = val;
      this.isLeaf = isLeaf;
      this.topLeft = topLeft;
      this.topRight = topRight;
      this.bottomLeft = bottomLeft;
      this.bottomRight = bottomRight;
    }
  }
}
