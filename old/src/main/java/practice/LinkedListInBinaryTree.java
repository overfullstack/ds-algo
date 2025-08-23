package practice;

import ds.ListNode;
import ds.TreeNode;

/**
 * [1367. Linked List in Binary Tree](https://leetcode.com/problems/linked-list-in-binary-tree/)
 */
public class LinkedListInBinaryTree {
  public boolean isSubPath(ListNode head, TreeNode root) {
    if (head == null) {
      return true;
    }
    if (root == null) {
      return false;
    }
    return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
  }
  
  private boolean dfs(ListNode head, TreeNode root) {
    if (head == null) {
      return true;
    }
    if (root == null) {
      return false;
    }
    return root.val == head.val && (dfs(head.next, root.left) || dfs(head.next, root.right));
  }
}
