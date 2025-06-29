package educative;

import static educative.graph.unionfind.LongestConsecutiveSequenceKt.longestConsecutiveSequenceKt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UnionFindf {
	public Map<Integer, Integer> parent;
	public Map<Integer, Integer> size;
	public int maxLength;

	// Constructor
	public UnionFindf(int[] nums) {
		parent = new HashMap<>();
		size = new HashMap<>();
		maxLength = 1;

		for (int num : nums) {
			parent.put(num, num);
			size.put(num, 1);
		}
	}

	// Function to find the root of a sequence to which num belongs
	public int find(int num) {
		if (parent.get(num) != num) {
			parent.put(num, find(parent.get(num)));
		}
		return parent.get(num);
	}

	// Function to merge the two sequences and update lengths
	public void union(int num1, int num2) {
		int xRoot = find(num1);
		int yRoot = find(num2);
		if (xRoot != yRoot) {
			if (size.get(xRoot) < size.get(yRoot)) {
				int temp = xRoot;
				xRoot = yRoot;
				yRoot = temp;
			}
			parent.put(yRoot, xRoot);
			size.put(xRoot, size.get(xRoot) + size.get(yRoot));
			maxLength = Math.max(maxLength, size.get(xRoot));
		}
	}

	public static int longestConsecutiveSequence(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		// data structure for implementing union find
		UnionFindf ds = new UnionFindf(nums);

		for (int num : nums) {
			// check if the next consecutive number
			// is in the input array
			if (ds.parent.containsKey(num + 1)) {
				ds.union(num, num + 1);
			}
		}

		return ds.maxLength;
	}

	// driver code
	public static void main(String[] args) {
		int[][] inputNums = {
			{150, 14, 200, 1, 3, 2},
			{1, 2, 3, 4, 5, 6, 7},
			{1, 3, 5, 7},
			{7, 6, 5, 4, 3, 2, 1},
			{7, 6, 5, 1}
		};

		for (int i = 0; i < inputNums.length; i++) {
			System.out.println((i + 1) + ".\tnums = " + Arrays.toString(inputNums[i]));
			System.out.println(
					"\tThe length of the longest consecutive sequence is: "
							+ longestConsecutiveSequenceKt(inputNums[i]));
			System.out.println(new String(new char[100]).replace('\0', '-'));
		}
	}
}
