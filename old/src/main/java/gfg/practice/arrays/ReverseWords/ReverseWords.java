package gfg.practice.arrays.ReverseWords;

import java.util.Scanner;

/** Created by Gopala Akshintala on 5/21/17. */
public class ReverseWords {
	public static void main(String[] args) {
		var scn = new Scanner(System.in);
		var testCases = scn.nextInt();
		while (testCases-- > 0) {
			var str = scn.next();
			System.out.println(reverseWords(str.toCharArray()));
		}
	}

	private static String reverseWords(char[] str) {
		var len = str.length;
		var wordBeginIndex = -1;
		for (var i = 0;
				i < len;
				i++) { // Leading spaces shall be taken care, as they don't satisfy any of the if
			// conditions.
			// wordBeginIndex is not set until a non-space character is encountered. For Multiple spaces
			// and leading spaces
			if (wordBeginIndex == -1 && str[i] != '.') {
				wordBeginIndex = i;
			}
			// End of string or Space character.
			if ((wordBeginIndex != -1) && ((i == len - 1)) || (str[i + 1] == '.')) {
				reverse(wordBeginIndex, i, str);
				wordBeginIndex = -1;
			}
		}
		// Reverse the entire string
		return String.valueOf(reverse(0, len - 1, str));
	}

	private static char[] reverse(int begin, int end, char[] str) {
		var low = begin;
		var high = end;

		while (low < high) {
			var temp = str[low];
			str[low] = str[high];
			str[high] = temp;
			low++;
			high--;
		}
		return str;
	}
}
