package practice;

/* 01 Oct 2025 16:59 */

/**
 * [2140. Solving Questions With
 * Brainpower](https://leetcode.com/problems/solving-questions-with-brainpower/)
 */
public class SolvingQuestionsWithBrainpower {
	public long mostPoints(int[][] questions) {
		return solve(0, questions, new long[questions.length]);
	}

	private static long solve(int idx, int[][] questions, long[] memo) {
		if (idx >= questions.length) {
			return 0;
		}
		if (memo[idx] != 0) {
			return memo[idx];
		}
		memo[idx] =
				Math.max(
						solve(idx + 1, questions, memo),
						questions[idx][0] + solve(idx + questions[idx][1] + 1, questions, memo));
		return memo[idx];
	}

	static void main() {
		var sol = new SolvingQuestionsWithBrainpower();
		System.out.println(sol.mostPoints(new int[][] {{3, 2}, {4, 3}, {4, 4}, {2, 5}})); // 5
		System.out.println(sol.mostPoints(new int[][] {{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}})); // 7
	}
}
