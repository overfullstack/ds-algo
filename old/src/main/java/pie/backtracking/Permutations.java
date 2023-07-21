package pie.backtracking;

/** Created by gakshintala on 6/10/16. */
public class Permutations {
  public static void main(String[] args) {
    var str = "abc";
    permute(str, new StringBuilder(), new boolean[str.length()]);
  }

  private static void permute(String str, StringBuilder permutation, boolean[] used) {
    // Check the satisfying condition
    if (permutation.length() == str.length()) {
      System.out.println(permutation);
      return;
    }
    for (var i = 0; i < str.length(); i++) {
      // Check is Valid
      if (!used[i]) {
        permutation.append(str.charAt(i));
        used[i] = true;
        permute(str, permutation, used);

        // Backtrack
        used[i] = false;
        permutation.setLength(permutation.length() - 1);
      }
    }
  }
}
