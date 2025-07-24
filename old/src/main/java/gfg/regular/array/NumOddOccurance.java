package gfg.regular.array;

/** Created by gakshintala on 6/10/16. */
public class NumOddOccurance {
	static void main() {
		int[] arr = {2, 3, 5, 4, 5, 2, 4, 3, 5, 2, 4, 4, 2};
		var result = 0;
		for (var i : arr) {
			result ^= i;
		}
		System.out.println(result);
	}
}
