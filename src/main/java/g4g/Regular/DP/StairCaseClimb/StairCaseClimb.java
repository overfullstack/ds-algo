package g4g.Regular.DP.StairCaseClimb;

/** Created by Gopala Akshintala on 6/2/17. */
public class StairCaseClimb {
  public static void main(String[] args) {
    int stairsCount = 4, numWaysToJump = 2;
    System.out.println(numberOfWaysToJump(stairsCount, numWaysToJump));
  }

  private static int numberOfWaysToJump(int stairsCount, int numWaysToJump) {
    var table = new int[stairsCount + 1];
    table[0] = 1;
    table[1] = 1;
    for (var i = 2; i <= stairsCount; i++) {
      for (var j = 1; j <= numWaysToJump && j <= i; j++) {
        table[i] += table[i - j];
      }
    }
    return table[stairsCount];
  }
}
