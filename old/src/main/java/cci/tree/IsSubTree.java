package cci.tree;

import ga.overfullstack.ds.tree.TreeNode;

/** Created by gakshintala on 3/29/16. */
public class IsSubTree {
  public static void main(String[] args) {
    var treeNode8 = new TreeNode(8, null, null);
    var treeNode7 = new TreeNode(7, null, null);
    var treeNode6 = new TreeNode(6, null, null);
    var treeNode5 = new TreeNode(5, null, null);
    var treeNode4 = new TreeNode(4, treeNode8, null);
    var treeNode3 = new TreeNode(3, treeNode6, treeNode7);
    var treeNode2 = new TreeNode(2, treeNode4, treeNode5);
    var treeNode1 = new TreeNode(1, treeNode2, treeNode3);

    var t5 = new TreeNode(5, null, null);
    var t4 = new TreeNode(4, null, null);
    var t2 = new TreeNode(2, t5, t4);
    System.out.println(isSubTree(treeNode1, t2));
  }

  private static boolean isSubTree(TreeNode pt, TreeNode st) {
    if (pt == null)
      return false; // Since in this method we are navigating through pt if data doesn't match

    if (pt.value == st.value) {
      // it is kept in if rather than returning directly coz, even if one case fails to be a subtree
      // in the middle,
      // rest validation should continue.
      if (isAllChildrenSame(pt, st)) {
        return true;
      }
    }

    return isSubTree(pt.left, st) || isSubTree(pt.right, st);
  }

  private static boolean isAllChildrenSame(TreeNode pt, TreeNode st) {
    if (pt == null && st == null) return true;

    if (st == null || pt == null) return false; // This is same as below
    // if (st != null && pt == null) return false;
    // if (st == null) return true;

    if (pt.value != st.value) {
      return false;
    }

    return isAllChildrenSame(pt.left, st.left) && isAllChildrenSame(pt.right, st.right);
  }
}
