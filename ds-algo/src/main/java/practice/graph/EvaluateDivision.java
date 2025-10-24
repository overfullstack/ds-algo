package practice.graph;

import ds.util.Pair;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* 07 Oct 2025 22:21 */

/** [399. Evaluate Division](https://leetcode.com/problems/evaluate-division/) */
public class EvaluateDivision {
	public double[] calcEquation(
			List<List<String>> equations, double[] values, List<List<String>> queries) {
		var biDiGraph = new HashMap<String, List<Pair<String, Double>>>();
		for (var i = 0; i < equations.size(); i++) {
			var equation = equations.get(i);
			biDiGraph
					.computeIfAbsent(equation.get(0), _ -> new ArrayList<>())
					.add(Pair.of(equation.get(1), values[i]));
			biDiGraph
					.computeIfAbsent(equation.get(1), _ -> new ArrayList<>())
					.add(Pair.of(equation.get(0), 1 / values[i]));
		}
		return queries.stream().mapToDouble(q -> solve(q.get(0), q.get(1), biDiGraph)).toArray();
	}

	private static double solve(
			String src, String dest, Map<String, List<Pair<String, Double>>> biDiGraph) {
		if (src.equals(dest)) {
			return biDiGraph.containsKey(src) ? 1.0 : -1.0;
		}
		if (!biDiGraph.containsKey(src)) {
			return -1.0;
		}

		var queue = new ArrayDeque<Pair<String, Double>>();
		var visited = new HashSet<String>();
		queue.add(Pair.of(src, 1.0));
		visited.add(src);
		while (!queue.isEmpty()) {
			var cur = queue.removeFirst();
			var a = cur.first();
			var val = cur.second();
			if (a.equals(dest)) {
				return val;
			}
			for (var b : biDiGraph.getOrDefault(a, Collections.emptyList())) {
				var bStr = b.first();
				var bVal = b.second();
				if (!visited.contains(bStr)) {
					visited.add(bStr);
					queue.add(Pair.of(bStr, val * bVal));
				}
			}
		}
		return -1;
	}

	static void main() {
		var equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
		var values = new double[] {2.0, 3.0};
		var queries =
				Arrays.asList(
						Arrays.asList("a", "c"),
						Arrays.asList("b", "a"),
						Arrays.asList("a", "e"),
						Arrays.asList("a", "a"),
						Arrays.asList("x", "x"));
		var result = new EvaluateDivision().calcEquation(equations, values, queries);
		System.out.println(
				Arrays.stream(result)
						.mapToObj(r -> r + " ")
						.toList()
						.toString()); // 6.0, 0.5, -1.0, 1.0, -1.0
		var equations2 =
				Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"), Arrays.asList("bc", "cd"));
		var values2 = new double[] {1.5, 2.5, 5.0};
		var queries2 =
				Arrays.asList(
						Arrays.asList("a", "c"),
						Arrays.asList("c", "b"),
						Arrays.asList("bc", "cd"),
						Arrays.asList("cd", "bc"));
		var result2 = new EvaluateDivision().calcEquation(equations2, values2, queries2);
		System.out.println(
				Arrays.stream(result2).mapToObj(r -> r + " ").toList()); // 3.75, 0.4, 5.0, 0.2
	}
}
