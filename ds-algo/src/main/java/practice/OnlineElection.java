package practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* 31 Aug 2025 18:06 */

/** [911. Online Election](https://leetcode.com/problems/online-election/) */
public class OnlineElection {
	private final Map<Integer, Integer> timeIdxToLeader;
	private final int[] times;

	OnlineElection(int[] persons, int[] times) {
		this.times = times;
		var personToVoteCount = new HashMap<Integer, Integer>();
		timeIdxToLeader = new HashMap<>();
		var leader = -1;
		for (var i = 0; i < persons.length; i++) {
			personToVoteCount.merge(persons[i], 1, (old, _) -> old + 1);
			if (leader == -1 || personToVoteCount.get(persons[i]) >= personToVoteCount.get(leader)) {
				leader = persons[i];
			}
			timeIdxToLeader.put(i, leader);
		}
	}

	public int q(int t) {
		var i = Arrays.binarySearch(times, t);
		i = (i < 0) ? (-i - 2) : i;
		return timeIdxToLeader.get(i);
	}
}
