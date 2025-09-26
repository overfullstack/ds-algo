package tbd;

import java.util.HashMap;
import java.util.Map;

public class NumPairsDivisibleBy60 {

	public int numPairsDivisibleBy60(int[] time) {
		// Map to store frequency of remainders when divided by 60
		// Key: remainder (0-59), Value: count of songs with that remainder
		Map<Integer, Integer> remainderCount = new HashMap<>();
		int pairs = 0;

		for (int duration : time) {
			// Get the remainder when this song's duration is divided by 60
			int remainder = duration % 60;

			// Calculate what remainder we need to pair with to get a sum divisible by 60
			// If remainder is 0, we need another 0. Otherwise, we need (60 - remainder)
			int complement = (remainder == 0) ? 0 : 60 - remainder;

			// If we've seen songs with the complement remainder before,
			// each of them can form a valid pair with the current song
			if (remainderCount.containsKey(complement)) {
				pairs += remainderCount.get(complement);
			}

			// Add current song's remainder to our map for future pairs
			// Using merge to handle both insertion and increment in one operation
			remainderCount.merge(remainder, 1, Integer::sum);
		}

		return pairs;
	}
}
