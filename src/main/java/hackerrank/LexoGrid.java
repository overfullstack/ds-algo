package hackerrank;

import java.util.Scanner;

public class LexoGrid {

    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var c = scn.nextInt();
        for (var i = 0; i < c; i++) {
            var n = scn.nextInt();
            scn.nextLine();
            var a = new char[n][n];
            for (var j = 0; j < n; j++) {
                a[j] = scn.nextLine().toCharArray();
                sort(a[j]);
            }
            int j = 0, k = 0;
            row_loop:
            for (; j < n; j++) {
                k = 0;
                for (; k < n - 1; k++) {
                    if (a[k][j] > a[k + 1][j]) {
                        break row_loop;
                    }
                }
            }

            if (j == n && k == n - 1) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    public static void sort(char[] a) {
        for (var i = 0; i < a.length; i++) {
            var temp = a[i];
            var j = i - 1;
            var flag = false;
            for (; j >= 0; j--) {
                if (temp < a[j]) {
                    a[j + 1] = a[j];
                    flag = true;
                } else break;
            }
            if (flag) a[j + 1] = temp;
        }
    }
}