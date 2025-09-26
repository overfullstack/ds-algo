package practice;

import java.util.HashMap;
import java.util.TreeSet;

/* 28 Aug 2025 16:15 */

/** [1488. Avoid Flood in The City](https://leetcode.com/problems/avoid-flood-in-the-city) */
public class AvoidFloodInTheCity {
	public int[] avoidFlood(int[] rains) {
		var result = new int[rains.length];
		var resultIdx = 0;
		var dryDays = new TreeSet<Integer>();
		var lakeToLastRainDay = new HashMap<Integer, Integer>();
		for (var i = 0; i < rains.length; i++, resultIdx++) {
			if (rains[i] == 0) {
				dryDays.add(i);
				result[resultIdx] = 1; // Specific to problem output
			} else {
				if (lakeToLastRainDay.containsKey(rains[i])) {
					var lastRainDay = lakeToLastRainDay.get(rains[i]);
					var nextDryDay = dryDays.higher(lastRainDay);
					if (nextDryDay == null) {
						return new int[] {};
					}
					dryDays.remove(nextDryDay);
					result[nextDryDay] = rains[i];
				}
				lakeToLastRainDay.put(rains[i], i);
				result[resultIdx] = -1;
			}
		}
		return result;
	}
}
