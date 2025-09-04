package practice.graph;

import java.util.TreeSet;

/* 29 Aug 2025 08:08 */

/** [475. Heaters](https://leetcode.com/problems/heaters/) */
public class Heaters {
	public int findRadius(int[] houses, int[] heaters) {
		var heatersTreeSet = new TreeSet<Integer>();
		for (var heater : heaters) {
			heatersTreeSet.add(heater);
		}
		var result = 0;
		for (var house : houses) {
			var leftHeater = heatersTreeSet.floor(house);
			var rightHeater = heatersTreeSet.ceiling(house);
			var leftDistance = leftHeater == null ? Integer.MAX_VALUE : house - leftHeater;
			var rightDistance = rightHeater == null ? Integer.MAX_VALUE : rightHeater - house;
			var closestHeaterDistance = Math.min(leftDistance, rightDistance);
			result = Math.max(result, closestHeaterDistance);
		}
		return result;
	}

	static void main() {
		var heaters = new Heaters();
		System.out.println(heaters.findRadius(new int[] {1, 2, 3}, new int[] {2}));
		System.out.println(heaters.findRadius(new int[] {1, 2, 3, 4}, new int[] {1, 4}));
		System.out.println(heaters.findRadius(new int[] {1, 5}, new int[] {2}));
	}
}
