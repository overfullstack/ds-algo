package practice.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RestoreIpAddresses {
	public List<String> restoreIpAddresses(String s) {
		return solve(0, "", 0, s);
	}

	private static List<String> solve(int idx, String acc, int intCount, String s) {
		if (intCount > 4) {
			return Collections.emptyList();
		}
		if (intCount == 4 && idx == s.length()) {
			return List.of(acc);
		}
		final var result = new ArrayList<String>();
		for (var i = 1; i <= 3 && idx + i <= s.length(); i++) {
			var sub = s.substring(idx, idx + i);
			if (isValid(sub)) {
				result.addAll(solve(idx + i, acc + sub + ((intCount == 3) ? "" : "."), intCount + 1, s));
			}
		}
		return result;
	}

	private static boolean isValid(String sub) {
		if (sub.length() > 1 && sub.startsWith("0")) {
			return false;
		}
		var value = Integer.parseInt(sub);
		return value <= 255;
	}

	static void main() {
		var restoreIpAddresses = new RestoreIpAddresses();
		restoreIpAddresses
				.restoreIpAddresses("25525511135")
				.forEach(IO::println); // ["255.255.11.135","255.255.111.35"]
		restoreIpAddresses.restoreIpAddresses("0000").forEach(IO::println); // ["0.0.0.0"]
		restoreIpAddresses
				.restoreIpAddresses("101023")
				.forEach(IO::println); // ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
	}
}
