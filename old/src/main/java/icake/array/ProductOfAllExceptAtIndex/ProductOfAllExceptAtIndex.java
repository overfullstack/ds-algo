package icake.array.ProductOfAllExceptAtIndex;

import java.util.Arrays;

/** Created by Gopala Akshintala on 10/15/16. */
public class ProductOfAllExceptAtIndex {
  public static void main(String[] args) {
    int[] arr = {1, 7, 3, 4};
    System.out.println(Arrays.toString(getProductOfAllExceptAtIndex(arr)));
  }

  private static int[] getProductOfAllExceptAtIndex(int[] arr) {
    var productOfAllExceptAtIndex = new int[arr.length];
    var productSoFar = 1;
    // All products for previous indices is stored.
    // Current index of product array should contain product of all elements before that, except
    // this
    for (var i = 0; i < arr.length; i++) {
      productOfAllExceptAtIndex[i] = productSoFar;
      productSoFar *= arr[i];
    }

    // All products for before indices is added upon previous ones, as we traverse in reverse
    productSoFar = 1;
    for (var i = arr.length - 1; i >= 0; i--) {
      productOfAllExceptAtIndex[i] *= productSoFar;
      productSoFar *= arr[i];
    }

    return productOfAllExceptAtIndex;
  }
}
