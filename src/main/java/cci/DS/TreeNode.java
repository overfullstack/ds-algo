package cci.DS;

/**
 * Created by gakshintala on 3/22/16.
 */
public class TreeNode {
    public int data;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;
    public TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    public TreeNode(int data, TreeNode left, TreeNode right, TreeNode parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return String.valueOf(this.data);
    }

}
