package practice.backtracking;

import static java.lang.IO.println;
import static java.lang.String.join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/** [401. Binary Watch](https://leetcode.com/problems/binary-watch/) */
public class BinaryWatch {
	public List<String> readBinaryWatch(int turnedOn) {
		var result = new ArrayList<String>();
		for (var hours = 0; hours <= 11; hours++) {
			for (var mins = 0; mins <= 59; mins++) {
				if (Integer.bitCount(hours * 64 + mins) == turnedOn) {
					result.add(String.format("%d:%02d", hours, mins));
				}
			}
		}
		return result;
	}

	public List<String> readBinaryWatch2(int turnedOn) {
		return solve(0, "", turnedOn).stream()
				.map(
						time -> {
							var hours = Integer.parseInt(time.substring(0, 4), 2);
							var minutes = Integer.parseInt(time.substring(4), 2);
							return hours + ":" + (minutes <= 9 ? "0" : "") + minutes;
						})
				.toList();
	}

	private static List<String> solve(int idx, String acc, int remainingOnes) {
		if (remainingOnes < 0) {
			return Collections.emptyList();
		}
		if (acc.length() == 10) {
			return (remainingOnes == 0 && isValid(acc)) ? List.of(acc) : Collections.emptyList();
		}
		final var result1 = solve(idx + 1, "1" + acc, remainingOnes - 1);
		final var result0 = solve(idx + 1, "0" + acc, remainingOnes);
		return Stream.concat(result1.stream(), result0.stream()).toList();
	}

	private static boolean isValid(String time) {
		var hours = Integer.parseInt(time.substring(0, 4), 2);
		if (hours > 11) {
			return false;
		}
		var minutes = Integer.parseInt(time.substring(4), 2);
		return minutes <= 59;
	}

	static void main() {
		var binaryWatch = new BinaryWatch();
		println(
				join(
						", ",
						binaryWatch.readBinaryWatch2(
								1))); // ["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
	}
}
