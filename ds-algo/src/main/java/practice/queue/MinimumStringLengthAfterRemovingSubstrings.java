package practice.queue;

import java.util.ArrayDeque;

/* 23 Oct 2025 17:09 */

/**
 * [2696. Minimum String Length After Removing
 * Substrings](https://leetcode.com/problems/minimum-string-length-after-removing-substrings/)
 */
public class MinimumStringLengthAfterRemovingSubstrings {
	public int minLength(String s) {
		var stk = new ArrayDeque<Character>();
		for (var ch : s.toCharArray()) {
			if (stk.isEmpty()) {
				stk.push(ch);
			} else if (ch == 'B' && stk.peek() == 'A') {
				stk.pop();
			} else if (ch == 'D' && stk.peek() == 'C') {
				stk.pop();
			} else {
				stk.push(ch);
			}
		}
		return stk.size();
	}

	static void main() {
		var minimumStringLengthAfterRemovingSubstrings =
				new MinimumStringLengthAfterRemovingSubstrings();
		System.out.println(minimumStringLengthAfterRemovingSubstrings.minLength("ABFCACDB")); // 2
		System.out.println(minimumStringLengthAfterRemovingSubstrings.minLength("ACBBD")); // 5
	}
}
