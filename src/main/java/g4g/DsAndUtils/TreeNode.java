package g4g.DsAndUtils;

/** Created by gakshintala on 3/22/16. */
public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;
  public int level;

  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  public TreeNode(int val, TreeNode left, TreeNode right, int level) {
    this(val, left, right);
    this.level = level;
  }

  public TreeNode(int val) {
    this.val = val;
  }

  public TreeNode(int val, int level) {
    this.val = val;
    this.level = level;
  }

  @Override
  public String toString() {
    return String.valueOf(this.val);
  }

  public void setLeft(TreeNode left) {
    this.left = left;
  }

  public void setRight(TreeNode right) {
    this.right = right;
  }

  public TreeNode left() {
    return left;
  }

  public TreeNode right() {
    return right;
  }
}
