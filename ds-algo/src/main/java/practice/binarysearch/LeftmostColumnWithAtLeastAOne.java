package practice.binarysearch;

/* 06 Oct 2025 06:52 */

/**
 * [3683 · Columns with at Least a One on the Leftmost Side](https://www.lintcode.com/problem/3683/)
 */
public class LeftmostColumnWithAtLeastAOne {

	public int getLeftmostColumnWithOne(BinaryMatrix binaryMatrix) {
		var dimensions = binaryMatrix.dimensions();
		var rows = dimensions[0];
		var leftmostCol = dimensions[1];
		for (var row = 0; row < rows; row++) {
			var left = 0;
			var right = leftmostCol;
			while (left < right) {
				var mid = left + (right - left) / 2;
				if (binaryMatrix.get(row, mid) == 1) {
					right = mid;
				} else {
					left = mid + 1;
				}
			}
			leftmostCol = Math.min(leftmostCol, right);
		}
		return leftmostCol == dimensions[1] ? -1 : leftmostCol;
	}

	static class BinaryMatrix {
		private final int[][] matrix;

		BinaryMatrix(int[][] matrix) {
			this.matrix = matrix;
		}

		public int get(int row, int col) {
			return matrix[row][col];
		}

		public int[] dimensions() {
			return new int[] {matrix.length, matrix[0].length};
		}
	}

	static void main() {
		var leftmostColumnWithOne = new LeftmostColumnWithAtLeastAOne();
		var binaryMatrix = new BinaryMatrix(new int[][] {{0, 0, 0, 1}, {0, 0, 1, 1}, {0, 1, 1, 1}});
		System.out.println(leftmostColumnWithOne.getLeftmostColumnWithOne(binaryMatrix)); // 1
		var binaryMatrix2 = new BinaryMatrix(new int[][] {{0, 0}, {1, 1}});
		System.out.println(leftmostColumnWithOne.getLeftmostColumnWithOne(binaryMatrix2)); // 0
		var binaryMatrix3 = new BinaryMatrix(new int[][] {{0, 0}, {0, 1}});
		System.out.println(leftmostColumnWithOne.getLeftmostColumnWithOne(binaryMatrix3)); // 1
		var binaryMatrix4 = new BinaryMatrix(new int[][] {{0, 0}, {0, 0}});
		System.out.println(leftmostColumnWithOne.getLeftmostColumnWithOne(binaryMatrix4)); // -1
	}
}
