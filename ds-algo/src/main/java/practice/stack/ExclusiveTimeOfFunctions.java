package practice.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

/**
 * [636. Exclusive Time of Functions](https://leetcode.com/problems/exclusive-time-of-functions/)
 */
public class ExclusiveTimeOfFunctions {
	public int[] exclusiveTime(int n, List<String> logs) {
		var stk = new ArrayDeque<Log>();
		var result = new int[n];
		for (var logStr : logs) {
			var log = new Log(logStr);
			if (log.isStart) {
				stk.push(log);
			} else {
				var startLog = stk.pop();
				var fnDuration = log.timeStamp - startLog.timeStamp + 1;
				result[log.fnId] += fnDuration;
				if (!stk.isEmpty()) {
					result[stk.peek().fnId] -= fnDuration;
				}
			}
		}
		return result;
	}

	record Log(int fnId, boolean isStart, int timeStamp) {
		Log(String log) {
			var logParts = log.split(":");
			this(
					Integer.parseInt(logParts[0]),
					logParts[1].equals("start"),
					Integer.parseInt(logParts[2]));
		}
	}

	static void main() {
		var exclusiveTimeOfFunctions = new ExclusiveTimeOfFunctions();
		System.out.println(
				Arrays.toString(
						exclusiveTimeOfFunctions.exclusiveTime(
								1,
								List.of(
										"0:start:0",
										"0:start:1",
										"0:start:2",
										"0:end:3",
										"0:end:4",
										"0:end:5")))); // [6]
		System.out.println(
				Arrays.toString(
						exclusiveTimeOfFunctions.exclusiveTime(
								1,
								List.of(
										"0:start:0",
										"0:start:2",
										"0:end:5",
										"0:start:6",
										"0:end:6",
										"0:end:7")))); // [8]
		System.out.println(
				Arrays.toString(
						exclusiveTimeOfFunctions.exclusiveTime(
								2, List.of("0:start:0", "1:start:2", "1:end:5", "0:end:6")))); // [3, 4]
		System.out.println(
				Arrays.toString(
						exclusiveTimeOfFunctions.exclusiveTime(
								2,
								List.of(
										"0:start:0",
										"0:start:2",
										"0:end:5",
										"1:start:6",
										"1:end:6",
										"0:end:7")))); // [7, 1]
	}
}
