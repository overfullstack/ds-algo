package g4g.Regular.Arrays.SortIfSubStringReversed;

/** Created by Gopala Akshintala on 2/26/17. */
public class SortIfSubStringReversed {
  public static void main(String[] args) {
    int[] arr = {1, 2, 5, 4, 3};
    System.out.println(sortIfSubStringReversed(arr));
  }

  private static boolean sortIfSubStringReversed(int[] arr) {
    var len = arr.length;
    if (len == 1) {
      return true;
    }

    var i = 1;
    while (i < len && arr[i] > arr[i - 1]) {
      i++;
    }
    if (i == len) {
      return true;
    }

    var j = i;
    while (j < len && arr[j] < arr[j - 1]) {
      if (i > 1
          && arr[j]
              < arr[i - 2]) { // i stops at one position after the anomaly as anomaly also satisfies
        // the condition arr[i] > arr[i - 1]
        return false;
      }
      j++;
    }
    if (j == len) {
      return true;
    }

    var k = j;

    if (arr[k] < arr[i - 1]) {
      return false;
    }

    while (k < len && k > 1) {
      if (arr[k] < arr[k - 1]) {
        return false;
      }
      k++;
    }
    return true;
  }
}
