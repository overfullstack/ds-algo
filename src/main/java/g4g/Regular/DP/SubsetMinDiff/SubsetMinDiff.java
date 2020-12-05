package g4g.Regular.DP.SubsetMinDiff;

import java.util.Arrays;

/**
 * Created by gakshintala on 6/13/16.
 */
public class SubsetMinDiff {
    public static void main(String[] args) {
        int arr[] = {3, 1, 4, 2, 2, 1};
        System.out.println(minDiff(arr));
    }

    private static int minDiff(int[] arr) {
        var sum = Arrays.stream(arr).sum();

        var len = arr.length;
        var table = new boolean[sum + 1][len + 1];

        for (var i = 0; i <= len; i++) {
            table[0][i] = true;
        }
        // This can be omitted as array is by default initialized to false.
        /*for (int i = 1; i <= sum; i++) {
            table[i][0] = false;
        }*/

        for (var i = 1; i <= sum; i++) {
            for (var j = 1; j <= len; j++) {
                table[i][j] = table[i][j - 1]; // Exclude
                if (arr[j - 1] <= i) {
                    table[i][j] |= table[i - arr[j - 1]][j];
                }
            }
        }

        for (var i = sum / 2; i <= sum; i++) {
            if (table[i][len]) {
                return sum - 2 * i; // (sum - i) - i
            }
        }
        return -1;
    }
}
