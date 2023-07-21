package g4g.regular.Trees.BSTFromPreOrder;

import static g4g.ds.Utils.treeInorder;

import g4g.ds.TreeNode;
import java.util.Stack;

/** Created by gakshintala on 6/12/16. */
public class BSTFromPreOrder {
  public static void main(String[] args) {
    int pre[] = {10, 5, 1, 7, 40, 50};
    treeInorder(bstFromPreOrderRecursive(pre, Integer.MIN_VALUE, Integer.MAX_VALUE, new Index(0)));
    System.out.println();
    treeInorder(bstFromPreOrderIterative(pre));
  }

  private static TreeNode bstFromPreOrderIterative(int[] pre) {
    var stk = new Stack<TreeNode>();
    var root = new TreeNode(pre[0]);
    stk.push(root);
    TreeNode nextRoot = null;
    for (var i = 1; i < pre.length; i++) {
      var node = new TreeNode(pre[i]);
      // Left nodes are made as and when a lesser is about to be pushed
      if (pre[i] < stk.peek().val) {
        stk.peek().left = node;
      } else { // right nodes are made if a value greater than current peek found. we need to pop
        // till we find
        // its root to attach it as right child
        while (!stk.isEmpty() && pre[i] > stk.peek().val) {
          nextRoot = stk.pop();
        }
        nextRoot.right = node;
      }
      stk.push(node);
    }
    return root;
  }

  private static TreeNode bstFromPreOrderRecursive(
      int[] pre, int minValue, int maxValue, Index index) {
    if (index.index < pre.length) {
      var val = pre[index.index];
      // Recursive calls are made for left right, only in the valid call where val falls in range
      if (val >= minValue && val < maxValue) {
        var root = new TreeNode(val);
        // This index takes the array traversal forward, array goes forward only if the val falls in
        // range
        // The index values stays common through all recursions.
        index.index++;
        root.left = bstFromPreOrderRecursive(pre, minValue, val, index);
        root.right = bstFromPreOrderRecursive(pre, val, maxValue, index);
        return root;
      }
    }
    return null;
  }
}

class Index {
  public int index;

  public Index(int index) {
    this.index = index;
  }
}
