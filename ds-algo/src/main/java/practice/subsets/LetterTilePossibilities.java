package practice.subsets;

/* 25 Oct 2025 14:35 */

public class LetterTilePossibilities {
	public int numTilePossibilities(String tiles) {
		var freq = new int[26];
		for (var ch : tiles.toCharArray()) {
			freq[ch - 'A']++;
		}
		return permutationsAndCombinationsCount(freq);
	}

	private static int permutationsAndCombinationsCount(int[] freq) {
		var count = 0;
		for (var i = 0; i < 26; i++) {
			if (freq[i] > 0) {
				freq[i]--;
				count++;
				count += permutationsAndCombinationsCount(freq);
				freq[i]++;
			}
		}
		return count;
	}

	static void main() {
		var l = new LetterTilePossibilities();
		System.out.println(l.numTilePossibilities("AAB")); // 8
		System.out.println(l.numTilePossibilities("AAABBC")); // 188
	}
}
