package gfg.regular.SortAndSearch.SearchInArrWithDiff1;

/** Created by gakshintala on 6/13/16. */
public class SearchInArrWithDiff1 {
  public static void main(String[] args) {
    int arr[] = {8, 7, 6, 7, 6, 5, 4, 3, 2, 3, 4, 3};
    var key = 3;
    System.out.println(find(arr, key));
  }

  private static int find(int[] arr, int key) {
    var diff = Math.abs(key - arr[0]);
    // We constantly move forward, coz if the number has to be found according to diff, the arr
    // should be
    // strictly increasing or decreasing by 1. Since the number is not found in diff, arr is zig-zag
    // and there is
    // no way the number can be present inside that zig-zag. So we move fwd and search outside.
    for (var i = diff; i < arr.length; i += diff) {
      if (arr[i] == key) return i;
      diff = Math.abs(key - arr[i]);
    }
    return -1;
  }
}
