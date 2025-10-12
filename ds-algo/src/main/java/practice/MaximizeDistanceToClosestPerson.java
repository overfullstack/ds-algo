package practice;

/* 27 Aug 2025 16:24 */

/**
 * [849. Maximize Distance to Closest
 * Person](https://leetcode.com/problems/maximize-distance-to-closest-person)
 */
public class MaximizeDistanceToClosestPerson {
	public int maxDistToClosest(int[] seats) {
		var startIdx = -1; // ! Negative to mark not initiated
		var maxDistance = Integer.MIN_VALUE;
		for (var i = 0; i < seats.length; i++) {
			if (seats[i] == 1) {
				// ! div 2 indicates to sit in the window middle
				// ! `startIdx < 0` indicates the first window
				maxDistance = startIdx < 0 ? i : Math.max(maxDistance, (i - startIdx) / 2);
				startIdx = i;
			}
		}
		return Math.max(maxDistance, seats.length - 1 - startIdx); // ! For the last window
	}
}
