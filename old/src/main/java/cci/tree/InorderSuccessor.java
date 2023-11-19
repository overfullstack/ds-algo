package cci.tree;

import ga.overfullstack.ds.tree.TreeNode;

/** Created by gakshintala on 3/30/16. */
public class InorderSuccessor {
  public static void main(String[] args) {
    TreeNode t1, t2, t3, t4, t5, t6, t7, t8;
    t8 = new TreeNode(8, null, null);
    t7 = new TreeNode(7, null, null);
    t6 = new TreeNode(6, null, null);
    t5 = new TreeNode(5, null, null);
    t4 = new TreeNode(4, t8, null);
    t3 = new TreeNode(3, t6, t7);
    t2 = new TreeNode(2, t4, t5);
    t1 = new TreeNode(1, t2, t3, null);

    t2.parent = t3.parent = t1;
    t4.parent = t5.parent = t2;
    t6.parent = t7.parent = t3;
    t8.parent = t4;

    System.out.println(findNextInorderSuccessor(t7));
    System.out.println(findNexInorderSuccessorWithoutParent(t1, t7));
  }

  private static TreeNode findNextInorderSuccessor(TreeNode root) {
    if (root == null) {
      return null; // Can happen when we run into root parent, in case we are checking successor for
      // rightmost
      // node in the tree
    }
    // Normally Inorder successor shall be right node, so emphasis is on checking the right side
    if (root.right != null) {
      return leftMostNode(root.right);
    }

    // Return the nearest parent for which the subtree containing this node is on left
    // This indicates the subtree in which the node is present in on the left of the parent
    var x = root;
    var p = root.parent;
    while (p != null && p.left != x) {
      x = p;
      p = p.parent;
    }
    return p;
  }

  private static TreeNode leftMostNode(TreeNode root) {
    if (root.left == null) return root;
    return leftMostNode(root.left);
  }

  /** This only applies to BST. */
  private static TreeNode findNexInorderSuccessorWithoutParent(
      TreeNode root, TreeNode nodeForSuccessor) {
    if (nodeForSuccessor.right != null) {
      return leftMostNode(nodeForSuccessor.right);
    }
    TreeNode successor = null;
    while (root != null) { // Searching from the top, keeping reference to parent
      if (nodeForSuccessor.value < root.value) {
        successor = root;
        root = root.left;
      } else if (nodeForSuccessor.value
          > root.value) { // Stop updating successor when taking right turn
        root = root.right;
      } else { // Found
        break;
      }
    }
    return successor;
  }
}
