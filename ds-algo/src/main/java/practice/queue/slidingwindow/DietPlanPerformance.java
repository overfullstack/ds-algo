package practice.queue.slidingwindow;

/* 23 Oct 2025 17:44 */

/** [1176 - Diet Plan Performance](https://leetcode.ca/2019-02-18-1176-Diet-Plan-Performance/) */
public class DietPlanPerformance {
	public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
		var sum = 0;
		var points = 0;
		for (var i = 0; i < k; i++) {
			sum += calories[i];
		}
		points += points(sum, lower, upper);
		for (var i = k; i < calories.length; i++) {
			sum -= calories[i - k];
			sum += calories[i];
			points += points(sum, lower, upper);
		}
		return points;
	}

	private static int points(int value, int lower, int upper) {
		if (value < lower) {
			return -1;
		}
		if (value > upper) {
			return 1;
		}
		return 0;
	}

	static void main() {
		var dietPlanPerformance = new DietPlanPerformance();
		System.out.println(
				dietPlanPerformance.dietPlanPerformance(new int[] {6, 5, 0, 0}, 2, 1, 5)); // 0
		System.out.println(
				dietPlanPerformance.dietPlanPerformance(new int[] {1, 2, 3, 4, 5}, 1, 3, 3)); // 0
		System.out.println(dietPlanPerformance.dietPlanPerformance(new int[] {3, 2}, 2, 0, 1)); // 1
	}
}
