package practice;

/* 06 Oct 2025 16:53 */

/** [553 · Bomb Enemy](https://www.lintcode.com/problem/553/) */
public class BombEnemy {
	public int maxKilledEnemies(char[][] grid) {
		if (grid.length == 0) {
			return 0;
		}
		var enemyCount = new int[grid.length][grid[0].length];
		var count = 0;
		for (var row = 0; row < grid.length; row++) {
			count = 0;
			for (var col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == 'E') {
					count++;
				} else if (grid[row][col] == 'W') {
					count = 0;
				}
				enemyCount[row][col] = count;
			}
			count = 0;
			for (var col = grid[0].length - 1; col >= 0; col--) {
				if (grid[row][col] == 'E') {
					count++;
				} else if (grid[row][col] == 'W') {
					count = 0;
				}
				enemyCount[row][col] += count;
			}
		}
		for (var col = 0; col < grid[0].length; col++) {
			count = 0;
			for (var row = 0; row < grid.length; row++) {
				if (grid[row][col] == 'E') {
					count++;
				} else if (grid[row][col] == 'W') {
					count = 0;
				}
				enemyCount[row][col] += count;
			}
			count = 0;
			for (var row = grid.length - 1; row >= 0; row--) {
				if (grid[row][col] == 'E') {
					count++;
				} else if (grid[row][col] == 'W') {
					count = 0;
				}
				enemyCount[row][col] += count;
			}
		}
		var maxEnemies = 0;
		for (var row = 0; row < grid.length; row++) {
			for (var col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == '0') {
					maxEnemies = Math.max(maxEnemies, enemyCount[row][col]);
				}
			}
		}
		return maxEnemies;
	}

	static void main() {
		var bombEnemy = new BombEnemy();
		System.out.println(
				bombEnemy.maxKilledEnemies(
						new char[][] {
							{'0', 'E', '0', '0'},
							{'E', '0', 'W', 'E'},
							{'0', 'E', '0', '0'}
						})); // 3
		System.out.println(
				bombEnemy.maxKilledEnemies(
						new char[][] {
							{'0', 'E', '0', '0'},
							{'E', 'E', 'W', 'E'},
							{'0', 'E', '0', '0'}
						})); // 2
	}
}
