package gfg.practice.arrays.SumOfFour;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/** Created by Gopala Akshintala on 2/4/17. */
public class SumOfFour {
	public static void main(String[] args) {
		var scn = new Scanner(System.in);
		var tests = scn.nextInt();
		while (tests-- > 0) {
			var len = scn.nextInt();
			var sumOfFour = scn.nextInt();
			var arr = new int[len];
			fillArray(arr, scn);
			sumOfFour(arr, sumOfFour);
			System.out.println();
		}
	}

	private static void sumOfFour(int[] arr, int sumOfFour) {
		var auxiliaryArr = computeAuxiliaryArr(arr);
		Arrays.sort(auxiliaryArr);
		Set<Set<PairSum>> allCombinations = new HashSet<>();
		int left = 0, right = auxiliaryArr.length - 1;
		while (left < right) {
			var currentSum = auxiliaryArr[left].sum + auxiliaryArr[right].sum;
			if (currentSum == sumOfFour && noCommon(auxiliaryArr[left], auxiliaryArr[right])) {
				Set<PairSum> combination =
						new HashSet<>(Arrays.asList(auxiliaryArr[left], auxiliaryArr[right]));
				allCombinations.add(combination);
				left++;
				right--;
			} else if (currentSum < sumOfFour) {
				left++;
			} else {
				right--;
			}
		}
		printAllCombinations(allCombinations);
	}

	private static void printAllCombinations(Set<Set<PairSum>> allCombinations) {
		if (allCombinations.size() > 0) {
			allCombinations.forEach(
					combination -> {
						combination.forEach(e -> System.out.print(e.first + " " + e.second + " "));
						System.out.print("$");
					});
		} else {
			System.out.print("-1");
		}
	}

	private static boolean noCommon(PairSum pair1, PairSum pair2) {
		return !(pair1.first == pair2.first
				|| pair1.first == pair2.second
				|| pair1.second == pair2.first
				|| pair1.second == pair2.second);
	}

	private static PairSum[] computeAuxiliaryArr(int[] arr) {
		var arrLen = arr.length;
		var auxiliaryArrLen = arrLen * (arrLen - 1) / 2;
		var auxiliaryArr = new PairSum[auxiliaryArrLen];
		var k = 0;
		for (var i = 0; i < arrLen - 1; i++) {
			for (var j = i + 1; j < arrLen; j++) {
				auxiliaryArr[k] = new PairSum();
				auxiliaryArr[k].sum = arr[i] + arr[j];
				auxiliaryArr[k].first = i;
				auxiliaryArr[k].second = j;
				k++;
			}
		}
		return auxiliaryArr;
	}

	private static void fillArray(int[] arr, Scanner scn) {
		for (var i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}
	}
}

class PairSum implements Comparable<PairSum> {
	int first;
	int second;
	int sum;

	@Override
	public int compareTo(PairSum o) {
		return Integer.compare(this.sum, o.sum);
	}
}
