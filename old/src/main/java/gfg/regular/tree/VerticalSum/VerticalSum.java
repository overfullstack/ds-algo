package gfg.regular.tree.VerticalSum;

import static gfg.ds.Utils.printDLL;

import gfg.ds.DLLNode;
import gfg.ds.TreeNode;
import java.util.HashMap;
import java.util.Map;

/** Created by gakshintala on 6/11/16. */
public class VerticalSum {
  public static void main(String[] args) {
    var root = new TreeNode(1);
    root.setLeft(new TreeNode(2));
    root.setRight(new TreeNode(3));
    root.left().setLeft(new TreeNode(4));
    root.left().setRight(new TreeNode(5));
    root.right().setLeft(new TreeNode(6));
    root.right().setRight(new TreeNode(7));

    Map<Integer, Integer> map = new HashMap<>();
    verticalSumWithMap(root, map, 0);
    System.out.println(map.values());

    var dll = new DLLNode(0);
    verticalSumWithDll(root, dll);
    while (dll.prev != null) dll = dll.prev;
    printDLL(dll);
  }

  // This is more space optimized way.
  private static void verticalSumWithDll(TreeNode root, DLLNode dll) {
    if (root == null) {
      return;
    }

    dll.val += root.val;

    // Creation of new nodes if required, and recur
    if (root.left != null && dll.prev == null) {
      dll.prev = new DLLNode(0);
      dll.prev.next = dll;
    }

    if (root.right != null && dll.next == null) {
      dll.next = new DLLNode(0);
      dll.next.prev = dll;
    }

    // Put the sum in nextRight node if moving right or put in prev if moving left
    verticalSumWithDll(root.left, dll.prev);
    verticalSumWithDll(root.right, dll.next);
  }

  private static void verticalSumWithMap(
      TreeNode root, Map<Integer, Integer> map, int horizontalDistance) {
    if (root == null) {
      return;
    }
    int prevSum = map.getOrDefault(horizontalDistance, 0);
    map.put(horizontalDistance, prevSum + root.val);

    // Decrease horizontal distance as we move left and increase as we move right
    verticalSumWithMap(root.left, map, horizontalDistance - 1);
    verticalSumWithMap(root.right, map, horizontalDistance + 1);
  }
}
