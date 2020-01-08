package g4g.Regular.Arrays.UniqueInSortedArray;

/**
 * Created by gakshintala on 6/22/16.
 */
public class UniqueInSortedArray {
    public static void main(String[] args) {
        int arr[] = {1, 1, 2, 4, 4, 5, 5, 6, 6};
        searchUnique(arr, 0, arr.length - 1);
    }

    private static void searchUnique(int[] arr, int low, int high) {
        if (low > high) return;

        var mid = (low + high) / 2;
        
        // this can also be replaced by low==high, but the below condition can detect early, in cases like unique 
        // element falls in middle of an array
        if ((arr[mid] != arr[mid - 1]) && (arr[mid] != arr[mid + 1])) {
            System.out.println(arr[mid]);
            return;
        }

        if (mid % 2 == 0) { // If mid even, means we landed on an odd position
            if (arr[mid] == arr[mid + 1]) { // If this condition satisfies in odd position, left part is clear, so 
                // search right side
                searchUnique(arr, mid + 2, high);
            } else {
                searchUnique(arr, low, mid);
            }
        } else { // If landed in even position
            if (arr[mid] == arr[mid - 1]) { // this condition states left part is clear, so search right
                searchUnique(arr, mid, high);
            } else {
                searchUnique(arr, low, mid - 2);
            }
        }
    }
}
