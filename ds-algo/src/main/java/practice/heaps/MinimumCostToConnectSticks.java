package practice.heaps;

/* 19 Oct 2025 12:21 */

import java.util.List;
import java.util.PriorityQueue;

/** [1872 · Minimum Cost to Connect Sticks](https://www.lintcode.com/problem/1872/) */
public class MinimumCostToConnectSticks {
	public int minimumCost(List<Integer> sticks) {
		final var pq = new PriorityQueue<>(sticks);
		var cost = 0;
		while (pq.size() > 1) {
			final var a = pq.poll();
			final var b = pq.poll();
			cost += a + b;
			pq.add(a + b);
		}
		return cost;
	}

	static void main() {
		var minimumCostToConnectSticks = new MinimumCostToConnectSticks();
		System.out.println(minimumCostToConnectSticks.minimumCost(List.of(2, 4, 3))); // 14
		System.out.println(minimumCostToConnectSticks.minimumCost(List.of(1, 8, 3, 5))); // 30
	}
}
