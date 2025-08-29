package practice;

/* 29 Aug 2025 15:53 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * [1584. Min Cost to Connect All
 * Points](https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended)
 */
public class MaximumNumberOfEventsThatCanBeAttended {
	public int maxEvents(int[][] events) {
		var minEndHeap = new PriorityQueue<Integer>();
		var eventIdx = 0;
		var day = 0;
		var attendedEventCount = 0;
		Arrays.sort(events, Comparator.comparingInt(e -> e[0]));
		while (!minEndHeap.isEmpty() || eventIdx < events.length) {
			if (minEndHeap.isEmpty()) {
				day = events[eventIdx][0]; // ! Jump skip to next non-overlapping interval
			}
			while (eventIdx < events.length && events[eventIdx][0] == day) {
				minEndHeap.add(events[eventIdx][1]);
				// ! `eventIdx` may exhaust here
				// ! so the while loop above continues till `minEndHeap` is fully polled
				eventIdx++;
			}
			minEndHeap.poll(); // ! Only one event can be attended per day
			attendedEventCount++;
			day++; // ! Moving 1 day at a time
			while (!minEndHeap.isEmpty() && minEndHeap.peek() < day) {
				minEndHeap.poll(); // ! Events  we cannot attend
			}
		}
		return attendedEventCount;
	}

	static void main() {
		var m = new MaximumNumberOfEventsThatCanBeAttended();
		System.out.println(
				m.maxEvents(new int[][] {{1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}})); // 7
		System.out.println(m.maxEvents(new int[][] {{1, 4}, {4, 4}, {2, 2}, {3, 4}, {1, 1}})); // 4
		System.out.println(m.maxEvents(new int[][] {{1, 2}, {2, 3}, {3, 4}})); // 3
		System.out.println(m.maxEvents(new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 2}})); // 4
		System.out.println(m.maxEvents(new int[][] {{1, 1}, {2, 3}, {3, 4}, {1, 1}})); // 3
	}
}
