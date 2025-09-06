package practice.dp;

/* 06 Sep 2025 17:46 */

/**
 * [2431 - Maximize Total Tastiness of Purchased
 * Fruits](https://leetcode.ca/2022-07-27-2431-Maximize-Total-Tastiness-of-Purchased-Fruits/)
 */
public class MaximizeTotalTastinessOfPurchasedFruits {
	public int maxTastiness(int[] price, int[] tastiness, int maxAmount, int maxCoupons) {
		return maxTastiness(
				0,
				price,
				tastiness,
				maxAmount,
				maxCoupons,
				new int[price.length][maxAmount + 1][maxCoupons + 1]);
	}

	public int maxTastiness(
			int idx,
			int[] price,
			int[] tastiness,
			int remainingAmount,
			int remainingCoupons,
			int[][][] memo) {
		if (idx == price.length) {
			return 0;
		}
		if (memo[idx][remainingAmount][remainingCoupons] != 0) {
			return memo[idx][remainingAmount][remainingCoupons];
		}
		// ! 3 Choices - Skip, Buy, Buy with coupon
		var maxTastiness =
				maxTastiness(idx + 1, price, tastiness, remainingAmount, remainingCoupons, memo);
		if (remainingAmount >= price[idx]) {
			maxTastiness =
					Math.max(
							maxTastiness,
							tastiness[idx]
									+ maxTastiness(
											idx + 1,
											price,
											tastiness,
											remainingAmount - price[idx],
											remainingCoupons,
											memo));
		}
		if (remainingAmount >= (price[idx] / 2) && remainingCoupons > 0) {
			maxTastiness =
					Math.max(
							maxTastiness,
							tastiness[idx]
									+ maxTastiness(
											idx + 1,
											price,
											tastiness,
											remainingAmount - (price[idx] / 2),
											remainingCoupons - 1,
											memo));
		}
		memo[idx][remainingAmount][remainingCoupons] = maxTastiness;
		return maxTastiness;
	}

	static void main() {
		var maxTastiness = new MaximizeTotalTastinessOfPurchasedFruits();
		System.out.println(
				maxTastiness.maxTastiness(new int[] {10, 20, 20}, new int[] {5, 8, 8}, 20, 1)); // 13
		System.out.println(
				maxTastiness.maxTastiness(new int[] {10, 15, 7}, new int[] {5, 8, 20}, 10, 2)); // 28
	}
}
