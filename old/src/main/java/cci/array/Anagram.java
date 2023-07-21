package cci.array;

/** Created by Gopala Akshintala on 11/2/16. */
public class Anagram {
  public static void main(String[] args) {
    System.out.println(areStringAnagrams("abc", "cab"));
    System.out.println(areStringAnagrams("abc", "acd"));
  }

  private static boolean areStringAnagrams(String str1, String str2) {
    if (str1.length() != str2.length()) {
      return false;
    }
    var letters = new int[128];
    for (var c : str1.toCharArray()) {
      letters[c]++;
    }

    for (var c : str2.toCharArray()) {
      letters[c]--;
      if (letters[c] < 0) {
        return false;
      }
    }
    return true;
  }
}
