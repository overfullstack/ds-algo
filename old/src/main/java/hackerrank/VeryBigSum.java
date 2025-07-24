package hackerrank;

import java.util.ArrayList;
import java.util.Scanner;

public class VeryBigSum {

	static void main() {
		var scn = new Scanner(System.in);
		var n = scn.nextInt();
		var sum = new ArrayList<Integer>();
		for (var i = 0; i < n; i++) {
			sum = sumArrays(sum, intToArrayList(scn.nextInt()));
		}

		for (var i = sum.size() - 1; i >= 0; i--) {
			System.out.print(sum.get(i));
		}
	}

	public static ArrayList<Integer> intToArrayList(int a) {
		var number = new ArrayList<Integer>();
		for (; a > 0; a /= 10) {
			number.add(a % 10);
		}
		return number;
	}

	public static ArrayList<Integer> sumArrays(ArrayList<Integer> a, ArrayList<Integer> b) {
		int i = 0, carry = 0;
		var sum = new ArrayList<Integer>();
		for (; i < a.size() && i < b.size(); i++) {
			var digitSum = carry + a.get(i) + b.get(i);
			sum.add(i, digitSum % 10);
			carry = digitSum / 10;
		}

		for (; i < a.size(); i++) {
			var digitSum = carry + a.get(i);
			sum.add(i, digitSum % 10);
			carry = digitSum / 10;
		}

		for (; i < b.size(); i++) {
			var digitSum = carry + b.get(i);
			sum.add(i, digitSum % 10);
			carry = digitSum / 10;
		}

		if (carry != 0) sum.add(i, carry);

		return sum;
	}
}
