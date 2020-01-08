package cci.TreesAndGraphs;

import cci.DS.TreeNode;

/**
 * Created by gakshintala on 3/29/16.
 */
public class FindPathForSum {
    private static int[] path; // Path with level allows us to check sum with left and then right nodes.
    private static final int SUM_TO_FIND = 6;

    public static void main(String[] args) {
        var treeNode8 = new TreeNode(8, null, null);
        var treeNode7 = new TreeNode(7, null, null);
        var treeNode6 = new TreeNode(6, null, null);
        var treeNode5 = new TreeNode(5, null, null);
        var treeNode4 = new TreeNode(4, treeNode8, null);
        var treeNode3 = new TreeNode(3, treeNode6, treeNode7);
        var treeNode2 = new TreeNode(2, treeNode4, treeNode5);
        var treeNode1 = new TreeNode(1, treeNode2, treeNode3);

        path = new int[depth(treeNode1)];
        findSum(treeNode1, 0);
    }

    private static void findSum(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        path[level] = root.data; // Every node at a level is tested one at a time.

        // Check if adding this node completes the sum
        var s = 0;
        for (var i = level; i >= 0; i--) {
            s += path[i];
            if (s == SUM_TO_FIND) {
                printPath(i, level);
            }
        }
        findSum(root.left, level + 1);
        findSum(root.right, level + 1);
    }

    private static void printPath(int start, int end) {
        System.out.print("Path: ");
        for (var i = start; i <= end; i++)
            System.out.print(path[i] + " ");
        System.out.println();
    }

    private static int depth(TreeNode root) {
        if (root == null) return 0;
        else return 1 + Math.max(depth(root.left), depth(root.right));
    }
}
