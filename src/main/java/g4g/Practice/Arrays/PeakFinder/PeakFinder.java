package g4g.Practice.Arrays.PeakFinder;

import java.util.Scanner;

/** Created by gakshintala on 6/13/16. */
public class PeakFinder {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var testCases = scn.nextInt();
    while (testCases-- > 0) {
      var len = scn.nextInt();
      var arr = readArray(scn, len);
      System.out.println(arr[findPeak(arr, 0, arr.length - 1)]);
    }
  }

  private static int findPeak(int[] arr, int left, int right) {
    var mid = (left + right) / 2;
    // Since we are always travelling towards positive slope, if the element is greater than both
    // neighbours
    // Check if neighbours exists with ||, to avoid ArrayOutOfBoundExceptions
    if ((mid == 0 || arr[mid] >= arr[mid - 1])
        && (mid == arr.length - 1 || arr[mid] >= arr[mid + 1])) {
      return mid;
    }
    if (mid >= 1 && arr[mid - 1] > arr[mid]) { // Always mind checking the index range for `mid`.
      return findPeak(arr, left, (mid - 1));
    }

    // If middle element is not peak and its right neighbor is greater than it, then right half must
    // have a peak
    // element. Since this indicates positive slope, Travel towards the peak i.e., right-side.
    return findPeak(arr, (mid + 1), right);
  }

  private static int[] readArray(Scanner scn, int len) {
    var arr = new int[len];
    for (var i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
    return arr;
  }
}
