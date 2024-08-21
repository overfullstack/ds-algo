package gfg.practice.backtracking.RatMaze;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This is incomplete
public class RatMazeAllPossiblePaths {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var testCases = scn.nextInt();
    while (testCases-- > 0) {
      var dimen = scn.nextInt();
      var maze = readMaze(dimen, scn);
      List<char[]> allPaths = new ArrayList<>();
      var path = new char[dimen * dimen];
      allPossiblePaths(maze, dimen, allPaths, path, 0, 0, 0);
    }
  }

  private static void allPossiblePaths(
      int[][] maze,
      int dimen,
      List<char[]> allPaths,
      char[] curPath,
      int pathIndex,
      int row,
      int col) {
    if (row == dimen && col == dimen) {
      allPaths.add(curPath);
      return;
    }
    if (isValidMove(maze, dimen, row, col)) {
      curPath[pathIndex] = 'D';
      allPossiblePaths(maze, dimen, allPaths, curPath, pathIndex + 1, row - 1, col);
      curPath[pathIndex] = 'U';
      allPossiblePaths(maze, dimen, allPaths, curPath, pathIndex + 1, row + 1, col);
      curPath[pathIndex] = 'R';
      allPossiblePaths(maze, dimen, allPaths, curPath, pathIndex + 1, row, col + 1);
      curPath[pathIndex] = 'L';
      allPossiblePaths(maze, dimen, allPaths, curPath, pathIndex + 1, row, col - 1);
    }
  }

  private static boolean isValidMove(int[][] maze, int dimen, int row, int col) {
    return (row >= 0 && row < dimen) && (col >= 0 && col < dimen) && (maze[row][col] != 0);
  }

  private static int[][] readMaze(int dimen, Scanner scn) {
    var maze = new int[dimen + 1][dimen + 1];
    for (var i = 0; i < dimen; i++) {
      for (var j = 0; j < dimen; j++) {
        maze[i][j] = scn.nextInt();
      }
    }
    return maze;
  }
}
