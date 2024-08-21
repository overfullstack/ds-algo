package gfg.practice.backtracking.Sudoku;

import java.util.Scanner;

/** Created by Go on 9-4-16. */
public class Sudoku {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var testCases = scn.nextInt();
    while (testCases-- > 0) {
      var grid = readGrid(scn);
      if (solveSudoku(grid)) {
        printGrid(grid);
      } else {
        System.out.println("No Solution");
      }
    }
  }

  private static void printGrid(int[][] grid) {
    for (var i = 0; i < 9; i++) {
      for (var j = 0; j < 9; j++) {
        System.out.print(grid[i][j] + " ");
      }
    }
    System.out.println();
  }

  private static int[][] readGrid(Scanner scn) {
    var grid = new int[9][9];
    for (var i = 0; i < 9; i++) {
      for (var j = 0; j < 9; j++) {
        grid[i][j] = scn.nextInt();
      }
    }
    return grid;
  }

  private static boolean solveSudoku(int[][] grid) {
    var unfilled = findNextUnfilled(grid);
    if (unfilled[0] == -1 && unfilled[1] == -1) {
      return true; // Base Condition, no unfilled items
    }
    for (var val = 1;
        val <= 9;
        val++) { // For loop, whenever we backtrack, check for nextRight case
      if (isSafe(val, grid, unfilled)) { // Is nextRight step valid
        grid[unfilled[0]][unfilled[1]] = val;
        // Recursion
        if (solveSudoku(grid)) {
          return true;
        }
        // backtracking
        grid[unfilled[0]][unfilled[1]] = 0;
      }
    }
    return false; // Tried everything and failed.
  }

  private static boolean isSafe(int val, int[][] grid, int[] unfilled) {
    return isNotInRow(val, grid, unfilled)
        && isNotInColumn(val, grid, unfilled)
        && isNotInBox(val, grid, unfilled);
  }

  private static boolean isNotInRow(int val, int[][] grid, int[] unfilled) {
    for (var col = 0; col < 9; col++) {
      if (grid[unfilled[0]][col] == val) return false;
    }
    return true;
  }

  private static boolean isNotInColumn(int val, int[][] grid, int[] unfilled) {
    for (var row = 0; row < 9; row++) {
      if (grid[row][unfilled[1]] == val) return false;
    }
    return true;
  }

  private static boolean isNotInBox(int val, int[][] grid, int[] unfilled) {
    var boxRowOrigin = unfilled[0] - unfilled[0] % 3;
    var boxColOrigin = unfilled[1] - unfilled[1] % 3;
    for (var row = 0; row < 3; row++) {
      for (var col = 0; col < 3; col++) {
        if (grid[boxRowOrigin + row][boxColOrigin + col] == val) {
          return false;
        }
      }
    }
    return true;
  }

  private static int[] findNextUnfilled(int[][] grid) {
    var unfilled = new int[2];
    unfilled[0] = -1;
    unfilled[1] = -1;

    for (var i = 0; i < 9; i++) {
      for (var j = 0; j < 9; j++) {
        if (grid[i][j] == 0) {
          unfilled[0] = i;
          unfilled[1] = j;
          return unfilled;
        }
      }
    }
    return unfilled;
  }
}
