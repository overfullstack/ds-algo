package practice;

import java.util.Comparator;
import java.util.List;

public class LongestWordInDictionaryThroughDeleting {
	public String findLongestWord(String s, List<String> dictionary) {
		final var sortedDictionary =
				dictionary.stream()
						.sorted(
								Comparator.comparingInt(String::length)
										.reversed()
										.thenComparing(Comparator.naturalOrder()))
						.toList();
		for (final var word : sortedDictionary) {
			if (word.length() <= s.length()) {
				var wordIdx = 0;
				final var charArray = s.toCharArray();
				for (var i = 0; i < charArray.length && wordIdx < word.length(); i++) {
					if (charArray[i] == word.charAt(wordIdx)) {
						wordIdx++;
					}
				}
				if (wordIdx == word.length()) {
					return word;
				}
			}
		}
		return "";
	}

	public static void main(String[] args) {
		final var obj = new LongestWordInDictionaryThroughDeleting();
		System.out.println(obj.findLongestWord("abpcplea", List.of("ale", "apple", "monkey", "plea")));
	}
}
