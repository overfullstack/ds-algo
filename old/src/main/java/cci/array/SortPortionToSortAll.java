package cci.array;

import static java.util.Arrays.sort;

import java.util.Arrays;

/** Created by gakshintala on 4/14/16. */
public class SortPortionToSortAll {
  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
    sortPortion(arr);
    System.out.println(Arrays.toString(arr));
  }

  private static void sortPortion(int[] arr) {
    var i = findAnomalyFromLeft(arr);
    if (i == arr.length - 1) {
      System.out.println("Already sorted");
      return;
    }
    var j = findAnomalyFromRight(arr);

    // Find min and max elements inside the anomaly
    int minIndex = i, maxIndex = j;
    for (var k = i + 1; k <= j - 1; k++) {
      if (arr[k] < arr[minIndex]) minIndex = k;
      if (arr[k] > arr[maxIndex]) maxIndex = k;
    }

    // Although we found the anomaly, it doesn't necessarily be the point of array to be sorted.
    // We find the max and min elements inside anomaly to check how broad does this portion spread.
    var m = findStartOfPortion(arr, minIndex);
    var n = findEndOfPortion(arr, maxIndex);
    System.out.println("Sort Portion Start:" + m + " End:" + n);
    sort(arr, m, n + 1);
  }

  private static int findEndOfPortion(int[] arr, int j) {
    var len = arr.length;
    for (var l = j + 1; l < len; l++) {
      if (arr[l] > arr[j]) return l - 1;
    }
    return len - 1;
  }

  private static int findStartOfPortion(int[] arr, int i) {
    for (var k = i - 1; k >= 0; k--) {
      if (arr[k] < arr[i]) return k + 1;
    }
    return 0;
  }

  private static int findAnomalyFromLeft(int[] arr) {
    var len = arr.length;
    for (var i = 1; i < len; i++) {
      if (arr[i] < arr[i - 1]) return i;
    }
    return len - 1;
  }

  private static int findAnomalyFromRight(int[] arr) {
    var len = arr.length;
    for (var i = len - 2; i >= 0; i--) {
      if (arr[i] > arr[i + 1]) return i;
    }
    return 0;
  }
}
