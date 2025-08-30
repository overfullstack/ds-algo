package practice;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* 30 Aug 2025 12:40 */

/** [2050. Parallel Courses III](https://leetcode.com/problems/parallel-courses-iii) */
public class ParallelCourses3 {
	public int minimumTime(int n, int[][] relations, int[] time) {
		var diGraph = new HashMap<Integer, Set<Integer>>();
		var inDegree = new int[n + 1];
		for (var relation : relations) {
			diGraph.computeIfAbsent(relation[0], _ -> new HashSet<>()).add(relation[1]);
			inDegree[relation[1]]++;
		}
		var queue = new ArrayDeque<Integer>();
		var maxTime = new int[n + 1];
		for (var i = 1; i < inDegree.length; i++) {
			if (inDegree[i] == 0) { // ! Top-Leaves in an inverted tree with no dependencies
				queue.add(i);
				maxTime[i] = time[i - 1]; // ! `-1` as it's 0-indexed
			}
		}
		while (!queue.isEmpty()) {
			var course = queue.poll();
			var dependencies = diGraph.get(course);
			if (dependencies != null && !dependencies.isEmpty()) {
				for (var dependency : dependencies) {
					maxTime[dependency] =
							Math.max(maxTime[dependency], maxTime[course] + time[dependency - 1]);
					inDegree[dependency]--;
					if (inDegree[dependency] == 0) {
						queue.add(dependency);
					}
				}
			}
		}
		return Arrays.stream(maxTime).max().orElse(0);
	}

	static void main() {
		var parallelCourses3 = new ParallelCourses3();
		System.out.println(
				parallelCourses3.minimumTime(3, new int[][] {{1, 3}, {2, 3}}, new int[] {3, 2, 5})); // 8
		System.out.println(
				parallelCourses3.minimumTime(
						5,
						new int[][] {{1, 5}, {2, 5}, {3, 5}, {3, 4}, {4, 5}},
						new int[] {1, 2, 3, 4, 5})); // 12
	}
}
