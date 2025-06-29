package hackerrank;

import java.util.Scanner;

/** Created by gakshintala on 12/15/15. */
public class GridSearch {

	public static void main(String[] args) {
		var scn = new Scanner(System.in);
		var tc = scn.nextInt();

		while (tc-- > 0) {
			var pRow = scn.nextInt();
			var pCol = scn.nextInt();
			var pMat = readMatrix(scn, pRow);

			var cRow = scn.nextInt();
			var cCol = scn.nextInt();
			var cMat = readMatrix(scn, cRow);

			var isPresent = false;
			for (var i = 0; i <= pCol - cCol && !isPresent; i++) {
				var start = pMat[i].indexOf(cMat[0]);
				if (start != -1) isPresent = checkPresence(pMat, cMat, start, i, cRow, cCol);
			}
			System.out.println(isPresent ? "YES" : "NO");
		}
	}

	private static boolean checkPresence(
			String[] pMat, String[] cMat, int start, int matchRow, int cRow, int cCol) {
		for (var i = matchRow + 1; i < matchRow + cRow; i++)
			if (!pMat[i].substring(start, start + cCol).equalsIgnoreCase(cMat[i - matchRow]))
				return false;
		return true;
	}

	private static String[] readMatrix(Scanner scn, int row) {
		var mat = new String[row];
		for (var i = 0; i < row; i++) mat[i] = scn.next();
		return mat;
	}
}
