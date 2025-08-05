package gfg.practice.arrays.MergeOverlaps;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/** Created by Gopala Akshintala on 10/04/17. */
public class MergeOverlaps {
	static void main() {
		var scn = new Scanner(System.in);
		var tests = scn.nextInt();
		List<List<Interval>> inputIntervals = new ArrayList<>();
		while (tests-- > 0) {
			var len = scn.nextInt();
			var intervals = readIntervals(scn, len);
			inputIntervals.add(intervals);
		}

		inputIntervals.forEach(
				intervals -> {
					mergeIntervals(intervals).forEach(System.out::print);
					System.out.println();
				});
	}

	private static List<Interval> mergeIntervals(List<Interval> intervals) {
		List<Interval> mergedIntervals = new ArrayList<>();
		var sortedIntervals =
				intervals.stream().sorted(compareBy(i -> i.startTime)).collect(Collectors.toList());
		var prev = sortedIntervals.get(0);
		for (var cur : sortedIntervals) {
			if (prev.endTime >= cur.startTime) {
				prev.endTime = Math.max(prev.endTime, cur.endTime);
			} else {
				mergedIntervals.add(prev);
				prev = cur;
			}
		}
		mergedIntervals.add(prev);
		return mergedIntervals;
	}

	private static List<Interval> readIntervals(Scanner scn, int len) {
		List<Interval> intervals = new ArrayList<>();
		while (len-- > 0) {
			var interval = new Interval();
			interval.startTime = scn.nextInt();
			interval.endTime = scn.nextInt();
			intervals.add(interval);
		}
		return intervals;
	}
}

class Interval {
	int startTime;
	int endTime;

	@Override
	public String toString() {
		return startTime + " " + endTime + " ";
	}
}
