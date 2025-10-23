package practice.greedy;

/* 22 Oct 2025 20:58 */

/** [605. Can Place Flowers](https://leetcode.com/problems/can-place-flowers) */
public class CanPlaceFlowers {
	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		if (flowerbed.length == 0) {
			return n == 0;
		}
		if (flowerbed.length == 1) {
			return (n <= 1 && flowerbed[0] == 0) || (n == 0 && flowerbed[0] == 1);
		}
		var start = -1;
		for (var i = 0; i < flowerbed.length; i++) {
			if (flowerbed[i] == 1) {
				if (start < 0) {
					n -= (i <= 1) ? 0 : i / 2;
				} else {
					var emptyCount = i - 1 - start;
					n -= (emptyCount <= 2 ? 0 : (emptyCount - 1) / 2);
				}
				if (n <= 0) {
					return true;
				}
				start = i;
			}
		}
		if (start < 0) {
			n -= (flowerbed.length + 1) / 2;
		} else {
			var emptyCountAtEnd = flowerbed.length - 1 - start;
			n -= (emptyCountAtEnd <= 1) ? 0 : emptyCountAtEnd / 2;
		}
		return n <= 0;
	}

	static void main() {
		var canPlaceFlowers = new CanPlaceFlowers();
		System.out.println(canPlaceFlowers.canPlaceFlowers(new int[] {0, 1, 0}, 1)); // false
		System.out.println(canPlaceFlowers.canPlaceFlowers(new int[] {0, 0, 1, 0, 1}, 1)); // true
		System.out.println(canPlaceFlowers.canPlaceFlowers(new int[] {1, 0}, 1)); // false
		System.out.println(canPlaceFlowers.canPlaceFlowers(new int[] {1, 0, 0, 0}, 2)); // false
		System.out.println(canPlaceFlowers.canPlaceFlowers(new int[] {1, 0, 0, 0, 0, 1}, 2)); // false
		System.out.println(canPlaceFlowers.canPlaceFlowers(new int[] {1, 0, 0, 0, 1}, 1)); // true
		System.out.println(canPlaceFlowers.canPlaceFlowers(new int[] {1, 0, 0, 0, 1}, 2)); // false
	}
}
