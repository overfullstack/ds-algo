package practice.hashmap;

/* 21 Oct 2025 17:22 */

/** [791. Custom Sort String](https://leetcode.com/problems/custom-sort-string/) */
public class CustomSortString {
	public String customSortString(String order, String s) {
		var bucket = new int[26];
		for (var ch : s.toCharArray()) {
			bucket[ch - 'a']++;
		}
		var result = new StringBuilder();
		for (var ch : order.toCharArray()) {
			while (bucket[ch - 'a'] > 0) {
				result.append(ch);
				bucket[ch - 'a']--;
			}
		}
		for (var ch = 'a'; ch <= 'z'; ch++) {
			while (bucket[ch - 'a'] > 0) {
				result.append(ch);
				bucket[ch - 'a']--;
			}
		}
		return result.toString();
	}

	static void main() {
		var c = new CustomSortString();
		System.out.println(c.customSortString("cba", "abcd")); // "cbad"
		System.out.println(c.customSortString("bcafg", "abcd")); // "bcad"
		System.out.println(c.customSortString("kqep", "pekeq")); // "kqeep"
	}
}
