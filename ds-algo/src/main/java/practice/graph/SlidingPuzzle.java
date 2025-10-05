package practice.graph;

import ds.util.Pair;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

/* 05 Oct 2025 09:12 */

/** [773. Sliding Puzzle](https://leetcode.com/problems/sliding-puzzle) */
public class SlidingPuzzle {
	private static final int[][] directions = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
	private static final String TARGET = "123450";

	public int slidingPuzzle(int[][] board) {
		var boardStr =
				Arrays.stream(board)
						.map(row -> Arrays.stream(row).mapToObj(String::valueOf).collect(Collectors.joining()))
						.collect(Collectors.joining());
		var queue = new ArrayDeque<Pair<String, Integer>>();
		var visited = new HashSet<String>();
		queue.add(Pair.of(boardStr, 0));
		visited.add(boardStr);
		while (!queue.isEmpty()) {
			var pair = queue.removeFirst();
			var curBoardStr = pair.first();
			var curMoves = pair.second();
			if (curBoardStr.equals(TARGET)) {
				return curMoves;
			}
			var zeroPos = curBoardStr.indexOf('0');
			Arrays.stream(directions[zeroPos])
					.mapToObj(nextPos -> swap(nextPos, zeroPos, curBoardStr))
					.filter(nextBoardStr -> !visited.contains(nextBoardStr))
					.forEach(
							nextBoardStr -> {
								visited.add(nextBoardStr);
								queue.add(Pair.of(nextBoardStr, curMoves + 1));
							});
		}
		return -1;
	}

	private static String swap(int pos1, int pos2, String boardStr) {
		var boardStrBuilder = new StringBuilder(boardStr);
		boardStrBuilder.setCharAt(pos1, boardStr.charAt(pos2));
		boardStrBuilder.setCharAt(pos2, boardStr.charAt(pos1));
		return boardStrBuilder.toString();
	}

	static void main() {
		var slidingPuzzle = new SlidingPuzzle();
		System.out.println(slidingPuzzle.slidingPuzzle(new int[][] {{1, 2, 3}, {4, 0, 5}})); // 1
		System.out.println(slidingPuzzle.slidingPuzzle(new int[][] {{1, 2, 3}, {5, 4, 0}})); // -1
		System.out.println(slidingPuzzle.slidingPuzzle(new int[][] {{1, 2, 3}, {4, 5, 0}})); // 0
		System.out.println(slidingPuzzle.slidingPuzzle(new int[][] {{4, 1, 2}, {5, 0, 3}})); // 5
	}
}
