package practice.graph.kahn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 07 Sep 2025 19:09 */

/** [851. Loud and Rich](https://leetcode.com/problems/loud-and-rich) */
public class LoudAndRich {
	public int[] loudAndRich(int[][] richer, int[] quiet) {
		var graph = new HashMap<Integer, List<Integer>>();
		var inDegree = new int[quiet.length];
		for (var edge : richer) { // ! rich -> poor
			graph.computeIfAbsent(edge[0], _ -> new ArrayList<>()).add(edge[1]);
			inDegree[edge[1]]++;
		}
		var minQuietIdx = new int[quiet.length];
		var minQuiet = new int[quiet.length];
		var queue = new ArrayDeque<Integer>();
		for (var i = 0; i < inDegree.length; i++) {
			minQuietIdx[i] = i;
			minQuiet[i] = quiet[i];
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}
		while (!queue.isEmpty()) {
			var richerPerson = queue.poll();
			for (var person : graph.getOrDefault(richerPerson, Collections.emptyList())) {
				// ! You arrive at a person from different richer person parents
				if (minQuiet[richerPerson] < minQuiet[person]) {
					minQuietIdx[person] = minQuietIdx[richerPerson]; // ! Set the corresponding Idx recorded
					minQuiet[person] = minQuiet[richerPerson];
				}
				inDegree[person]--;
				if (inDegree[person] == 0) {
					queue.add(person);
				}
			}
		}
		return minQuietIdx;
	}

	public int[] loudAndRichDFS(int[][] richer, int[] quiet) {
		var graph = new HashMap<Integer, List<Integer>>();
		for (var edge : richer) {
			graph.computeIfAbsent(edge[1], _ -> new ArrayList<>()).add(edge[0]);
		}
		var minLoudest = new int[quiet.length];
		Arrays.fill(minLoudest, -1); // ! For memoization check
		for (var i = 0; i < minLoudest.length; i++) {
			dfs(i, graph, quiet, minLoudest);
		}
		return minLoudest;
	}

	private static int dfs(
			int personIdx, Map<Integer, List<Integer>> graph, int[] quiet, int[] minLoudest) {
		if (minLoudest[personIdx] >= 0) {
			return minLoudest[personIdx];
		}
		var minLoudestIdx =
				graph.getOrDefault(personIdx, Collections.emptyList()).stream()
						.map(richerPersonIdx -> dfs(richerPersonIdx, graph, quiet, minLoudest))
						// ! Separate `min` as we need to results in `idx` and not `richerPersonIdx`
						.min(Comparator.comparingInt(idx -> quiet[idx]))
						.filter(idx -> quiet[idx] < quiet[personIdx])
						.orElse(personIdx);

		minLoudest[personIdx] = minLoudestIdx;
		return minLoudestIdx;
	}

	static void main() {
		var loudAndRich = new LoudAndRich();
		System.out.println(
				Arrays.toString(
						loudAndRich.loudAndRich(
								new int[][] {{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}},
								new int[] {3, 2, 5, 4, 6, 1, 7, 0}))); // [5,5,2,5,4,5,6,7]
		System.out.println(
				Arrays.toString(
						loudAndRich.loudAndRichDFS(
								new int[][] {{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}},
								new int[] {3, 2, 5, 4, 6, 1, 7, 0}))); // [5,5,2,5,4,5,6,7]
	}
}
