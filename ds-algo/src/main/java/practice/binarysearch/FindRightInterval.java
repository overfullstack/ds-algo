package practice.binarysearch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/* 19 Oct 2025 07:16 */

/**
 * [436. Find Right Interval](https://leetcode.com/problems/find-right-interval/)
 */
public class FindRightInterval {
  public int[] findRightInterval(int[][] intervals) {
    final var sortedStartIntervalIdx = IntStream.range(0, intervals.length).boxed()
        .sorted(Comparator.comparingInt(i -> intervals[i][0])).mapToInt(i -> i).toArray();
    final var result = new int[sortedStartIntervalIdx.length];
    for (var i = 0; i < intervals.length; i++) {
      result[i] = leftmostIdx(intervals[i][1], sortedStartIntervalIdx, intervals);
    }
    return result;
  }

  private int leftmostIdx(int end, int[] sortedStartIntervalIdx, int[][] intervals) {
    var left = 0;
    var right = sortedStartIntervalIdx.length - 1;
    while (left < right) {
      var mid = left + (right - left) / 2;
      if (end <= intervals[sortedStartIntervalIdx[mid]][0]) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    var intervalIdx = sortedStartIntervalIdx[right];
    return intervals[intervalIdx][0] < end ? -1 : intervalIdx;
  }

  static void main() {
    var findRightInterval = new FindRightInterval();
    var intervals = new int[][]{{3, 4}, {2, 3}, {1, 2}};
    System.out.println(
        Arrays.stream(findRightInterval.findRightInterval(intervals))
            .mapToObj(i -> i + " ")
            .toList()); // [-1,0,1]
    intervals = new int[][]{{1, 4}, {2, 3}, {3, 4}};
    System.out.println(
        Arrays.stream(findRightInterval.findRightInterval(intervals))
            .mapToObj(i -> i + " ")
            .toList()); // [-1,2,-1]
    intervals = new int[][]{{1, 2}};
    System.out.println(
        Arrays.stream(findRightInterval.findRightInterval(intervals))
            .mapToObj(i -> i + " ")
            .toList()); // [-1]
  }
}
