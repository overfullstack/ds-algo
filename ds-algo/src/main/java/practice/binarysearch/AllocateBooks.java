package practice.binarysearch;

/* 15 Oct 2025 10:47 */

import java.util.ArrayList;
import java.util.List;

/** [Allocate Books](https://www.naukri.com/code360/problems/allocate-books_1090540) */
public class AllocateBooks {
	public static int findPages(ArrayList<Integer> books, int n, int m) {
		if (m > books.size()) {
			return -1;
		}
		var left = books.stream().mapToInt(i -> i).max().orElseThrow();
		var right = books.stream().mapToInt(i -> i).sum();
		while (left < right) {
			var mid = left + (right - left) / 2;
			var partitionCount = partitionCount(mid, books);
			if (partitionCount <= m) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}

	private static int partitionCount(int pageCapacity, List<Integer> books) {
		var partitionCount = 0;
		var sum = 0;
		for (var book : books) {
			sum += book;
			if (sum > pageCapacity) {
				sum = book;
				partitionCount++;
			}
		}
		return partitionCount + 1;
	}

	static void main() {
		System.out.println(findPages(new ArrayList<>(List.of(12, 34, 67, 90)), 4, 2)); // 113
		System.out.println(findPages(new ArrayList<>(List.of(25, 46, 28, 49, 24)), 5, 4)); // 71
		System.out.println(findPages(new ArrayList<>(List.of(2, 8, 8, 4, 5)), 5, 6)); // -1
		System.out.println(findPages(new ArrayList<>(List.of(1, 17, 14, 9, 15, 9, 14)), 7, 7)); // 17
	}
}
