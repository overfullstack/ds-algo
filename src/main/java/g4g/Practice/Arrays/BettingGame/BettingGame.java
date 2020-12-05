package g4g.Practice.Arrays.BettingGame;

import java.util.Scanner;

/**
 * Created by gakshintala on 6/14/16.
 */
public class BettingGame {
    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var tests = scn.nextInt();
        var strings = new String[tests];
        for (var i = 0; i < tests; i++) {
            strings[i] = scn.next();
        }

        var initAmount = 4;
        for (var str : strings) {
            System.out.println(bettingOutcome(initAmount, str));
        }
    }

    private static int bettingOutcome(int amount, String str) {
        var bet = 1;
        for (var result : str.toCharArray()) {
            amount -= bet;
            if (amount < 0) {
                return -1;
            }
            if (result == 'W') {
                amount += 2 * bet;
                bet = 1;
            } else {
                bet *= 2;
            }
        }
        return amount;
    }
}
