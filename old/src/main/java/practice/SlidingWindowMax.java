package practice;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class SlidingWindowMax {
  public int[] maxSlidingWindow(int[] nums, int k) {
    var dequeue = new ArrayDeque<Integer>();
    for (var i = 0; i < k; i++) {
      while (!dequeue.isEmpty() && nums[dequeue.getLast()] <= nums[i]) {
        dequeue.removeLast();
      }
      dequeue.add(nums[i]);
    }
    var result = new ArrayList<Integer>();
    for (var i = k; i < nums.length; i++) {
      result.add(nums[dequeue.getFirst()]);
      while (!dequeue.isEmpty() && dequeue.getFirst() <= (i - k)) {
        dequeue.removeFirst();
      }
      while (!dequeue.isEmpty() && nums[dequeue.getLast()] <= nums[i]) {
        dequeue.removeLast();
      }
      dequeue.add(i);
    }
    result.add(nums[dequeue.getFirst()]);
    return result.stream().mapToInt(Integer::intValue).toArray();
  }
}
