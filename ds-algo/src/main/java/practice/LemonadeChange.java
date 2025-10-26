package practice;

/* 25 Oct 2025 21:20 */

/** [860. Lemonade Change](https://leetcode.com/problems/lemonade-change/) */
public class LemonadeChange {
	public boolean lemonadeChange(int[] bills) {
		var five = 0;
		var ten = 0;
		for (var bill : bills) {
			if (bill == 5) {
				five++;
			} else if (bill == 10) {
				five--;
				ten++;
			} else {
				if (ten > 0) {
					ten--;
					five--;
				} else {
					five -= 3;
				}
			}
			if (five < 0) {
				return false;
			}
		}
		return true;
	}

	static void main() {
		var l = new LemonadeChange();
		System.out.println(l.lemonadeChange(new int[] {5, 5, 5, 10, 20})); // true
		System.out.println(l.lemonadeChange(new int[] {5, 5, 10, 10, 20})); // false
	}
}
