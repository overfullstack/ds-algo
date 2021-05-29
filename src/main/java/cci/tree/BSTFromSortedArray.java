package cci.tree;

import cci.ds.TreeNode;

/** Created by gakshintala on 3/30/16. */
public class BSTFromSortedArray {
  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
    var root = arrToBST(arr, 0, arr.length);
  }

  private static TreeNode arrToBST(int[] arr, int start, int end) {
    if (end < start) return null;
    var mid = (start + end) / 2;
    // Start with middle and recur for left and right.
    return new TreeNode(arr[mid], arrToBST(arr, start, mid - 1), arrToBST(arr, mid - 1, end));
  }
}
