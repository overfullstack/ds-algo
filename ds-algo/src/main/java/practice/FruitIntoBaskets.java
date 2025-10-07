package practice;

import java.util.HashMap;

/* 27 Aug 2025 21:09 */

/** [904. Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets) */
public class FruitIntoBaskets {
	public int totalFruit(int[] fruits) {
		var startIdx = 0;
		var maxFruits = Integer.MIN_VALUE;
		var freqMap = new HashMap<Integer, Integer>();
		for (var i = 0; i < fruits.length; i++) {
			freqMap.merge(fruits[i], 1, Integer::sum);
			while (freqMap.size() > 2) {
				freqMap.computeIfPresent(fruits[startIdx], (_, value) -> value == 1 ? null : value - 1);
				startIdx++;
			}
			maxFruits = Math.max(maxFruits, i - startIdx + 1);
		}
		return maxFruits;
	}

	static void main() {
		var sol = new FruitIntoBaskets();
		System.out.println(sol.totalFruit(new int[] {1, 2, 1})); // 3
		System.out.println(sol.totalFruit(new int[] {0, 1, 2, 2})); // 3
		System.out.println(sol.totalFruit(new int[] {1, 2, 3, 2, 2})); // 4
	}
}
