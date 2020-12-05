package g4g.Practice.Trees.DiagonalTreeTraversal;

import g4g.DsAndUtils.TreeNode;

import java.util.*;

/**
 * Created by gakshintala on 6/21/16.
 */
public class DiagonalTreeTraversal {
    public static void main(String[] args) {
        var root = new TreeNode(8);

        root.setLeft(new TreeNode(3));
        root.setRight(new TreeNode(10));

        root.left().setLeft(new TreeNode(1));
        root.left().setRight(new TreeNode(6));
        root.right().setRight(new TreeNode(14));

        root.left().right().setLeft(new TreeNode(4));
        root.left().right().setRight(new TreeNode(7));
        root.right().right().setLeft(new TreeNode(13));

        Map<Integer, List<TreeNode>> map = new HashMap<>();
        diagonalTreeTraversal(root, map, 0);
        printDiagonalNodes(map);
    }

    private static void printDiagonalNodes(Map<Integer, List<TreeNode>> map) {
        map.values().stream().flatMap(Collection::stream).forEach(val -> System.out.print(val + " "));
    }

    private static void diagonalTreeTraversal(TreeNode root, Map<Integer, List<TreeNode>> map, int diagonal) {
        if (root == null) {
            return;
        }
        map.computeIfAbsent(diagonal, ignore -> new ArrayList<>()).add(root);
        // Observe right is traversed before left
        diagonalTreeTraversal(root.left, map, diagonal + 1); // A left node falls in adjacent diagonal
        diagonalTreeTraversal(root.right, map, diagonal); // All right nodes fall in same diagonal
    }
}
