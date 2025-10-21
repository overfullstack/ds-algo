package practice.hashmap;

/* 21 Oct 2025 15:57 */

import static java.lang.IO.println;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * [Top Five Average](https://www.naukri.com/code360/problems/top-five-average_1214648) ! This is
 * not running for large input below, not sure if it's platform issue
 */
public class TopFiveAverage {
	public static ArrayList<ArrayList<Integer>> findTopFiveAverages(
			ArrayList<ArrayList<Integer>> arr) {
		var idToScoreQueue = new HashMap<Integer, Queue<Integer>>();
		for (var rec : arr) {
			var id = rec.get(0);
			final var pq = idToScoreQueue.computeIfAbsent(id, _ -> new PriorityQueue<>());
			pq.add(rec.get(1));
			if (pq.size() > 5) {
				pq.poll();
			}
		}
		return idToScoreQueue.entrySet().stream()
				.map(
						entry -> {
							var avg = (int) entry.getValue().stream().mapToInt(i -> i).average().orElse(0);
							return new ArrayList<>(List.of(entry.getKey(), avg));
						})
				.collect(Collectors.toCollection(ArrayList::new));
	}

	static void main() {
		var input = new ArrayList<ArrayList<Integer>>();
		input.add(new ArrayList<>(List.of(7, 0)));
		input.add(new ArrayList<>(List.of(7, 0)));
		input.add(new ArrayList<>(List.of(7, 0)));
		input.add(new ArrayList<>(List.of(7, 0)));
		input.add(new ArrayList<>(List.of(7, 0)));
		println(findTopFiveAverages(input)); // [[7, 0]]
		input.clear();
		input.add(new ArrayList<>(List.of(1, 10)));
		input.add(new ArrayList<>(List.of(1, 11)));
		input.add(new ArrayList<>(List.of(2, 13)));
		input.add(new ArrayList<>(List.of(2, 15)));
		input.add(new ArrayList<>(List.of(1, 9)));
		input.add(new ArrayList<>(List.of(2, 1)));
		input.add(new ArrayList<>(List.of(1, 21)));
		input.add(new ArrayList<>(List.of(2, 21)));
		input.add(new ArrayList<>(List.of(2, 1)));
		input.add(new ArrayList<>(List.of(1, 19)));
		input.add(new ArrayList<>(List.of(1, 9)));
		input.add(new ArrayList<>(List.of(2, 11)));
		input.add(new ArrayList<>(List.of(1, 7)));
		println(findTopFiveAverages(input)); // [[1, 14], [2, 12]]
		input.clear();
		input.add(new ArrayList<>(List.of(1, 1)));
		input.add(new ArrayList<>(List.of(1, 2)));
		input.add(new ArrayList<>(List.of(1, 3)));
		input.add(new ArrayList<>(List.of(1, 4)));
		input.add(new ArrayList<>(List.of(1, 5)));
		println(findTopFiveAverages(input)); // [[1, 3]]
		input.clear();
		input.add(new ArrayList<>(List.of(452802192, 81425)));
		input.add(new ArrayList<>(List.of(360621461, 53846)));
		input.add(new ArrayList<>(List.of(662975874, 74936)));
		input.add(new ArrayList<>(List.of(843301558, 33523)));
		input.add(new ArrayList<>(List.of(152133263, 700)));
		input.add(new ArrayList<>(List.of(807607159, 7503)));
		input.add(new ArrayList<>(List.of(151070508, 57670)));
		input.add(new ArrayList<>(List.of(662975874, 25758)));
		input.add(new ArrayList<>(List.of(795933470, 80501)));
		input.add(new ArrayList<>(List.of(152133263, 67565)));
		input.add(new ArrayList<>(List.of(765147573, 6669)));
		input.add(new ArrayList<>(List.of(763661443, 55231)));
		input.add(new ArrayList<>(List.of(662975874, 30324)));
		input.add(new ArrayList<>(List.of(38696670, 68488)));
		input.add(new ArrayList<>(List.of(958998907, 12063)));
		input.add(new ArrayList<>(List.of(304239040, 93277)));
		input.add(new ArrayList<>(List.of(730805012, 7246)));
		input.add(new ArrayList<>(List.of(328864085, 82888)));
		input.add(new ArrayList<>(List.of(360621461, 91819)));
		input.add(new ArrayList<>(List.of(151070508, 74152)));
		input.add(new ArrayList<>(List.of(304239040, 5359)));
		input.add(new ArrayList<>(List.of(843301558, 34834)));
		input.add(new ArrayList<>(List.of(843301558, 10752)));
		input.add(new ArrayList<>(List.of(662975874, 42280)));
		input.add(new ArrayList<>(List.of(304239040, 12532)));
		input.add(new ArrayList<>(List.of(360621461, 53522)));
		input.add(new ArrayList<>(List.of(795933470, 67762)));
		input.add(new ArrayList<>(List.of(515709125, 69807)));
		input.add(new ArrayList<>(List.of(843301558, 92520)));
		input.add(new ArrayList<>(List.of(38696670, 97724)));
		input.add(new ArrayList<>(List.of(151070508, 84579)));
		input.add(new ArrayList<>(List.of(328864085, 52327)));
		input.add(new ArrayList<>(List.of(152133263, 36520)));
		input.add(new ArrayList<>(List.of(958998907, 80715)));
		input.add(new ArrayList<>(List.of(795933470, 79292)));
		input.add(new ArrayList<>(List.of(900218094, 19736)));
		input.add(new ArrayList<>(List.of(304239040, 90751)));
		input.add(new ArrayList<>(List.of(360621461, 12367)));
		input.add(new ArrayList<>(List.of(21441126, 76618)));
		input.add(new ArrayList<>(List.of(763661443, 77841)));
		input.add(new ArrayList<>(List.of(958998907, 34180)));
		input.add(new ArrayList<>(List.of(151070508, 93572)));
		input.add(new ArrayList<>(List.of(452802192, 41141)));
		input.add(new ArrayList<>(List.of(795933470, 78412)));
		input.add(new ArrayList<>(List.of(385497109, 57098)));
		input.add(new ArrayList<>(List.of(795933470, 59067)));
		input.add(new ArrayList<>(List.of(515709125, 95625)));
		input.add(new ArrayList<>(List.of(385497109, 38184)));
		input.add(new ArrayList<>(List.of(151070508, 10148)));
		input.add(new ArrayList<>(List.of(795933470, 89767)));
		input.add(new ArrayList<>(List.of(795933470, 21107)));
		input.add(new ArrayList<>(List.of(763661443, 51406)));
		input.add(new ArrayList<>(List.of(304239040, 12199)));
		input.add(new ArrayList<>(List.of(452802192, 91430)));
		input.add(new ArrayList<>(List.of(38696670, 97994)));
		input.add(new ArrayList<>(List.of(515709125, 38334)));
		input.add(new ArrayList<>(List.of(304239040, 96808)));
		input.add(new ArrayList<>(List.of(900218094, 64157)));
		input.add(new ArrayList<>(List.of(515709125, 45298)));
		input.add(new ArrayList<>(List.of(807607159, 82548)));
		input.add(new ArrayList<>(List.of(765147573, 9009)));
		input.add(new ArrayList<>(List.of(360621461, 38424)));
		input.add(new ArrayList<>(List.of(21441126, 19329)));
		input.add(new ArrayList<>(List.of(304239040, 2490)));
		input.add(new ArrayList<>(List.of(765147573, 24790)));
		input.add(new ArrayList<>(List.of(900218094, 24242)));
		input.add(new ArrayList<>(List.of(152133263, 18458)));
		input.add(new ArrayList<>(List.of(900218094, 33845)));
		input.add(new ArrayList<>(List.of(21441126, 26592)));
		input.add(new ArrayList<>(List.of(958998907, 92928)));
		input.add(new ArrayList<>(List.of(452802192, 69842)));
		input.add(new ArrayList<>(List.of(765147573, 28947)));
		input.add(new ArrayList<>(List.of(385497109, 4157)));
		input.add(new ArrayList<>(List.of(730805012, 7869)));
		input.add(new ArrayList<>(List.of(807607159, 31862)));
		input.add(new ArrayList<>(List.of(807607159, 69711)));
		input.add(new ArrayList<>(List.of(763661443, 75626)));
		input.add(new ArrayList<>(List.of(385497109, 32943)));
		input.add(new ArrayList<>(List.of(505422623, 65692)));
		input.add(new ArrayList<>(List.of(730805012, 96176)));
		input.add(new ArrayList<>(List.of(360621461, 60421)));
		input.add(new ArrayList<>(List.of(817322055, 40121)));
		input.add(new ArrayList<>(List.of(505422623, 16056)));
		input.add(new ArrayList<>(List.of(328864085, 51857)));
		input.add(new ArrayList<>(List.of(505422623, 97033)));
		input.add(new ArrayList<>(List.of(795933470, 50391)));
		input.add(new ArrayList<>(List.of(900218094, 12671)));
		input.add(new ArrayList<>(List.of(21441126, 92753)));
		input.add(new ArrayList<>(List.of(505422623, 43078)));
		input.add(new ArrayList<>(List.of(38696670, 23088)));
		input.add(new ArrayList<>(List.of(515709125, 69971)));
		input.add(new ArrayList<>(List.of(304239040, 60343)));
		input.add(new ArrayList<>(List.of(807607159, 83926)));
		input.add(new ArrayList<>(List.of(843301558, 79641)));
		input.add(new ArrayList<>(List.of(505422623, 66250)));
		input.add(new ArrayList<>(List.of(328864085, 35580)));
		input.add(new ArrayList<>(List.of(817322055, 12072)));
		input.add(new ArrayList<>(List.of(730805012, 389)));
		input.add(new ArrayList<>(List.of(152133263, 24509)));
		input.add(new ArrayList<>(List.of(730805012, 47597)));
		input.add(new ArrayList<>(List.of(385497109, 25275)));
		input.add(new ArrayList<>(List.of(38696670, 45542)));
		input.add(new ArrayList<>(List.of(515709125, 96180)));
		input.add(new ArrayList<>(List.of(452802192, 70279)));
		input.add(new ArrayList<>(List.of(328864085, 89958)));
		input.add(new ArrayList<>(List.of(765147573, 11745)));
		input.add(new ArrayList<>(List.of(662975874, 5950)));
		input.add(new ArrayList<>(List.of(763661443, 12212)));
		input.add(new ArrayList<>(List.of(817322055, 80286)));
		input.add(new ArrayList<>(List.of(21441126, 78225)));
		input.add(new ArrayList<>(List.of(304239040, 74482)));
		input.add(new ArrayList<>(List.of(505422623, 73874)));
		input.add(new ArrayList<>(List.of(662975874, 10091)));
		input.add(new ArrayList<>(List.of(360621461, 10309)));
		input.add(new ArrayList<>(List.of(730805012, 25587)));
		input.add(new ArrayList<>(List.of(817322055, 41952)));
		input.add(new ArrayList<>(List.of(765147573, 68681)));
		input.add(new ArrayList<>(List.of(152133263, 3353)));
		input.add(new ArrayList<>(List.of(452802192, 50309)));
		input.add(new ArrayList<>(List.of(385497109, 6789)));
		input.add(new ArrayList<>(List.of(807607159, 33047)));
		input.add(new ArrayList<>(List.of(328864085, 45515)));
		input.add(new ArrayList<>(List.of(21441126, 10079)));
		input.add(new ArrayList<>(List.of(958998907, 45908)));
		input.add(new ArrayList<>(List.of(843301558, 13882)));
		input.add(new ArrayList<>(List.of(304239040, 20101)));
		input.add(new ArrayList<>(List.of(152133263, 73879)));
		input.add(new ArrayList<>(List.of(817322055, 19705)));
		input.add(new ArrayList<>(List.of(958998907, 4956)));
		input.add(new ArrayList<>(List.of(452802192, 13548)));
		input.add(new ArrayList<>(List.of(900218094, 52783)));
		// [21441126 58703 38696670 66567 151070508 64024 152133263 44186 304239040 83132 328864085
		// 64509 360621461 59606 385497109 32057 452802192 72657 505422623 69185 515709125 75376
		// 662975874 36677 730805012 36895 763661443 54463 765147573 28634 795933470 79146 807607159
		// 60218 817322055 38827 843301558 50880 900218094 38952 958998907 53158]
		println(findTopFiveAverages(input));
	}
}
