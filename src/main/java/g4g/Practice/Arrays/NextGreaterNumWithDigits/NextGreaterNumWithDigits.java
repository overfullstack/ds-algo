package g4g.Practice.Arrays.NextGreaterNumWithDigits;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by gakshintala on 6/22/16.
 */
public class NextGreaterNumWithDigits {
    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var tests = scn.nextInt();
        while (tests-- > 0) {
            var len = scn.nextInt();
            var arr = new int[len];
            fillArray(arr, scn);
            nextGreaterNum(arr);
            printArr(arr);
        }
    }

    private static void printArr(int[] arr) {
        for (var e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    private static void nextGreaterNum(int[] arr) {
        var len = arr.length;
        var i = len - 2;
        // Find number that is smaller than its after number.
        for (; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                break;
            }
        }
        // Find number that is nextRight rightmost greater than arr[i] and less than arr[i+1]
        var nextGreaterNumPos = findNextGreaterNumPos(arr, i, i + 1);
        swap(arr, i, nextGreaterNumPos);
        Arrays.sort(arr, i + 1, len);
    }

    private static int findNextGreaterNumPos(int[] arr, int index, int start) {
        var len = arr.length;
        var minIndex = start;
        for (var j = start + 1; j < len; j++) {
            if (arr[j] > arr[index] && arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        return minIndex;
    }

    private static void fillArray(int[] arr, Scanner scn) {
        for (var i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }

    public static void swap(int[] arr, int pos1, int pos2) {
        if (pos1 == pos2) return;
        var temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }
}
