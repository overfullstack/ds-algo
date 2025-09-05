package practice.graph.floodfill;

import java.util.Arrays;

/** [130. Surrounded Regions](https://leetcode.com/problems/surrounded-regions) */
public class SurroundedRegions {
	public void solve(char[][] board) {
		// ! Mark 'O' groups attached to left, right edges
		for (var row = 0; row < board.length; row++) {
			if (board[row][0] == 'O') {
				dfsGroup(new int[] {row, 0}, board);
			}
			if (board[row][board[0].length - 1] == 'O') {
				dfsGroup(new int[] {row, board[0].length - 1}, board);
			}
		}
		// ! Mark 'O' groups attached to top, bottom edges
		for (var col = 0; col < board[0].length; col++) {
			if (board[0][col] == 'O') {
				dfsGroup(new int[] {0, col}, board);
			}
			if (board[board.length - 1][col] == 'O') {
				dfsGroup(new int[] {board.length - 1, col}, board);
			}
		}
		// ! Revive the 'O' groups marked on the edges
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

	private static void dfsGroup(int[] cell, char[][] board) {
		board[cell[0]][cell[1]] = '*';
		Arrays.stream(directions)
				.map(d -> new int[] {d[0] + cell[0], d[1] + cell[1]})
				.filter(nextCell -> isValid(nextCell, board) && board[nextCell[0]][nextCell[1]] == 'O')
				.forEach(nextCell -> dfsGroup(nextCell, board));
	}

	private static boolean isValid(int[] cell, char[][] board) {
		return (cell[0] >= 0 && cell[0] < board.length) && (cell[1] >= 0 && cell[1] < board[0].length);
	}

	static int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
}
