package practice;

/* 16 Oct 2025 18:35 */

import static java.lang.IO.println;

import java.util.HashMap;
import java.util.Map;

/** [3620 · Logger Rate Limiter](https://www.lintcode.com/problem/3620/) */
public class LoggerRateLimiter {
	private final Map<String, Integer> messageToTimestamp = new HashMap<>();

	public boolean couldPrintMessage(int timestamp, String message) {
		final var prevTimeStamp = messageToTimestamp.get(message);
		if (prevTimeStamp == null || timestamp - prevTimeStamp >= 10) {
			messageToTimestamp.put(message, timestamp);
			return true;
		}
		return false;
	}

	static void main() {
		final var logger = new LoggerRateLimiter();
		System.out.println(logger.couldPrintMessage(1, "foo")); // true
		System.out.println(logger.couldPrintMessage(2, "bar")); // true
		System.out.println(logger.couldPrintMessage(3, "foo")); // false
		println(logger.couldPrintMessage(8, "bar")); // false
		println(logger.couldPrintMessage(10, "foo")); // false
		println(logger.couldPrintMessage(11, "foo")); // true
	}
}
