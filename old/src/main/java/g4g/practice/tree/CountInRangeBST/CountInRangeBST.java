package g4g.practice.tree.CountInRangeBST;

import static g4g.ds.Utils.insertNodeIntoTree;

import g4g.ds.TreeNode;
import java.util.Scanner;

public class CountInRangeBST {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var testCases = scn.nextInt();
    while (testCases-- > 0) {
      var len = scn.nextInt();
      var root = readTree(scn, len);
      var low = scn.nextInt();
      var high = scn.nextInt();
      System.out.println(getCount(root, low, high));
    }
  }

  private static int getCount(TreeNode root, int low, int high) {
    if (root == null) {
      return 0;
    }

    if (isInRange(root.val, low, high)) {
      return 1 + getCount(root.left, low, high) + getCount(root.right, low, high);
    }

    if (root.val < low) {
      return getCount(root.right, low, high);
    }

    return getCount(root.left, low, high);
  }

  private static boolean isInRange(int val, int low, int high) {
    return val >= low && val <= high;
  }

  private static TreeNode readTree(Scanner scn, int len) {
    var root = new TreeNode(scn.nextInt());
    for (var i = 0; i < len - 1; i++) {
      insertNodeIntoTree(root, scn.nextInt());
    }
    return root;
  }
}
