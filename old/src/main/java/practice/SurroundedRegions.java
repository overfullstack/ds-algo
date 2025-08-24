package practice;

import ds.util.Pair;
import java.util.List;

/** [130. Surrounded Regions](https://leetcode.com/problems/surrounded-regions) */
public class SurroundedRegions {
	public void solve(char[][] board) {
		// left, right
		for (var row = 0; row < board.length; row++) {
			if (board[row][0] == 'O') {
				dfs(Pair.of(row, 0), board);
			}
			if (board[row][board[0].length - 1] == 'O') {
				dfs(Pair.of(row, board[0].length - 1), board);
			}
		}
		for (var col = 0; col < board[0].length; col++) {
			if (board[0][col] == 'O') {
				dfs(Pair.of(0, col), board);
			}
			if (board[board.length - 1][col] == 'O') {
				dfs(Pair.of(board.length - 1, col), board);
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

	private void dfs(Pair<Integer, Integer> cell, char[][] board) {
		board[cell.first()][cell.second()] = '*';
		DIRECTIONS.stream()
				.map(d -> Pair.of(d.first() + cell.first(), d.second() + cell.second()))
				.filter(
						nextCell ->
								isValid(nextCell, board) && board[nextCell.first()][nextCell.second()] == 'O')
				.forEach(nextCell -> dfs(nextCell, board));
	}

	private boolean isValid(Pair<Integer, Integer> cell, char[][] board) {
		return (cell.first() >= 0 && cell.first() < board.length)
				&& (cell.second() >= 0 && cell.second() < board[0].length);
	}

	private static final List<Pair<Integer, Integer>> DIRECTIONS =
			List.of(Pair.of(-1, 0), Pair.of(1, 0), Pair.of(0, -1), Pair.of(0, 1));
}
