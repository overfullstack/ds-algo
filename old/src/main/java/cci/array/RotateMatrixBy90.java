package cci.array;

import java.util.Arrays;

/** Created by gakshintala on 4/14/16. */
public class RotateMatrixBy90 {
	static void main() {
		int[][] mat = {
			{1, 2, 3, 4, 5},
			{6, 7, 8, 9, 10},
			{11, 12, 13, 14, 15},
			{16, 17, 18, 19, 20},
			{21, 22, 23, 24, 25}
		};
		printMat(mat);
		System.out.println();
		rotateMatBy90(mat);
		printMat(mat);
	}

	private static void rotateMatBy90(int[][] mat) {
		var n = mat.length;
		for (var layer = 0; layer < n / 2; layer++) {
			var last = n - 1 - layer;
			for (var i = layer; i < last; i++) {
				var first = layer;
				var offset = i - first;
				var temp = mat[first][i];
				mat[first][i] = mat[last - offset][first]; // bottom to top (Left)
				mat[last - offset][first] = mat[last][last - offset]; // left to right (bottom)
				mat[last][last - offset] = mat[i][last]; // top to bottom (right)
				mat[i][last] = temp; // right to left (top)
			}
		}
	}

	private static void printMat(int[][] mat) {
		for (var row : mat) {
			System.out.println(Arrays.toString(row));
		}
	}
}
