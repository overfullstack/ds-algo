package gfg.practice.greedy.ActivitySelection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ActivitySelection {
	static void main() {
		var scn = new Scanner(System.in);
		var testCases = scn.nextInt();
		while (testCases-- > 0) {
			var len = scn.nextInt();
			var activities = readActivities(scn, len);
			printSelectedActivities(activities);
			System.out.println();
		}
	}

	private static void printSelectedActivities(List<Activity> activities) {
		Collections.sort(activities);
		var prevActivity = activities.get(0);
		System.out.print(prevActivity.index + " ");
		for (var i = 1; i < activities.size(); i++) {
			var curActivity = activities.get(i);
			if (curActivity.startTime >= prevActivity.finishTime) {
				System.out.print(curActivity.index + " ");
				prevActivity = curActivity;
			}
		}
	}

	private static List<Activity> readActivities(Scanner scn, int len) {
		List<Activity> activities = new ArrayList<>();
		for (var i = 0; i < len; i++) {
			var activity = new Activity();
			activity.index = i + 1;
			activity.startTime = scn.nextInt();
			activities.add(activity);
		}
		for (var i = 0; i < len; i++) {
			activities.get(i).finishTime = scn.nextInt();
		}
		return activities;
	}
}

class Activity implements Comparable<Activity> {
	int index;
	int startTime;
	int finishTime;

	@Override
	public int compareTo(Activity o) {
		return Integer.compare(this.finishTime, o.finishTime);
	}
}
