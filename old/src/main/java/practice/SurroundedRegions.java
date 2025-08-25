package practice;

import java.util.Arrays;

/** [130. Surrounded Regions](https://leetcode.com/problems/surrounded-regions) */
public class SurroundedRegions {
	public void solve(char[][] board) {
		// left, right
		for (var row = 0; row < board.length; row++) {
			if (board[row][0] == 'O') {
				dfs(new int[] {row, 0}, board);
			}
			if (board[row][board[0].length - 1] == 'O') {
				dfs(new int[] {row, board[0].length - 1}, board);
			}
		}
		// top, bottom
		for (var col = 0; col < board[0].length; col++) {
			if (board[0][col] == 'O') {
				dfs(new int[] {0, col}, board);
			}
			if (board[board.length - 1][col] == 'O') {
				dfs(new int[] {board.length - 1, col}, board);
			}
		}
		for (var row = 0; row < board.length; row++) {
			for (var col = 0; col < board[0].length; col++) {
				if (board[row][col] == 'O') {
					board[row][col] = 'X';
				} else if (board[row][col] == '*') {
					board[row][col] = 'O';
				}
			}
		}
	}

	private static void dfs(int[] cell, char[][] board) {
		board[cell[0]][cell[1]] = '*';
		Arrays.stream(directions)
				.map(d -> new int[] {d[0] + cell[0], d[1] + cell[1]})
				.filter(nextCell -> isValid(nextCell, board) && board[nextCell[0]][nextCell[1]] == 'O')
				.forEach(nextCell -> dfs(nextCell, board));
	}

	private static boolean isValid(int[] cell, char[][] board) {
		return (cell[0] >= 0 && cell[0] < board.length) && (cell[1] >= 0 && cell[1] < board[0].length);
	}

	static int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
}
