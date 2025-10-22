package practice.greedy;

import java.util.Arrays;
import java.util.Comparator;

/* 22 Oct 2025 20:19 */

/** [1029. Two City Scheduling](https://leetcode.com/problems/two-city-scheduling/) */
public class TwoCityScheduling {
	public int twoCitySchedCost(int[][] costs) {
		// ! Sort based on savings if traveled to A instead of B.
		Arrays.sort(costs, Comparator.comparingInt(c -> c[0] - c[1]));
		var minCost = 0;
		// ! Early entries are optimum to go to A compared to later entries
		// ! So first `n/2` travels to A, and next `n/2` travels to B
		for (var i = 0; i < costs.length / 2; i++) {
			minCost += costs[i][0] + costs[i + costs.length / 2][1];
		}
		return minCost;
	}

	static void main() {
		var t = new TwoCityScheduling();
		System.out.println(
				t.twoCitySchedCost(new int[][] {{10, 20}, {30, 200}, {400, 50}, {30, 20}})); // 110
		System.out.println(
				t.twoCitySchedCost(
						new int[][] {
							{259, 770}, {448, 54}, {926, 667}, {184, 139}, {840, 118}, {577, 469}
						})); // 1859
		System.out.println(
				t.twoCitySchedCost(
						new int[][] {
							{515, 563},
							{451, 713},
							{537, 709},
							{343, 819},
							{855, 779},
							{457, 60},
							{650, 359},
							{631, 42}
						})); // 3086
	}
}
