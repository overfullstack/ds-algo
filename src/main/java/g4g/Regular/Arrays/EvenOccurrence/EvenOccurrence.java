package g4g.Regular.Arrays.EvenOccurrence;

import java.util.ArrayList;
import java.util.List;

/** Created by gakshintala on 6/27/16. */
public class EvenOccurrence {
  public static void main(String[] args) {
    int arr[] = {9, 12, 23, 10, 12, 12, 15, 23, 14, 12, 15};
    System.out.println(evenOccurrence(arr));
  }

  private static List<Integer> evenOccurrence(int[] arr) {
    var len = arr.length;
    var checker = 0;

    // Prepare bit map for even odd occurrences. Even shall have 0 and odd have 1.
    for (var ele : arr) {
      checker ^= (1 << ele);
    }

    List<Integer> evenOccurrences = new ArrayList<>();

    // Traverse again to check which number has 0 and which has 1.
    for (var i = 0; i < len - 1; i++) {
      if ((checker & (1 << arr[i])) == 0) {
        evenOccurrences.add(arr[i]);
      }
      // Setting it to 1 to avoid duplicates
      checker |= (1 << arr[i]);
    }
    return evenOccurrences;
  }
}
