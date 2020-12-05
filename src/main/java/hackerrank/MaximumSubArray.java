package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by gakshintala on 2/25/16.
 */
public class MaximumSubArray {

    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var testCases = scn.nextInt();
        while (testCases-- > 0) {
            List<Integer> arr = new ArrayList<>();
            var size = scn.nextInt();
            while (size-- > 0) {
                arr.add(scn.nextInt());
            }
            System.out.println(findMaximumSubarrayContiguous(arr).maxSum + " " + findMaximumSubarrayNonContiguous(arr));
        }
    }

    /*public static Subarray findMaximumSubarray2(List<Integer> A) {
        Subarray range = new Subarray(0, 0, 0);
        int startIndex = 0, sum = 0;
        int maxSum = 0, minSum = 0;
        for (int i = 0; i < A.size(); ++i) {
            sum += A.get(i);
            if (sum < minSum) {
                minSum = sum;
                startIndex = i + 1;
            }
            if (sum - minSum > maxSum) { // Nullifying the Negative portion of sum by adding it back to sum
                maxSum = sum - minSum;
                range = new Subarray(startIndex + 1, i + 1, maxSum);
            }
        }
        return range;
    }*/

    public static Subarray findMaximumSubarrayContiguous(List<Integer> arr) {
        var subarray = new Subarray(0, 0, 0);
        int sum = 0, startIndex = 0, maxSum = 0;
        for (var i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
            // Only a bigger negative, can nullify the sum that was accumulated till now. It essentially is an indication for end of this chain
            // If current negative hasn't made the sum negative, the sum survives until a big positive bumps up its value.
            if (sum < 0) {
                sum = 0;
                startIndex = i + 1;
            } else if (sum > maxSum) {
                maxSum = sum;
                subarray = new Subarray(startIndex + 1, i + 1, maxSum);
            }
        }
        if (maxSum == 0) subarray.maxSum = getMaxInArray(arr);
        return subarray;
    }

    private static int getMaxInArray(List<Integer> arr) {
        int max = arr.get(0);
        for (var i = 1; i < arr.size(); i++) {
            int val = arr.get(i);
            if (val > max) max = val;
        }

        return max;
    }

    public static int findMaximumSubarrayNonContiguous(List<Integer> arr) {
        var sum = 0;
        for (Integer integer : arr) {
            if (integer > 0) sum += integer;
        }
        if (sum == 0) return getMaxInArray(arr);
        return sum;
    }
}

class Subarray {
    public int start;
    public int end;
    public int maxSum;

    public Subarray(int start, int end, int maxSum) {
        this.start = start;
        this.end = end;
        this.maxSum = maxSum;
    }

    @Override
    public String toString() {
        return "(" + start + "," + end + "): " + maxSum;
    }
}
