package practice;

import java.util.Arrays;

/* 30 Aug 2025 17:26 */

/**
 * [289. Game of Life](https://leetcode.com/problems/game-of-life)
 */
public class GameOfLife {
	private static final int ALIVE_TO_DEAD = 2;
	private static final int DEAD_TO_ALIVE = 3;
	private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

	public void gameOfLife(int[][] board) {
		for (var row = 0; row < board.length; row++) {
			for (var col = 0; col < board[0].length; col++) {
				var aliveInNeighborsCount = aliveInNeighborsCount(row, col, board);
        // ! Keep the previous state of the cell, for neighbours 
				if (board[row][col] == 0 && aliveInNeighborsCount == 3) {
					board[row][col] = DEAD_TO_ALIVE;
				} else if (board[row][col] == 1 && aliveInNeighborsCount != 2 && aliveInNeighborsCount != 3) {
					board[row][col] = ALIVE_TO_DEAD;
				}
			}
		}
		for (var row = 0; row < board.length; row++) {
			for (var col = 0; col < board[0].length; col++) {
				if (board[row][col] == ALIVE_TO_DEAD) {
					board[row][col] = 0;
				} else if (board[row][col] == DEAD_TO_ALIVE) {
					board[row][col] = 1;
				}
			}
		}
	}

	private static int aliveInNeighborsCount(int row, int col, int[][] board) {
		return Math.toIntExact(
				Arrays.stream(directions)
						.map(d -> new int[] {d[0] + row, d[1] + col})
						.filter(
								v ->
										isValid(v, board)
												&& (board[v[0]][v[1]] == 1 || board[v[0]][v[1]] == ALIVE_TO_DEAD))
						.count());
	}

	private static boolean isValid(int[] cell, int[][] board) {
		return cell[0] >= 0 && cell[0] < board.length && cell[1] >= 0 && cell[1] < board[0].length;
	}

  static void main() {
    var gameOfLife = new GameOfLife();
    var board = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
    gameOfLife.gameOfLife(board);
    System.out.println(Arrays.deepToString(board)); // [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
  }
}
