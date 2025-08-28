package practice;

import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordBreak {
	public boolean wordBreak(String s, List<String> wordDict) {
		var wordDictSet = new HashSet<>(wordDict);

		var finalEndIndices =
				IntStream.range(0, s.length())
						.boxed()
						.reduce(
								List.of(-1), // Initial accumulator
								(wordEndIndices, index) -> {
									var canFormWord =
											wordEndIndices.stream()
													.anyMatch(
															prevEndIdx ->
																	wordDictSet.contains(s.substring(prevEndIdx + 1, index + 1)));

									return canFormWord
											? Stream.concat(wordEndIndices.stream(), Stream.of(index)).toList()
											: wordEndIndices;
								},
								(_, list2) -> list2 // Combiner (not used in sequential streams)
								);

		return !finalEndIndices.isEmpty() && finalEndIndices.getLast() == s.length() - 1;
	}
}
