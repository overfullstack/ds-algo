package g4g.regular.DP.WordBreakDP;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** Created by Gopala Akshintala on 5/3/17. */
public class WordBreakDP {
  private static Set<String> dictionary =
      new HashSet<>(
          Arrays.asList(
              "mobile",
              "samsung",
              "sam",
              "sung",
              "man",
              "mango",
              "icecream",
              "and",
              "go",
              "i",
              "like",
              "ice",
              "cream"));

  public static void main(String[] args) {
    System.out.println(wordBreak("ilikesamsung"));
    System.out.println(wordBreak("samsungandmangok"));
  }

  private static boolean wordBreak(String word) {
    var len = word.length();
    var table = new boolean[len + 1];
    for (var i = 1; i <= len; i++) {
      if (!table[i] && isDictionaryContainsWord(word.substring(0, i))) {
        table[i] = true;
      }
      if (table[i]) {
        if (i == len) {
          return true;
        }
        for (var j = i + 1; j <= len; j++) {
          if (!table[j] && isDictionaryContainsWord(word.substring(i, j))) {
            table[j] = true;
          }
          if (j == len && table[j]) {
            return true;
          }
        }
      }
    }

    return false;
  }

  private static boolean isDictionaryContainsWord(String word) {
    return dictionary.contains(word);
  }
}
