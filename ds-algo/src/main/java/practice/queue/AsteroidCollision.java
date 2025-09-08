package practice.queue;

import java.util.ArrayDeque;
import java.util.Arrays;

/* 26 Aug 2025 13:19 */

/** [735. Asteroid Collision](https://leetcode.com/problems/asteroid-collision) */
public class AsteroidCollision {
	public int[] asteroidCollision(int[] asteroids) {
		var queue = new ArrayDeque<Integer>();
		for (var asteroid : asteroids) {
			if (asteroid > 0) {
				queue.add(asteroid);
			} else {
				while (!queue.isEmpty() && queue.getLast() > 0 && queue.getLast() < Math.abs(asteroid)) {
					queue.removeLast();
				}
				// ! Whether to add the negative asteroid or not
				if (!queue.isEmpty() && queue.getLast() == Math.abs(asteroid)) {
					queue.removeLast();
				} else if (queue.isEmpty() || queue.getLast() < 0) {
					queue.add(asteroid);
				}
			}
		}
		return queue.stream().mapToInt(i -> i).toArray();
	}

	static void main() {
		var obj = new AsteroidCollision();
		System.out.println(Arrays.toString(obj.asteroidCollision(new int[] {5, 10, -5}))); // [5,10]
		System.out.println(Arrays.toString(obj.asteroidCollision(new int[] {8, -8}))); // []
		System.out.println(Arrays.toString(obj.asteroidCollision(new int[] {10, 2, -5}))); // [10]
	}
}
