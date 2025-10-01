package practice.dp;

/* 01 Oct 2025 13:55 */

/**
 * [10. Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/)
 */
public class RegularExpressionMatching {
  public boolean isMatch(String s, String p) {
    return solve(0, s, 0, p, new Result[s.length() + 1][p.length() + 1]);
  }
  
  private static boolean solve(int sIdx, String s, int pIdx, String p, Result[][] memo) {
    if (memo[sIdx][pIdx] != null) {
      return memo[sIdx][pIdx] == Result.TRUE;
    }
    var result = false;
    if (pIdx == p.length()) {
      result = sIdx == s.length();
    } else {
      var firstMatch = sIdx < s.length() && (s.charAt(sIdx) == p.charAt(pIdx) || p.charAt(pIdx) == '.');
      if (pIdx + 1 < p.length() && p.charAt(pIdx + 1) == '*') {
        result = solve(sIdx, s, pIdx + 2, p, memo) || (firstMatch && solve(sIdx + 1, s, pIdx, p, memo));
      } else {
        result = firstMatch && solve(sIdx + 1, s, pIdx + 1, p, memo);
      }
    }
    memo[sIdx][pIdx] = result ? Result.TRUE: Result.FALSE;
    return result;
  }

  private enum Result {
    TRUE,
    FALSE;
  }

  static void main() {
    var sol = new RegularExpressionMatching();
    System.out.println(sol.isMatch("aa", "a")); // false
    System.out.println(sol.isMatch("aa", "a*")); // true
    System.out.println(sol.isMatch("ab", ".*")); // true
    System.out.println(sol.isMatch("mississippi", "mis*is*p*")); // false
    System.out.println(sol.isMatch("mississippi", "mis*is*ip.*")); // true
    System.out.println(sol.isMatch("ab", ".*c")); // false
  }
}
