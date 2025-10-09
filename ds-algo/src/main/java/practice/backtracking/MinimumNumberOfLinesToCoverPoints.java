package practice.backtracking;

/* 09 Oct 2025 21:03 */

/**
 * [3808 · Minimum Number of Lines to Cover Points](https://www.lintcode.com/problem/3808/)
 */
public class MinimumNumberOfLinesToCoverPoints {
	public int minimumLines(int[][] points) {
		var visited = 0;
		var memo = new int[1 << points.length];
		return solve(points, visited, memo);
	}

	private static int solve(int[][] points, int visited, int[] memo) {
		if (visited == (1 << points.length) - 1) {
			return 0;
		}
		if (memo[visited] != 0) {
			return memo[visited];
		}
		var minLines = Integer.MAX_VALUE - 1;
		for (var p1 = 0; p1 < points.length; p1++) {
			if (((visited >> p1) & 1) == 0) {
				for (var p2 = p1 + 1; p2 < points.length; p2++) {
					if (((visited >> p2) & 1) == 0) {
						var nextVisited = visited | (1 << p1) | (1 << p2);
						for (var p3 = p2 + 1; p3 < points.length; p3++) {
							if (((visited >> p3) & 1) == 0 && areCollinear(points[p1], points[p2], points[p3])) {
								nextVisited |= (1 << p3);
							}
						}
						minLines = Math.min(minLines, 1 + solve(points, nextVisited, memo));
					}
				}
				// Special case: as inner loop gets skipped as if p1 = n - 1 => p2 = n
				if (p1 == points.length - 1) {
					minLines = Math.min(minLines, 1 + solve(points, (visited | 1 << p1), memo));
				}
			}
		}
		memo[visited] = minLines;
		return minLines;
	}

	private static boolean areCollinear(int[] point1, int[] point2, int[] point3) {
		return (point2[0] - point1[0]) * (point3[1] - point1[1])
				== (point3[0] - point1[0]) * (point2[1] - point1[1]);
	}

  static void main() {
    var sol = new MinimumNumberOfLinesToCoverPoints();
    System.out.println(sol.minimumLines(new int[][]{{0, 1}, {2, 3}, {4, 5}, {4, 3}})); // 2
    System.out.println(sol.minimumLines(new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}})); // 1
  }
}
