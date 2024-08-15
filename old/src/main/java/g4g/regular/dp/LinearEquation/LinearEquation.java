package g4g.regular.dp.LinearEquation;

/** Created by gakshintala on 6/10/16. */
public class LinearEquation {
  public static void main(String[] args) {
    int coeffs[] = {2, 2, 5};
    var rhs = 4;
    System.out.println(solutionCount(coeffs, rhs));
  }

  private static int solutionCount(int[] coefficients, int rhs) {
    var len = coefficients.length;
    var table = new int[rhs + 1];
    table[0] = 1; // if rhs=0, 1 solution i.e., all vals are 0s
    // Same as Coin Change problem, where we innumerable supply of coins,
    // we can have any value x and y, so any number of times of coefficients
    // Per coefficient
    for (var coefficient : coefficients) {
      // Looping through all possible vals till rhs
      for (var j = coefficient; j <= rhs; j++) {
        table[j] += table[j - coefficient];
      }
    }
    return table[rhs];
  }
}
