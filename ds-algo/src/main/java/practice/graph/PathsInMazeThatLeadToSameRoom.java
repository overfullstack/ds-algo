package practice.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* 04 Oct 2025 10:56 */

/**
 * [2077 - Paths in Maze That Lead to Same
 * Room](https://leetcode.ca/2021-08-07-2077-Paths-in-Maze-That-Lead-to-Same-Room/)
 */
public class PathsInMazeThatLeadToSameRoom {
	public int numberOfPaths(int n, int[][] corridors) {
		var diGraph = new HashMap<Integer, List<Integer>>();
		for (var corridor : corridors) {
			diGraph.computeIfAbsent(corridor[0], _ -> new ArrayList<>()).add(corridor[1]);
			diGraph.computeIfAbsent(corridor[1], _ -> new ArrayList<>()).add(corridor[0]);
		}
		var result = 0;
		for (var node = 1; node <= n; node++) {
			var neighbors = diGraph.get(node);
			for (var v1Idx = 1; v1Idx <= neighbors.size(); v1Idx++) {
				for (var v2Idx = v1Idx + 1; v2Idx <= neighbors.size(); v2Idx++) {
					var v1 = neighbors.get(v1Idx);
					var v2 = neighbors.get(v2Idx);
					if (diGraph.get(v1).contains(v2)) {
						result++;
					}
				}
			}
		}
		return result / 3;
	}

	static void main() {
		var solution = new PathsInMazeThatLeadToSameRoom();
		int n = 5;
		int[][] corridors = {{1, 2}, {5, 2}, {4, 1}, {2, 4}, {3, 1}, {3, 4}};
		System.out.println(solution.numberOfPaths(n, corridors));
		int[][] corridors2 = {{1, 2}, {3, 4}};
		System.out.println(solution.numberOfPaths(n, corridors2));
	}
}
