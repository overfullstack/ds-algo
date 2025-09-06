package practice.dp;

import java.util.HashSet;

/* 05 Sep 2025 18:16 */

/**
 * [1525. Number of Good Ways to Split a
 * String](https://leetcode.com/problems/number-of-good-ways-to-split-a-string/)
 */
public class NumberOfGoodWaysToSplitAString {
	public int numSplits(String s) {
		var len = s.length();
		var prefix = new int[len];
		var prefixSet = new HashSet<Character>();
		var suffix = new int[len];
		var suffixSet = new HashSet<Character>();

		for (var i = 0; i < len; i++) {
			prefixSet.add(s.charAt(i));
			prefix[i] = prefixSet.size();
			suffixSet.add(s.charAt(len - 1 - i));
			suffix[len - 1 - i] = suffixSet.size();
		}

		var count = 0;
		for (var partition = 1; partition < len; partition++) {
			if (prefix[partition - 1] == suffix[partition]) {
				count++;
			}
		}
		return count;
	}

	static void main() {
		var numberOfGoodWaysToSplitAString = new NumberOfGoodWaysToSplitAString();
		System.out.println(numberOfGoodWaysToSplitAString.numSplits("abcd")); // 1
		System.out.println(numberOfGoodWaysToSplitAString.numSplits("aacaba")); // 2
	}
}
