package g4g.Practice.Arrays.MaxProductSubArray;

import java.util.Scanner;

/**
 * Created by gakshintala on 7/3/16.
 */
public class MaxProductSubArray {
    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var tests = scn.nextInt();
        while (tests-- > 0) {
            var len = scn.nextInt();
            var arr = new int[len];
            for (var i = 0; i < arr.length; i++) {
                arr[i] = scn.nextInt();
            }
            System.out.println(maxProductSubArray(arr));
        }
    }

    private static int maxProductSubArray(int[] arr) {
        int curMax = 1, curMin = 1, maxSoFar = 1;
        for (int value : arr) {
            if (value > 0) {
                curMax *= value;
                curMin = Math.min(curMin * value, 1); // `Math.min` as curMin might not have got into negative zone
            } else if (value == 0) {
                curMax = curMin = 1;
            } else {
                var temp = curMax;
                curMax = Math.max(curMin * value, 1);
                curMin = temp * value;
            }
            maxSoFar = Math.max(maxSoFar, curMax); // we store result after every iteration,
            // as curMin and curMax keep shuffling as we encounter negative numbers
        }
        return maxSoFar;
    }

    private static void fillArray(int[] arr, Scanner scn) {
        for (var i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }
}
