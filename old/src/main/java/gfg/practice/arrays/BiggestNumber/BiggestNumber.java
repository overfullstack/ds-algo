package gfg.practice.arrays.BiggestNumber;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BiggestNumber {
	static void main() {
		var scn = new Scanner(System.in);
		var tests = scn.nextInt();
		while (tests-- > 0) {
			var len = scn.nextInt();
			var arr = readArray(scn, len);
			System.out.println(biggestNumberFromArray(arr));
		}
	}

	private static String biggestNumberFromArray(int[] arr) {
		var arrList =
				Arrays.stream(arr)
						.boxed()
						.sorted(
								(a, b) -> { // {9,6} -> 96 or 69, then 9 should come before 6 in sorted order
									var ba = String.valueOf(b) + a;
									var ab = String.valueOf(a) + b;
									return Integer.compare(
											Integer.parseInt(ba),
											Integer.parseInt(ab)); // Note we are comparing in reverse
								})
						.collect(Collectors.toList());

		return arrList.stream().map(String::valueOf).collect(Collectors.joining());
	}

	private static int[] readArray(Scanner scn, int len) {
		var arr = new int[len];
		for (var i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}
		return arr;
	}
}
