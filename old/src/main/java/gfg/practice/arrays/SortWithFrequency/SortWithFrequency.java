package gfg.practice.arrays.SortWithFrequency;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SortWithFrequency {
	public static void main(String[] args) {
		var scn = new Scanner(System.in);
		var tests = scn.nextInt();
		while (tests-- > 0) {
			var len = scn.nextInt();
			var arr = readArray(scn, len);
			printSortedWithFrequency(arr);
			System.out.println();
		}
	}

	private static void printSortedWithFrequency(int[] arr) {
		Map<Integer, Element> freqMap = new HashMap<>();
		for (var i = 0; i < arr.length; i++) {
			var element = freqMap.get(arr[i]);
			if (element == null) {
				freqMap.put(arr[i], new Element(i, 1, arr[i]));
			} else {
				element.freq++;
			}
		}

		freqMap.values().stream()
				.sorted(
						(ele1, ele2) -> {
							if (ele1.freq == ele2.freq) {
								// The practice problem says if freq is same order as per val.
								// If required we can modify this to be order as per index as well
								return Integer.compare(ele1.val, ele2.val);
							}
							return Integer.compare(ele2.freq, ele1.freq);
						})
				.flatMap(ele -> Collections.nCopies(ele.freq, ele.val).stream())
				.forEach(i -> System.out.print(i + " "));
	}

	private static int[] readArray(Scanner scn, int len) {
		var arr = new int[len];
		for (var i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}
		return arr;
	}
}

class Element {
	int firstIndex;
	int freq;
	int val;

	public Element(int firstIndex, int freq, int val) {
		this.firstIndex = firstIndex;
		this.freq = freq;
		this.val = val;
	}
}
