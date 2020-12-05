package g4g.Practice.Arrays.Sort0s1s2s;

import java.util.Arrays;
import java.util.Scanner;

public class Sort0s1s2s {
    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var testCases = scn.nextInt();
        while (testCases-- > 0) {
            var len = scn.nextInt();
            var arr = readArr(scn, len);
            Arrays.stream(sort0s1s2s(arr)).forEach(val -> System.out.print(val + " "));
            System.out.println();
        }
    }

    private static int[] sort0s1s2s(int[] arr) {
        var low = 0;
        var mid = 0;
        var high = arr.length - 1;

        while (mid <= high) { // <= high because even the high needs to be checked in cases without 2 like 0010
            switch (arr[mid]) {
                case 0 -> {
                    swap(low, mid, arr);
                    low++;
                    mid++;
                }
                case 1 -> mid++;
                case 2 -> {// notice mid++ is not done, as the value swapped from high needs to checked again
                    swap(mid, high, arr);
                    high--;
                }
            }
        }
        return arr;
    }

    private static void swap(int pos1, int pos2, int[] arr) {
        var temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    private static int[] readArr(Scanner scn, int len) {
        var arr = new int[len];
        for (var i = 0; i < len; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }
}
