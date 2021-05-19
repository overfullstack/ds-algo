package g4g.Regular.Arrays;

/** Created by gakshintala on 6/10/16. */
public class NumOddOccurance {
  public static void main(String[] args) {
    int arr[] = {2, 3, 5, 4, 5, 2, 4, 3, 5, 2, 4, 4, 2};
    var result = 0;
    for (var i : arr) {
      result ^= i;
    }
    System.out.println(result);
  }
}
