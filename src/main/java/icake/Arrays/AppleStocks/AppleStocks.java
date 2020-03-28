package icake.Arrays.AppleStocks;

/**
 * Created by gakshintala on 3/22/16.
 */
public class AppleStocks {
    public static void main(String[] args) {
        int[] stocks = {4, 3, 2, 1};
        System.out.println(maxProfit2(stocks));
    }

    private static int maxProfit(int[] stocks) {
        var len = stocks.length;
        if (len < 2) {
            throw new IllegalArgumentException();
        }

        var minBuyPrice = stocks[0];
        var maxProfit = Integer.MIN_VALUE;
        // Buy for Min and Sell for Max.
        // If you got to sell in future, max profit can occur only if u buy for minBuyPrice
        // so keeping track of minBuyPrice as we move fwd and comparing the difference for max profit
        for (var i = 1; i < len; i++) {
            // The order of computing minBuyPrice and maxProfit doesn't matter.
            minBuyPrice = Math.min(minBuyPrice, stocks[i]);
            maxProfit = Math.max(maxProfit, stocks[i] - minBuyPrice);
        }
        return maxProfit;
    }

    private static int maxProfit2(int[] arr) {
        var maxDiff = -1;
        var n = arr.length;
        // Initialize max element from right side 
        var maxRight = arr[n - 1];

        for (var i = n - 2; i >= 0; i--) {
            if (arr[i] > maxRight)
                maxRight = arr[i];
            else {
                var diff = maxRight - arr[i];
                if (diff > maxDiff) {
                    maxDiff = diff;
                }
            }
        }
        return maxDiff;
    }
}
