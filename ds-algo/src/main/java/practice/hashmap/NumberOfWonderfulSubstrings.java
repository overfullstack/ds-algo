package practice.hashmap;

/* 21 Oct 2025 20:25 */

/**
 * [1915. Number of Wonderful
 * Substrings](https://leetcode.com/problems/number-of-wonderful-substrings/)
 */
public class NumberOfWonderfulSubstrings {
	public long wonderfulSubstrings(String word) {
		var count = new int[(int) Math.pow(2, 10)]; // ! For `a..j` 10 letters
		count[0] = 1; // ! For first even state count
		var mask = 0;
		var result = 0L;
		for (var ch : word.toCharArray()) { // ! All characters even
			mask ^= (1 << (ch - 'a')); // ! Flip char state
			result += count[mask]; // ! Same state again
      // ! Flick each char to check if such state exists before
			for (var i = 0; i < 10; i++) {
				var flipMaskForChar = mask ^ (1 << i);
				result += count[flipMaskForChar];
			}
			count[mask]++;
		}
		return result;
	}

	static void main() {
		var numberOfWonderfulSubstrings = new NumberOfWonderfulSubstrings();
		System.out.println(numberOfWonderfulSubstrings.wonderfulSubstrings("aba")); // 4
		System.out.println(numberOfWonderfulSubstrings.wonderfulSubstrings("aabb")); // 9
		System.out.println(numberOfWonderfulSubstrings.wonderfulSubstrings("he")); // 2
	}
}
