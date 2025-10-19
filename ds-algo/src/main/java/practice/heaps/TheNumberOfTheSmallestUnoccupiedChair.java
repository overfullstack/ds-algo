package practice.heaps;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/* 19 Oct 2025 17:04 */

/**
 * [1942. The Number of the Smallest Unoccupied
 * Chair](https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/)
 */
public class TheNumberOfTheSmallestUnoccupiedChair {
	public int smallestChair(int[][] times, int targetFriend) {
		final var occupiedChairs =
				new PriorityQueue<>(
						Comparator.<int[]>comparingInt(ec -> ec[0]).thenComparingInt(ec -> ec[1]));
		final var availableChairs =
				new PriorityQueue<>(IntStream.range(0, times.length).boxed().toList());
		var sortedTimes =
				IntStream.range(0, times.length)
						.mapToObj(i -> new int[] {i, times[i][0], times[i][1]})
						.sorted(Comparator.<int[]>comparingInt(t -> t[1]).thenComparingInt(t -> t[2]))
						.toList();
		for (var time : sortedTimes) {
			var idx = time[0];
			var start = time[1];
			var end = time[2];
			while (!occupiedChairs.isEmpty() && occupiedChairs.peek()[0] <= start) {
				availableChairs.add(occupiedChairs.poll()[1]);
			}
			// ! Infinite chairs as per problem, so they never will be empty
			var chair = availableChairs.poll();
			if (idx == targetFriend) {
				return chair;
			}
			occupiedChairs.add(new int[] {end, chair});
		}
		return -1;
	}

	static void main() {
		var smallestChair = new TheNumberOfTheSmallestUnoccupiedChair();
		var times2 = new int[][] {{3, 10}, {1, 5}, {2, 6}};
		System.out.println(smallestChair.smallestChair(times2, 0)); // 2
		var times = new int[][] {{1, 4}, {2, 3}, {3, 5}};
		System.out.println(smallestChair.smallestChair(times, 1)); // 1
	}
}
