package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParallelCourses {
	public int minimumSemesters(int n, int[][] relations) {
		var diGraph = new HashMap<Integer, List<Integer>>();
		for (var relation : relations) {
			diGraph.computeIfAbsent(relation[0], _ -> new ArrayList<>()).add(relation[1]);
		}
		var visited = new HashSet<Integer>();
		var cache = new HashMap<Integer, Integer>();
		return diGraph.keySet().stream()
				.mapToInt(course -> dfsPerGroup(course, diGraph, cache, visited))
				.max()
				.orElse(0);
	}

	private int dfsPerGroup(
			int course,
			Map<Integer, List<Integer>> diGraph,
			Map<Integer, Integer> cache,
			Set<Integer> visited) {
		if (cache.containsKey(course)) {
			return cache.get(course);
		}
		visited.add(course);
		var minSemesters =
				1
						+ diGraph.get(course).stream()
								.mapToInt(toCourse -> dfsPerGroup(toCourse, diGraph, cache, visited))
								.max()
								.orElse(0);
		cache.put(course, minSemesters);
		return minSemesters;
	}
}
