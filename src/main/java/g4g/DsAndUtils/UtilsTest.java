package g4g.DsAndUtils;

import org.junit.Test;

import static g4g.DsAndUtils.Utils.binarySearch;
import static g4g.DsAndUtils.Utils.printArray;
import static g4g.DsAndUtils.Utils.quickSort;

public class UtilsTest {

    @Test
    public void quickSortTest() {
        int[] arr = {5, 3, 6, 2, 7};
        quickSort(arr);
        printArray(arr);
    }

    @Test
    public void binarySearchTest() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(binarySearch(arr, 8));
    }
}