package ci.DP.MinCoinChange;

/**
 * Created by gakshintala on 3/22/16.
 */
public class MinCoinChange {
    public static void main(String[] args) {
        int[] coins = {1, 3, 9, 10};
        System.out.println(findMinCoinChange(coins, 15));
        System.out.println(findMinCoinChange2(coins, 15));
    }

    private static int findMinCoinChange(int[] coins, int total) {
        var table = new int[total + 1];
        table[0] = 0;
        for (var i = 1; i <= total; i++) {
            table[i] = Integer.MAX_VALUE;
        }

        // For every coin, looping through all the sums
        // Here we fill all table[] values for a coin
        for (var coin : coins) {
            for (var j = coin; j <= total; j++) { // Min sum a coin can make starts with coin value
                table[j] = Math.min(table[j], table[j - coin] + 1); // Math.min(Excluding, Including + 1)
            }
        }
        return table[total];
    }

    private static int findMinCoinChange2(int[] coins, int total) {
        var table = new int[total + 1];

        // For every sum val, looping through all coins.
        // Here we fill one table[index] value per val iteration
        for (var val = 1; val <= total; val++) {
            table[val] = Integer.MAX_VALUE;
            for (var coin : coins) {
                if (coin <= val)
                    //Math.min(Excluding, including)
                    table[val] = Math.min(table[val], table[val - coin] + 1); // Note the +1 is inside min.
            }
        }
        return table[total];
    }
}
