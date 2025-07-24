package hackerrank.Candies;

import java.util.Arrays;
import java.util.Scanner;

/** Created by gakshintala on 2/25/16. */
public class Candies {
	static void main() {
		var scn = new Scanner(System.in);
		var childrenCount = scn.nextInt();
		var rating = new long[childrenCount];
		for (var i = 0; i < childrenCount; i++) {
			rating[i] = scn.nextInt();
		}

		var candies = new long[childrenCount];

		candies[0] = 1;

		for (var i = 1; i < childrenCount; i++) {
			if (rating[i - 1] < rating[i]) candies[i] = candies[i - 1] + 1;
			else candies[i] = 1;
		}

		for (var i = childrenCount - 1; i > 0; i--) {
			if (rating[i] < rating[i - 1])
				candies[i - 1] =
						Math.max(candies[i - 1], candies[i] + 1); // This is required at peak points
		}

		System.out.println("Distribution: " + Arrays.toString(candies));
		var total = 0;
		for (var i = 0; i < childrenCount; i++) total += candies[i];

		System.out.println(total);
	}
}
