package hackerrank;

import java.util.Scanner;

public class AngryProf {

    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var c = scn.nextInt();
        for (var i = 0; i < c; i++) {
            var n = scn.nextInt();
            var k = scn.nextInt();

            for (var j = 0; j < n; j++) {
                var num = scn.nextInt();
                if (k > 0 && num <= 0) k--;
                else {
                }
            }

            if (k == 0) System.out.println("NO");
            else System.out.println("YES");
        }
    }
}