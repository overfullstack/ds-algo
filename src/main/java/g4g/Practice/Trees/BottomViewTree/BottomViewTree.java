package g4g.Practice.Trees.BottomViewTree;

import g4g.DsAndUtils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class BottomViewTree {
    public static void main(String[] args) {
        var root = new TreeNode(20);
        root.left = new TreeNode(8);
        root.right = new TreeNode(22);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(25);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(14);

        Map<Integer, TreeNode> map = new HashMap<>();
        constructBottomView(root, map, 0, 0);
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.print(entry.getValue().val + " "));
    }

    private static void constructBottomView(TreeNode root, Map<Integer, TreeNode> map, int horizontalDistance, int height) {
        if (root == null) {
            return;
        }
        // Top-Down
        var node = map.get(horizontalDistance);
        if (node == null || node.level <= height) { // Replace if node not present, or node is of lesser height.
            map.put(horizontalDistance, new TreeNode(root.val, height));
        }

        constructBottomView(root.left, map, horizontalDistance - 1, height + 1);
        constructBottomView(root.right, map, horizontalDistance + 1, height + 1);
    }
}
