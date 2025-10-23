package practice;

import java.util.HashMap;
import java.util.stream.Collectors;

/* 23 Oct 2025 07:04 */

/** [1366. Rank Teams by Votes](https://leetcode.com/problems/rank-teams-by-votes) */
public class RankTeamsByVotes {
	public String rankTeams(String[] votes) {
		if (votes.length == 0) {
			return "";
		}
		if (votes.length == 1) {
			return votes[0];
		}
		final var teamToRanks = new HashMap<Character, int[]>();
		for (var vote : votes) {
			for (var i = 0; i < vote.length(); i++) {
				var ranks = teamToRanks.computeIfAbsent(vote.charAt(i), _ -> new int[votes[0].length()]);
				ranks[i]++;
			}
		}
		return teamToRanks.keySet().stream()
				.sorted(
						(team1, team2) -> {
							for (var i = 0; i < votes[0].length(); i++) {
								if (teamToRanks.get(team1)[i] != teamToRanks.get(team2)[i]) {
									return teamToRanks.get(team2)[i] - teamToRanks.get(team1)[i];
								}
							}
							return team1 - team2;
						})
				.map(Object::toString)
				.collect(Collectors.joining());
	}

	static void main() {
		var rankTeamsByVotes = new RankTeamsByVotes();
		System.out.println(
				rankTeamsByVotes.rankTeams(new String[] {"ABC", "ACB", "ABC", "ACB", "ACB"})); // "ACB"
		System.out.println(rankTeamsByVotes.rankTeams(new String[] {"WXYZ", "XYZW"})); // "XWYZ"
		System.out.println(
				rankTeamsByVotes.rankTeams(
						new String[] {"ZMNAGUEDSJYLBOPHRQICWF"})); // "ZMNAGUEDSJYLBOPHRQICWF"
	}
}
