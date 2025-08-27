package practice;

import java.util.HashMap;

/* 27 Aug 2025 21:09 */

public class FruitIntoBaskets {
	public int totalFruit(int[] fruits) {
		var startIdx = 0;
		var maxFruits = Integer.MIN_VALUE;
		var freqMap = new HashMap<Integer, Integer>();
		for (var i = 0; i < fruits.length; i++) {
			freqMap.merge(fruits[i], 1, (old, _) -> old + 1);
			while (freqMap.size() > 2) {
				freqMap.computeIfPresent(fruits[startIdx], (_, value) -> value == 1 ? null : value - 1);
				startIdx++;
			}
			maxFruits = Math.max(maxFruits, i - startIdx + 1);
		}
		return maxFruits;
	}
}
