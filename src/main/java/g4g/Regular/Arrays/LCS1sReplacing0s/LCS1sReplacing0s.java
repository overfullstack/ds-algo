package g4g.Regular.Arrays.LCS1sReplacing0s;

import java.util.Arrays;

/**
 * Created by gakshintala on 4/12/16.
 */
public class LCS1sReplacing0s {
    public static void main(String[] args) {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1};
        System.out.println(Arrays.toString(findMax1s(arr)));
    }

    private static int[] findMax1s(int[] arr) {
        var len = arr.length;
        int prev_prev_zero, prev_zero;
        prev_prev_zero = prev_zero = -1;
        int max = 0, maxIndex = 0;

        for (var cur = 0; cur < len; cur++) {
            if (arr[cur] == 0) {
                var maxCurr = cur - prev_prev_zero;
                if (maxCurr > max) {
                    max = maxCurr;
                    maxIndex = prev_zero;
                }
                prev_prev_zero = prev_zero;
                prev_zero = cur;
            }
        }

        if (len - prev_prev_zero > max) {
            max = len - prev_prev_zero;
            maxIndex = prev_zero;
        }

        return new int[]{maxIndex, max};
    }
}
