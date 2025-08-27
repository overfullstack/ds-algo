package practice;

/** Created by gakshintala on 7/2/16. [69. Sqrt(x)](https://leetcode.com/problems/sqrtx/) */
public class SqrtX {
	static void main() {
		var x = 81;
		System.out.println(new SqrtX().mySqrt(x));
	}

	public int mySqrt(int x) {
		if (x == 0 || x == 1) {
			return x;
		}
		var left = 1;
		var right = x;
		while (left < right) {
			var mid = left + (right - left) / 2;
			if (mid < x / mid) {
				left = mid + 1;
			} else if (mid > x / mid) {
				right = mid;
			} else {
				return mid;
			}
		}
		return left - 1;
	}
}
