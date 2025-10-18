package practice.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** [93. Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses) */
public class RestoreIpAddresses {
	public List<String> restoreIpAddresses(String s) {
		return solve(0, "", 0, s);
	}

	private static List<String> solve(int idx, String acc, int segCount, String s) {
		if (segCount > 4) {
			return Collections.emptyList();
		}
		if (idx == s.length()) {
			return segCount == 4 ? List.of(acc) : Collections.emptyList();
		}
		final var result = new ArrayList<String>();
		for (var i = 1; i <= 3 && idx + i <= s.length(); i++) {
			var seg = s.substring(idx, idx + i);
			if (isValid(seg)) {
				result.addAll(solve(idx + i, acc + seg + ((segCount == 3) ? "" : "."), segCount + 1, s));
			}
		}
		return result;
	}

	private static boolean isValid(String segment) {
		return !(segment.length() > 1 && segment.startsWith("0")) && Integer.parseInt(segment) <= 255;
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
