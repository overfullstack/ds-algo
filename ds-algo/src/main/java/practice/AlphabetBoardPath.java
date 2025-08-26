package practice;

import ds.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class AlphabetBoardPath {
	private static final String[] board = {"abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"};
	private static final int[][] directions = {{-1, 0, 'U'}, {1, 0, 'D'}, {0, -1, 'L'}, {0, 1, 'R'}};

	public String alphabetBoardPath(String target) {
		var result = new ArrayList<String>();
		var start = new int[] {0, 0};
		for (var c : target.toCharArray()) {
			var pair = bfs(start, c, new boolean[board.length][board[0].length()]);
			start = pair.first();
			result.add(pair.second());
		}
		return String.join("!", result) + "!";
	}

	private static Pair<int[], String> bfs(int[] start, char target, boolean[][] visited) {
		var queue = new LinkedList<Pair<int[], String>>();
		queue.add(Pair.of(new int[] {start[0], start[1], 0}, ""));
		while (!queue.isEmpty()) {
			var pair = queue.poll();
			var cell = pair.first();
			var path = pair.second();
			if (board[cell[0]].charAt(cell[1]) == target) {
				return Pair.of(cell, path);
			}
			visited[cell[0]][cell[1]] = true;
			Arrays.stream(directions)
					.map(dir -> new int[] {cell[0] + dir[0], cell[1] + dir[1], dir[2]})
					.filter(nextCell -> isValid(nextCell) && !visited[nextCell[0]][nextCell[1]])
					.forEach(nextCell -> queue.add(Pair.of(nextCell, path + (char) nextCell[2])));
		}
		return null;
	}

	private static boolean isValid(int[] cell) {
		return cell[0] >= 0
				&& cell[0] < board.length
				&& cell[1] >= 0
				&& (cell[0] == 5 ? cell[1] == 0 : cell[1] < board[0].length());
	}

	static void main() {
		System.out.println(new AlphabetBoardPath().alphabetBoardPath("leet"));
	}
}
