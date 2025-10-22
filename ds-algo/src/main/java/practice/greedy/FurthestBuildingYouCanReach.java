package practice.greedy;

import java.util.PriorityQueue;

/* 26 Aug 2025 15:42 */

/**
 * [1642. Furthest Building You Can
 * Reach](https://leetcode.com/problems/furthest-building-you-can-reach)
 */
public class FurthestBuildingYouCanReach {
	public int furthestBuilding(int[] heights, int bricks, int ladders) {
		// * Greedily use Bricks for lower heightDiffs
		// * Ladders shall auto-cover the higher heightDiffs
		var minHeap = new PriorityQueue<Integer>();
		for (var i = 1; i < heights.length; i++) {
			var heightDiff = heights[i] - heights[i - 1];
			if (heightDiff > 0) {
				minHeap.add(heightDiff);
			}
			if (minHeap.size() > ladders) {
				bricks -= minHeap.poll();
			}
			if (bricks < 0) {
				return i - 1;
			}
		}
		return heights.length - 1;
	}
}
