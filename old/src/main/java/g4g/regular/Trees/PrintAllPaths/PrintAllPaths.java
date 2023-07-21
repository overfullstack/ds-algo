package g4g.regular.Trees.PrintAllPaths;

import static g4g.ds.Utils.treeDepth;

import g4g.ds.TreeNode;
import java.util.Arrays;

/** Created by gakshintala on 6/10/16. */
public class PrintAllPaths {
  public static void main(String[] args) {
    var treeNode6 = new TreeNode(6, null, null);
    var treeNode5 = new TreeNode(5, null, null);
    var treeNode4 = new TreeNode(4, null, null);
    var treeNode3 = new TreeNode(3, treeNode6, null);
    var treeNode2 = new TreeNode(2, treeNode4, treeNode5);
    var treeNode1 = new TreeNode(1, treeNode2, treeNode3);

    var path = new TreeNode[treeDepth(treeNode1)];
    printPath(treeNode1, 0, path);
  }

  // Pre-order traversal
  private static void printPath(TreeNode root, int level, TreeNode[] path) {
    path[level] = root;

    if (root == null || (root.left == null && root.right == null)) {
      System.out.println(Arrays.toString(path));
      path[level] = null; // Like erasing these elements for backtracking
      return;
    }

    level++;

    printPath(root.left, level, path);
    printPath(root.right, level, path);
  }
}
