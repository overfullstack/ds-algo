package practice;

import java.util.Comparator;
import java.util.HashMap;

/* 23 Oct 2025 12:06 */

/**
 * [3016. Minimum Number of Pushes to Type Word
 * II](https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-ii/)
 */
public class MinimumNumberOfPushesToTypeWordII {
	public int minimumPushes(String word) {
		var freq = new HashMap<Character, Integer>();
		for (var ch : word.toCharArray()) {
			freq.merge(ch, 1, Integer::sum);
		}
		if (freq.size() <= 8) {
			return word.length();
		}
		var sortedFreq = freq.values().stream().sorted(Comparator.reverseOrder()).toList();
		var result = 0;
		for (var i = 0; i < sortedFreq.size(); i++) {
			result += (i / 8 + 1) * sortedFreq.get(i);
		}
		return result;
	}

	static void main() {
		var test = new MinimumNumberOfPushesToTypeWordII();
		System.out.println(test.minimumPushes("abcde")); // 5
		System.out.println(test.minimumPushes("xycdefghij")); // 12
		System.out.println(test.minimumPushes("xyzxyzxyzxyz")); // 12
		System.out.println(test.minimumPushes("aabbccddeeffgghhiiiiii")); // 24
	}
}
