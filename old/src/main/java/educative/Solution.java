package educative;

public class Solution {
	public static void main(String[] args) {
		int[][] bombs = {
			{56, 80, 2},
			{55, 9, 10},
			{32, 75, 2},
			{87, 89, 1},
			{61, 94, 3},
			{43, 82, 9},
			{17, 100, 6},
			{50, 6, 7},
			{9, 66, 7},
			{98, 3, 6},
			{67, 50, 2},
			{79, 39, 5},
			{92, 60, 10},
			{49, 9, 9},
			{42, 32, 10}
		};
		Solution solution = new Solution();
		System.out.println(solution.maximumDetonation(bombs));
	}

	public int maximumDetonation(int[][] bombs) {
		int n = bombs.length, ans = 0;
		for (int i = 0; i < n; i++) {
			ans = Math.max(ans, dfs(i, new boolean[n], bombs));
		}
		return ans;
	}

	private int dfs(int idx, boolean[] v, int[][] bombs) {
		int count = 1;
		v[idx] = true;
		int n = bombs.length;
		for (int i = 0; i < n; i++) {
			if (!v[i] && inRange(bombs[idx], bombs[i])) {
				count += dfs(i, v, bombs);
			}
		}
		return count;
	}

	private boolean inRange(int[] a, int[] b) {
		long dx = a[0] - b[0], dy = a[1] - b[1], r = a[2];
		return dx * dx + dy * dy <= r * r;
	}
}
