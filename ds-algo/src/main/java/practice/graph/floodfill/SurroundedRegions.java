package practice.graph.floodfill;

import java.util.Arrays;

/** [130. Surrounded Regions](https://leetcode.com/problems/surrounded-regions) */
public class SurroundedRegions {
	public void solve(char[][] board) {
		// ! Mark 'O' groups attached to left, right edges
		for (var row = 0; row < board.length; row++) {
			dfsGroup(new int[] {row, 0}, board);
			dfsGroup(new int[] {row, board[0].length - 1}, board);
		}
		// ! Mark 'O' groups attached to top, bottom edges
		for (var col = 0; col < board[0].length; col++) {
			dfsGroup(new int[] {0, col}, board);
			dfsGroup(new int[] {board.length - 1, col}, board);
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
		if (!isValid(cell, board) || board[cell[0]][cell[1]] != 'O') {
			return;
		}
		// ! Mark 0s found attached to borders differently
		// ! to distinguish them from the ones that are surrounded
		board[cell[0]][cell[1]] = '*';
		Arrays.stream(directions)
				.map(d -> new int[] {d[0] + cell[0], d[1] + cell[1]})
				.forEach(nextCell -> dfsGroup(nextCell, board));
	}

	private static boolean isValid(int[] cell, char[][] board) {
		return (cell[0] >= 0 && cell[0] < board.length) && (cell[1] >= 0 && cell[1] < board[0].length);
	}

	static int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
}
