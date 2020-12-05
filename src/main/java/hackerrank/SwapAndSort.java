package hackerrank;

import java.util.Arrays;

public class SwapAndSort {

    public static void main(String[] args) {
        int[] arr = {1, 5, 5, 5, 5};
        System.out.println(solution(arr));
    }

    public static boolean solution(int[] A) {
        var length = A.length;
        var i = length - 1;
        for (; i > 0; i--) {
            if (A[i] < A[i - 1]) {
                var k = i;
                while (A[k] == A[k + 1]) k++;
                var j = i - 1;
                while (j >= 0 && A[i] < A[j])
                    j--;
                swap(A, k, j + 1);
                System.out.println(Arrays.toString(A));
                return isArraySorted(A, length);
            }
        }

        return true;
    }

    private static boolean isArraySorted(int[] a, int length) {
        for (var i = 0; i < length - 1; i++) {
            if (a[i] > a[i + 1]) return false;
        }
        return true;
    }

    private static void swap(int[] a, int i, int j) {
        var temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}