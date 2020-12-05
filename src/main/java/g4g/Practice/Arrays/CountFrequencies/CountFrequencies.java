package g4g.Practice.Arrays.CountFrequencies;

import java.util.Scanner;

/**
 * Created by gakshintala on 7/6/16.
 */
public class CountFrequencies {
    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var tests = scn.nextInt();
        while (tests-- > 0) {
            var len = scn.nextInt();
            var arr = new int[len];
            fillArray(arr, scn);
            printFrequency(arr);
            System.out.println();
        }
    }

    private static void printFrequency(int[] arr) {
        var len = arr.length;
        // This is necessary or the element which is equal to len will create a problem while doing arr[i]%len
        for (var i = 0; i < len; i++) {
            arr[i]--;
        }
        for (var i = 0; i < len; i++) {
            // arr[i]%len because arr[i] might increase from it's actual value due to these increments, so we need to get actual value
            arr[arr[i] % len] += len;
        }
        for (var freq : arr) {
            System.out.print(freq / len + " ");
        }
    }

    private static void fillArray(int[] arr, Scanner scn) {
        for (var i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }
}
