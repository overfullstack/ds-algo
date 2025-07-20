package educative;

import java.util.*;

class Interval {
	int start;
	int end;
	boolean closed;

	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
		this.closed = true; // by default, the interval is closed
	}

	// set the flag for closed/open
	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	class Solution {

		public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
			PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

			// Iterate for all employees' schedules
			// and add start of each schedule's first interval along with
			// its index value and a value 0.
			for (int i = 0; i < schedule.size(); i++) {
				List<Interval> employeeSchedule = schedule.get(i);
				Interval interval = employeeSchedule.get(0);
				heap.offer(new int[] {interval.start, i, 0});
			}

			// Take an empty list to store results.
			List<Interval> result = new ArrayList<>();

			// Set 'previous' to the start time of the first interval in heap.
			int previous = schedule.get(heap.peek()[1]).get(heap.peek()[2]).start;

			// Iterate until the heap is empty
			while (!heap.isEmpty()) {
				// Poll an element from the heap and get values of i and j
				int[] tuple = heap.poll();
				int i = tuple[1];
				int j = tuple[2];

				// Select an interval
				Interval interval = schedule.get(i).get(j);

				// If the selected interval's start value is greater than the previous value,
				// it means that this interval is free. So, add this interval
				// (previous, interval's end value) into the result.
				if (interval.start > previous) {
					result.add(new Interval(previous, interval.start));
				}

				// Update the previous as the maximum of previous and interval's end value.
				previous = Math.max(previous, interval.end);

				// If there is another interval in the current employee's schedule,
				// push that into the heap.
				if (j + 1 < schedule.get(i).size()) {
					Interval nextInterval = schedule.get(i).get(j + 1);
					heap.offer(new int[] {nextInterval.start, i, j + 1});
				}
			}

			// When the heap is empty, return the result.
			return result;
		}

		// Function for displaying interval list
		public static String display(List<Interval> l1) {
			if (l1.size() == 0) {
				return "[]";
			}

			String resultStr = "[";

			for (int i = 0; i < l1.size() - 1; i++) {
				resultStr += "[" + l1.get(i).start + ", ";
				resultStr += l1.get(i).end + "], ";
			}

			resultStr += "[" + l1.get(l1.size() - 1).start + ", ";
			resultStr += l1.get(l1.size() - 1).end + "]";
			resultStr += "]";

			return resultStr;
		}

		// Driver code
		public static void main(String args[]) {
			List<List<List<Interval>>> inputs1 =
					Arrays.asList(
							Arrays.asList(
									Arrays.asList(new Interval(1, 2), new Interval(5, 6)),
									Arrays.asList(new Interval(1, 3)),
									Arrays.asList(new Interval(4, 10))),
							Arrays.asList(
									Arrays.asList(new Interval(1, 3), new Interval(6, 7)),
									Arrays.asList(new Interval(2, 4)),
									Arrays.asList(new Interval(2, 5), new Interval(9, 12))),
							Arrays.asList(
									Arrays.asList(new Interval(2, 3), new Interval(7, 9)),
									Arrays.asList(new Interval(1, 4), new Interval(6, 7))),
							Arrays.asList(
									Arrays.asList(new Interval(3, 5), new Interval(8, 10)),
									Arrays.asList(new Interval(4, 6), new Interval(9, 12)),
									Arrays.asList(new Interval(5, 6), new Interval(8, 10))),
							Arrays.asList(
									Arrays.asList(new Interval(1, 3), new Interval(6, 9), new Interval(10, 11)),
									Arrays.asList(new Interval(3, 4), new Interval(7, 12)),
									Arrays.asList(new Interval(1, 3), new Interval(7, 10)),
									Arrays.asList(new Interval(1, 4)),
									Arrays.asList(new Interval(7, 10), new Interval(11, 12))),
							Arrays.asList(
									Arrays.asList(
											new Interval(1, 2),
											new Interval(3, 4),
											new Interval(5, 6),
											new Interval(7, 8)),
									Arrays.asList(new Interval(2, 3), new Interval(4, 5), new Interval(6, 8))),
							Arrays.asList(
									Arrays.asList(
											new Interval(1, 2),
											new Interval(3, 4),
											new Interval(5, 6),
											new Interval(7, 8),
											new Interval(9, 10),
											new Interval(11, 12)),
									Arrays.asList(
											new Interval(1, 2),
											new Interval(3, 4),
											new Interval(5, 6),
											new Interval(7, 8),
											new Interval(9, 10),
											new Interval(11, 12)),
									Arrays.asList(
											new Interval(1, 2),
											new Interval(3, 4),
											new Interval(5, 6),
											new Interval(7, 8),
											new Interval(9, 10),
											new Interval(11, 12)),
									Arrays.asList(
											new Interval(1, 2),
											new Interval(3, 4),
											new Interval(5, 6),
											new Interval(7, 8),
											new Interval(9, 10),
											new Interval(11, 12))));

			int i = 1;
			List<List<List<Interval>>> inputs = new ArrayList<>();

			for (int j = 0; j < inputs1.size(); j++) {
				inputs.add(new ArrayList<List<Interval>>());

				for (int k = 0; k < inputs1.get(j).size(); k++) {
					inputs.get(j).add(new ArrayList<Interval>());

					for (int g = 0; g < inputs1.get(j).get(k).size(); g++) {
						inputs.get(j).get(k).add(inputs1.get(j).get(k).get(g));
					}
				}
			}

			for (int j = 0; j < inputs.size(); j++) {
				System.out.println(i + ".\tEmployee Schedules:\n");

				for (int s = 0; s < inputs.get(j).size(); s++) {
					System.out.println("\t\t" + display(inputs.get(j).get(s)));
				}

				System.out.println("\n\tEmployees' free time " + display(employeeFreeTime(inputs.get(j))));
				System.out.println(new String(new char[100]).replace('\0', '-'));
				i += 1;
			}
		}
	}
}
