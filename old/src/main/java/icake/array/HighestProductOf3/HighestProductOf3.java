package icake.array.HighestProductOf3;

/// Created by gakshintala on 6/21/16.
public class HighestProductOf3 {
  static void main() {
    int[] arr = {1, -2, 3, 4};
    IO.println(highestProductOf3(arr));
  }

  private static int highestProductOf3(int[] arr) {
    var len = arr.length;
    var maxOf1 = Math.max(arr[0], arr[1]);
    var minOf1 = Math.min(arr[0], arr[1]); // For negative numbers
    IO.println("One - " + maxOf1 + " " + minOf1);

    var maxProduct2 = arr[0] * arr[1];
    var minProduct2 = arr[0] * arr[1];
    IO.println("Two - " + maxProduct2 + " " + minProduct2);

    var maxProduct3 = maxProduct2 * arr[2];
    IO.println("Three - " + maxProduct3);

    // Starting from index 2 as it might affect *Of2 and *Of1
    for (var i = 2; i < arr.length; i++) {
      // If we found new maxProduct3, doesn't always lead to updation of other 2.
      // We do in reverse (3->2->1) cause current element need to be checked with previous records.
      // Updating
      // maxProduct3 doesn't affect other 2, but doing in reverse affects
      IO.println("---");
      maxProduct3 = Math.max(maxProduct3, Math.max(maxProduct2 * arr[i], minProduct2 * arr[i]));
      IO.println("Three - " + maxProduct3);

      maxProduct2 = Math.max(maxProduct2, maxOf1 * arr[i]);
      minProduct2 = Math.min(minProduct2, minOf1 * arr[i]);
      IO.println("Two - " + maxProduct2 + " " + minProduct2);

      maxOf1 = Math.max(maxOf1, arr[i]);
      minOf1 = Math.min(minOf1, arr[i]);
      IO.println("One - " + maxOf1 + " " + minOf1);
    }
    return maxProduct3;
  }
}
