package practice.graph.kahn;

import static java.util.Collections.emptyList;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* 04 Oct 2025 13:53 */

/** [3673 · Parallel Courses](https://www.lintcode.com/problem/3673/) */
public class ParallelCourses {
	public int minimumSemesters(int n, int[][] relations) {
		var diGraph = new HashMap<Integer, List<Integer>>();
		var inDegree = new int[n + 1];
		for (var relation : relations) {
			diGraph.computeIfAbsent(relation[0], _ -> new ArrayList<>()).add(relation[1]);
			inDegree[relation[1]]++;
		}
		var queue = new ArrayDeque<Integer>();
		for (var i = 1; i <= n; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}
		var result = 0;
		while (!queue.isEmpty()) {
			result++;
			var qSize = queue.size();
			for (var i = 0; i < qSize; i++) {
				var u = queue.removeFirst();
				n--; // ! visit on dequeue
				for (var v : diGraph.getOrDefault(u, emptyList())) {
					inDegree[v]--;
					if (inDegree[v] == 0) {
						queue.add(v);
					}
				}
			}
		}
		return n == 0 ? result : -1;
	}

	static void main() {
		var parallelCourses = new ParallelCourses();
		System.out.println(parallelCourses.minimumSemesters(3, new int[][] {{1, 3}, {2, 3}})); // 2
		System.out.println(
				parallelCourses.minimumSemesters(3, new int[][] {{1, 2}, {2, 3}, {3, 1}})); // -1
		System.out.println(
				parallelCourses.minimumSemesters(
						5,
						new int[][] {
							{1, 2}, {5, 2}, {1, 5}, {3, 2}, {4, 1}, {3, 5}, {4, 2}, {1, 3}, {4, 3}, {4, 5}
						})); // 5
	}
}
