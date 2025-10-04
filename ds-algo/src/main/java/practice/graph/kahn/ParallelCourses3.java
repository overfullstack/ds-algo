package practice.graph.kahn;

import static java.util.Collections.emptySet;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* 30 Aug 2025 12:40 */

/** [2050. Parallel Courses III](https://leetcode.com/problems/parallel-courses-iii) */
public class ParallelCourses3 {
	public int minimumTime(int n, int[][] relations, int[] time) { // * Kahn's algorithm
		var diGraph = new HashMap<Integer, Set<Integer>>();
		var inDegree = new int[n + 1];
		for (var relation : relations) {
			diGraph.computeIfAbsent(relation[0], _ -> new HashSet<>()).add(relation[1]);
			inDegree[relation[1]]++; // ! prevCourse ([0]) -> nextCourse ([1])
		}
		// * Topological sort with BFS
		var queue = new ArrayDeque<Integer>();
		var maxTimes = new int[n + 1];
		for (var i = 1; i <= n; i++) {
			if (inDegree[i] == 0) { // ! Top Leaf-courses with only next courses
				queue.add(i);
				maxTimes[i] = time[i - 1]; // ! `-1` as it's 0-indexed
			}
		}
		while (!queue.isEmpty()) {
			var prevCourse = queue.poll();
			for (var course : diGraph.getOrDefault(prevCourse, emptySet())) {
				// ! The time to complete nextCourse is the time it takes to complete its
				// ! longest prerequisite path (maxTimes[prevCourse]) plus the time for nextCourse itself.
				// ! You arrive at a course from different prevCourse parents
				// ! Record if another prevCourse arrived with a longer time
				maxTimes[course] = Math.max(maxTimes[course], maxTimes[prevCourse] + time[course - 1]);
				inDegree[course]--;
				if (inDegree[course] == 0) {
					queue.add(course);
				}
			}
		}
		return Arrays.stream(maxTimes).max().orElse(0);
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
