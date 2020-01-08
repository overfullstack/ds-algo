package g4g.Practice.Arrays.MinDiffBtPairs;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Gopala Akshintala on 30/01/17.
 */
public class MinDiffBtPairs {
    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var tests = scn.nextInt();
        while (tests-- > 0) {
            var len = scn.nextInt();
            var arr = new int[len];
            fillArray(arr, scn);
            System.out.println(minDiffBtPairs(arr));
        }
    }

    private static int minDiffBtPairs(int[] arr) {
        Arrays.sort(arr);
        var diff = arr[1] - arr[0];
        for (var i = 1; i < arr.length - 1; i++) {
            diff = Math.min(arr[i + 1] - arr[i], diff);
        }
        return diff;
    }

    private static void fillArray(int[] arr, Scanner scn) {
        for (var i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }
}
