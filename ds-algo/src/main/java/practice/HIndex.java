package practice;

/* 25 Oct 2025 20:32 */

/** [274. H-Index](https://leetcode.com/problems/h-index) */
public class HIndex {
	public int hIndex(int[] citations) {
		var buckets = new int[citations.length + 1];
		for (var citation : citations) {
			// ! Any with more than citations length fall into nth extra bucket
			if (citation > citations.length) { // ! or `buckets.length - 1`
				buckets[buckets.length - 1]++;
			} else {
				buckets[citation]++;
			}
		}
		var paperCount = 0;
		for (var i = buckets.length - 1; i >= 0; i--) { // ! Iterating backwards
			paperCount += buckets[i];
			// ! All citations above `i` are cited more than `i` times
			if (paperCount >= i) {
				return i;
			}
		}
		return 0;
	}

	static void main() {
		var l = new HIndex();
		System.out.println(l.hIndex(new int[] {3, 0, 6, 1, 5})); // 3
		System.out.println(l.hIndex(new int[] {1, 3, 1})); // 1
	}
}
