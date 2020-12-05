package g4g.Practice.Arrays.WaveArray;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by gakshintala on 6/22/16.
 */
public class WaveArray {
    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var tests = scn.nextInt();
        while (tests-- > 0) {
            var len = scn.nextInt();
            var arr = new int[len];
            fillArray(arr, scn);
            Arrays.sort(arr);
            waveIt(arr);
            printArr(arr);
        }
    }

    private static void waveIt(int[] arr) {
        for (var i = 0; i < arr.length - 1; i += 2) {
            swap(arr, i, i + 1);
        }
    }

    private static void fillArray(int[] arr, Scanner scn) {
        for (var i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }

    private static void printArr(int[] arr) {
        for (var e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int pos1, int pos2) {
        if (pos1 == pos2) return;
        var temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }
}
