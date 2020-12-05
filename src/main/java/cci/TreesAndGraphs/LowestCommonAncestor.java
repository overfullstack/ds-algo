package cci.TreesAndGraphs;

import cci.DS.TreeNode;

/**
 * Created by gakshintala on 3/22/16.
 */
public class LowestCommonAncestor {
    public static void main(String[] args) {
        var treeNode1 = new TreeNode(1, null, null);
        var treeNode3 = new TreeNode(3, null, null);
        var root = new TreeNode(2, treeNode1, treeNode3);
        System.out.println(commonAncestorOptimized(root, treeNode1, treeNode3));
    }

    private static TreeNode commonAncestorOptimized(TreeNode root, TreeNode p, TreeNode q) {
        // Top-down - Initially the search is to find either p or q
        if (root == null || root == p || root == q) {
            return root; // Found p or q
        }

        var x = commonAncestorOptimized(root.left, p, q);
        if (x != null && x != p && x != q) { // This fails when in trace back, after p or q is found. This avoids spawning right when common ancestor is found.
            return x; // Which means it originated from * Found Ancestor *
        }
        // Once you find p, you search the right side of the tree for q,
        // if q not found, this below call returns null, and the entire computation returns x as per `return x == null ? y : x;`
        var y = commonAncestorOptimized(root.right, p, q);
        if (y != null && y != p && y != q) {
            return y; // Which means it originated from * Found Ancestor *
        }

        if (x != null && y != null) {
            return root; // * Found Ancestor * This is where we return something other than p or q
        }

        return x == null ? y : x; // This covers both cases 1. If either is null, return non-null 2. If both are null, return Null
    }

    private static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        var isPOnLeft = isOnLeft(root.left, p);
        var isQOnLeft = isOnLeft(root.left, q);

        if (isPOnLeft != isQOnLeft) return root;

        return isPOnLeft ? commonAncestor(p.left, p, q) : commonAncestor(p.right, p, q);
    }

    private static boolean isOnLeft(TreeNode left, TreeNode p) {
        if (left == null) return false;
        if (p == left) return true;

        return isOnLeft(left.left, p) || isOnLeft(left.right, p);
    }
}
