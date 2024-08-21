package gfg.regular.SortAndSearch.SortTwoElementsSwapped;

import java.util.Arrays;

/** Created by gakshintala on 4/14/16. */
public class SortTwoElementsSwapped {
  public static void main(String[] args) {
    int[] arr = {10, 30, 20, 40, 50, 60, 70};
    correctArray(arr);
    System.out.println(Arrays.toString(arr));
  }

  private static void correctArray(int[] arr) {
    var len = arr.length;
    // Right to left
    for (var i = len - 1; i >= 1; i--) { // The edge case is both swapped nodes are side-by-side.
      if (arr[i] < arr[i - 1]) { // find anomaly
        var j = i - 1;

        // Starting from [i-1], Find the first encounter of smaller element [j] than [i] towards
        // left,
        // so it can fit before it in position [j+1]
        while (j >= 0 && arr[j] > arr[i]) {
          j--;
        }

        // Swap the number before the first smaller encounter
        swap(arr, i, j + 1); // `j` points to prev number of anomaly.
        break;
      }
    }
  }

  private static void swap(int[] arr, int i, int j) {
    var temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
