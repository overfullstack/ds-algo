package hackerrank;

/** Created by gakshintala on 2/23/16. */
public class CoinChange {

  public static void main(String[] args) {
    int[] coins = {1, 2, 3};
    var coinCount = 3;
    var total = 5;
    System.out.println(CountCoinCombinations(coins, coinCount, total));
  }

  private static int CountCoinCombinations(int[] coins, int coinCount, int total) {
    var table = new int[total + 1];
    table[0] = 1;
    /*
     * We are iterating coin after coin, so table[i] already consists of count excluding our coin, we just need to
     * add the value of count including our coin i.e table[j-s[i]];
     */
    for (var i = 0; i < coinCount; i++) {
      for (var j = coins[i]; j <= total; j++) {
        table[j] += table[j - coins[i]];
      }
      print(table);
      System.out.println();
    }
    return table[total];
  }

  private static void print(int[] table) {
    for (var i : table) {
      System.out.print(i + " ");
    }
  }
}
