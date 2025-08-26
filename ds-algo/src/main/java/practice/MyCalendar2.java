package practice;

import java.util.Map;
import java.util.TreeMap;

/** [731. My Calendar II](https://leetcode.com/problems/my-calendar-ii) */
public class MyCalendar2 {

	final Map<Integer, Integer> map;

	public MyCalendar2() {
		map = new TreeMap<>(); // ! TreeMap so entries are stored in sorted order
	}

	public boolean book(int startTime, int endTime) {
		map.merge(startTime, 1, (old, _) -> old + 1);
		map.merge(endTime, -1, (old, _) -> old - 1);
		var count = 0;
		for (var entry : map.entrySet()) {
			count += entry.getValue();
			if (count > 2) {
				map.computeIfPresent(startTime, (_, curVal) -> (curVal == 1) ? null : curVal - 1);
				map.computeIfPresent(endTime, (_, curVal) -> (curVal == -1) ? null : curVal + 1);
				return false;
			}
		}
		return true;
	}
}
