package gfg.regular.Recursion.WordBreak;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** Created by Gopala Akshintala on 5/3/17. */
public class WordBreak {
	private static Set<String> dictionary =
			new HashSet<>(
					Arrays.asList(
							"mobile",
							"samsung",
							"sam",
							"sung",
							"man",
							"mango",
							"icecream",
							"and",
							"go",
							"i",
							"love",
							"ice",
							"cream"));

	public static void main(String[] args) {
		var word = "iloveicecreamandmango";
		wordBreak(word, "");
	}

	private static void wordBreak(String word, String result) {
		var len = word.length();
		for (var i = 1;
				i <= len;
				i++) { // Loop takes care of starting different branches of recursion for common letter
			// words
			var subWord = word.substring(0, i);
			if (dictionary.contains(subWord)) {
				if (i == len) {
					System.out.println(result + subWord);
					return;
				}
				wordBreak(word.substring(i, len), result + subWord + " ");
			}
		}
	}
}
