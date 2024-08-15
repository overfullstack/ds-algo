package g4g.regular.array.AlternatePositiveNegative;

import java.util.Arrays;

/** Created by gakshintala on 6/12/16. */
public class AlternatePositiveNegative {
  public static void main(String[] args) {
    int arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
    alternatePositiveNegative(arr);
    Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
  }

  private static void alternatePositiveNegative(int[] arr) {
    var outOfPlace = -1;
    for (var i = 0; i < arr.length; i++) {
      if (outOfPlace >= 0) {
        // Search for suitable element index with opposite sign to place in outOfPlace
        if ((arr[i] >= 0 && arr[outOfPlace] < 0) || (arr[i] < 0 && arr[outOfPlace] >= 0)) {
          rightShiftAndInsert(arr, i, outOfPlace);
          // If the diff is > 2, which means no suitable element is found in between. i.e., all of
          // them have
          // same sign as current outOfPlace. So nextRight element of current outOfPlace also has
          // the same sign,
          // which makes it eligible nextRight outOfPlace
          if (i - outOfPlace > 2) {
            // In this case, Index is already moved far ahead of outOfPlace so no need to increment
            // index
            outOfPlace += 2;
          } else {
            outOfPlace = -1;
          }
        }
      }

      // Search for outOfPlace
      if (outOfPlace == -1) {
        if ((arr[i] > 0 && i % 2 != 0) || (arr[i] < 0 && i % 2 == 0)) {
          outOfPlace = i;
        }
      }
    }
  }

  private static void rightShiftAndInsert(int[] arr, int rightBound, int insertIndex) {
    var temp = arr[rightBound];
    System.arraycopy(arr, insertIndex, arr, insertIndex + 1, rightBound - insertIndex);
    arr[insertIndex] = temp;
  }
}
