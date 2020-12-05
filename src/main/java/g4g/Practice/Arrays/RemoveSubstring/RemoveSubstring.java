package g4g.Practice.Arrays.RemoveSubstring;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by gakshintala on 6/24/16.
 */
public class RemoveSubstring {
    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var tests = scn.nextInt();
        while (tests-- > 0) {
            var subStringLen = scn.nextInt();
            var subStrings = new String[subStringLen];
            fillArray(subStrings, scn);
            var str = scn.next().toCharArray();
            printCharArr(removeSubstring(str, subStrings));
        }
    }

    private static void fillArray(String[] subStrings, Scanner scn) {
        for (var i = 0; i < subStrings.length; i++) {
            subStrings[i] = scn.next();
        }
    }

    private static void printCharArr(char[] chars) {
        var strb = new StringBuilder();
        for (var c : chars) {
            strb.append(c);
        }
        System.out.println(strb);
    }

    private static char[] removeSubstring(char[] str, String[] subStrings) {
        Arrays.sort(subStrings, (o1, o2) -> -Integer.compare(o1.length(), o2.length()));
        int j = 0, strIndex = 0, strLen = str.length;
        while (j < strLen) {
            for (String string : subStrings) {
                if (isSubstringMatch(str, string.toCharArray(), j)) {
                    j += string.length();
                }
            }

            if (j >= strLen) break;

            for (String subString : subStrings) {
                var subStrLen = subString.length();
                if (subStrLen > 1 && subString.charAt(subStrLen - 1) == str[j]
                        && (strIndex - (subStrLen - 2)) >= 0
                        && isSubstringMatch(str, subString.substring(0, subStrLen - 1).toCharArray(), strIndex - (subStrLen - 2))) {
                    strIndex -= (subStrLen - 2);
                }
            }
            str[strIndex++] = str[j++];
        }

        if (strIndex - subStrings[0].length() >= 0
                && isSubstringMatch(str, subStrings[0].toCharArray(), strIndex - subStrings[0].length())) {
            strIndex -= subStrings[0].length();
        }

        return Arrays.copyOfRange(str, 0, strIndex);
    }

    private static boolean isSubstringMatch(char[] str, char[] subString, int j) {
        for (var c : subString) {
            if (str[j] != c) {
                return false;
            }
            j++;
        }
        return true;
    }
}
