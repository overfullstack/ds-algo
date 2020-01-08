package cci.Arrays;

/**
 * Created by Gopala Akshintala on 11/2/16.
 */
public class UniqueChars {
    public static void main(String[] args) {
        System.out.println(isStringContainsAllUniqueChars("abcd"));
        System.out.println(isStringContainsAllUniqueChars("abca"));
    }

    private static boolean isStringContainsAllUniqueChars(String str) {
        var mask = 0;
        for (var i = 0; i < str.length(); i++) {
            var val = str.charAt(i) - 'a';
            if ((mask & (1 << val)) > 0) {
                return false;
            }
            mask |= (1 << val);
        }
        return true;
    }
}
