package practice;

/* 22 Oct 2025 11:42 */

/**
 * [1592. Rearrange Spaces Between
 * Words](https://leetcode.com/problems/rearrange-spaces-between-words/)
 */
public class RearrangeSpacesBetweenWords {
	public String reorderSpaces(String text) {
		var words = text.trim().split("\\s+");
		var spaceCount = (int) text.chars().filter(c -> c == ' ').count();
		var gapLen = words.length <= 1 ? 0 : spaceCount / (words.length - 1);
		var endGapLen = spaceCount - gapLen * (words.length - 1);
		return String.join(" ".repeat(gapLen), words) + " ".repeat(endGapLen);
	}

	static void main() {
		var rearrangeSpacesBetweenWords = new RearrangeSpacesBetweenWords();
		System.out.println(
				rearrangeSpacesBetweenWords.reorderSpaces(
						"  this   is  a sentence ")); // "this   is   a   sentence"
		System.out.println(
				rearrangeSpacesBetweenWords.reorderSpaces(
						" practice   makes   perfect")); // "practice   makes   perfect "
		System.out.println(rearrangeSpacesBetweenWords.reorderSpaces("  hello")); // "hello  "
	}
}
