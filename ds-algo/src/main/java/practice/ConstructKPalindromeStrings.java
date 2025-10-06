package practice;

/* 06 Oct 2025 15:25 */

import java.util.Arrays;

/**
 * [1400. Construct K Palindrome
 * Strings](https://leetcode.com/problems/construct-k-palindrome-strings/)
 */
public class ConstructKPalindromeStrings {
	public boolean canConstruct(String s, int k) {
		if (s.length() < k) {
			return false;
		}
		if (s.length() == k) {
			return true; // ! Split each char to make a Palindrome
		}
		var freq = new int[26];
		for (var ch : s.toCharArray()) {
			freq[ch - 'a']++;
		}
		var oddCount = Arrays.stream(freq).filter(x -> x % 2 != 0).count();
		// ! oddCount > k, we need to split to more than k palindromes to accommodate all odd chars
		return oddCount <= k;
	}
}
