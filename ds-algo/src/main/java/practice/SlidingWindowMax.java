package practice;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class SlidingWindowMax {
	public int[] maxSlidingWindow(int[] nums, int k) {
		var queue = new ArrayDeque<Integer>();
		for (var i = 0; i < k; i++) {
			while (!queue.isEmpty() && nums[queue.getLast()] <= nums[i]) {
				queue.removeLast();
			}
			queue.add(nums[i]);
		}
		var result = new ArrayList<Integer>();
		for (var i = k; i < nums.length; i++) {
			result.add(nums[queue.getFirst()]);
			while (!queue.isEmpty() && queue.getFirst() <= (i - k)) {
				queue.removeFirst();
			}
			while (!queue.isEmpty() && nums[queue.getLast()] <= nums[i]) {
				queue.removeLast();
			}
			queue.add(i);
		}
		result.add(nums[queue.getFirst()]);
		return result.stream().mapToInt(Integer::intValue).toArray();
	}
}
