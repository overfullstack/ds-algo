package pie.backtracking;

/** Created by gakshintala on 6/10/16. */
public class Combinations {
	public static void main(String[] args) {
		var str = "abc";
		combine(0, str, new StringBuilder());
	}

	private static void combine(int start, String str, StringBuilder combination) {
		// With 'start', We reduce the possibilities to backtrack at every recursive level,
		// first loop has a,b,c to backtrack, next only has b,c and next only c.
		// This can be converted to crude backtracking by checking position > start for is valid.
		for (var i = start; i < str.length(); i++) {
			combination.append(str.charAt(i));
			System.out.println(combination);
			combine(i + 1, str, combination);
			// Backtrack
			combination.setLength(combination.length() - 1);
		}
	}
}
