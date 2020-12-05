package icake.Trees;


import cci.DS.TreeNode;

/**
 * Created by gakshintala on 4/19/16.
 */
public class SecondLargestElement {
    public static void main(String[] args) {
        var treeNode8 = new TreeNode(8, null, null);
        var treeNode7 = new TreeNode(7, null, null);
        var treeNode6 = new TreeNode(6, null, null);
        var treeNode5 = new TreeNode(5, null, null);
        var treeNode4 = new TreeNode(4, treeNode8, null);
        var treeNode3 = new TreeNode(3, treeNode6, treeNode7);
        var treeNode2 = new TreeNode(2, treeNode4, treeNode5);
        var treeNode1 = new TreeNode(1, treeNode2, treeNode3);

        System.out.println(secondLargest(treeNode1));
    }

    private static int secondLargest(TreeNode root) {
        if (root.right == null || root.left == null) {
            throw new IllegalArgumentException("Should have minimum 2 nodes");
        }

        while (true) {
            // No right child, then rightmost of left tree
            if (root.right == null && root.left != null) {
                return findRightMost(root.left);
            }

            // If no children for right child, return right child's parent i.e current node
            if (root.right != null && (root.right.right == null && root.right.left == null)) {
                return root.data;
            }

            // If right child has children, Second largest can only occur on right side, so navigate right
            root = root.right;
        }
    }

    private static int findRightMost(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root.data;
    }
}
