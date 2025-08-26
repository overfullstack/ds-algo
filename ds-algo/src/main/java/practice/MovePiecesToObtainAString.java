package practice;

/* 26 Aug 2025 17:08 */

/**
 * [2337. Move Pieces to Obtain a
 * String](https://leetcode.com/problems/move-pieces-to-obtain-a-string/)
 */
public class MovePiecesToObtainAString {
	public boolean canChange(String start, String target) {
		var i = 0;
		var j = 0;
		var targetLen = target.length();
		while (i <= targetLen && j <= targetLen) {
			while (i < targetLen && start.charAt(i) == '_') i++;
			while (j < targetLen && target.charAt(j) == '_') j++;

			if (i == targetLen || j == targetLen) {
				return i == targetLen && j == targetLen;
			}

			if (start.charAt(i) != target.charAt(j)) {
				return false;
			}

			if (target.charAt(j) == 'L' && j > i) {
				return false;
			}

			if (target.charAt(j) == 'R' && j < i) {
				return false;
			}
			i++;
			j++;
		}
		return true;
	}
}
