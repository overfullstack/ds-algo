package educative;

import java.util.*;


class TreeNode<T> {
  T data;
  TreeNode<T> left;
  TreeNode<T> right;

  TreeNode(T data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }
}

class Print<T>{

    public static<T> int height(TreeNode<T> node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(height(node.left), height(node.right));
	}

    public static<T> void displayNode(String[] output, String[] linkAbove, TreeNode<T> node,
        int level, int p, Character linkChar) {
		if (node == null) {
			return;
		}
		int h = output.length;
		String SP = " ";
		if (p < 0) // Shunt everything non-blank across
		{
			String extra = String.join("", Collections.nCopies(Math.abs(p), " "));
			for (int i = 0; i < output.length; i++) {
				if (!output[i].isEmpty()) {
					output[i] = extra + output[i];
				}
			}
			for (int i = 0; i < linkAbove.length; i++) {
				if (!linkAbove[i].isEmpty()) {
					output[i] = extra + output[i];
				}
			}
		}
		if (level < h - 1)
			p = Math.max(p, output[level + 1].length());
		if (level > 0)
			p = Math.max(p, output[level - 1].length());
		p = Math.max(p, output[level].length());

		// Fill in to left
		if (node.left != null) {
            //System.out.println("here");
			String leftData = SP + node.left.data + SP;
			displayNode(output, linkAbove, node.left, level + 1, p - leftData.length(), 'L');
			p = Math.max(p, output[level + 1].length());
		}

		// Enter this data
		int space = p - output[level].length();
		if (space > 0) {
			output[level] += String.join("", Collections.nCopies(space, " "));
		}

        String nodeData = SP + node.data + SP;
        output[level] += nodeData;

		// Add vertical link above
		space = p + SP.length() - linkAbove[level].length();
		if (space > 0) {
			linkAbove[level] += String.join("", Collections.nCopies(space, " "));
		}
		linkAbove[level] += linkChar.toString();

		// Fill in to right
		if (node.right != null) {
			displayNode(output, linkAbove, node.right, level + 1, output[level].length(), 'R');
		}
	}

	public static<T> void displayTree(TreeNode<T> rootNode) {
		if (rootNode == null) {
			System.out.println("\tnull");
		}
		int h = height(rootNode);
		String[] output = new String[h];
		String[] linkAbove = new String[h];
		for (int i = 0; i < h; i++) {
			output[i] = "";
			linkAbove[i] = "";
		}
		displayNode(output, linkAbove, rootNode, 0, 5, ' ');

		// Create link lines
		for (int i = 1; i < h; i++) {
			for (int j = 0; j < linkAbove[i].length(); j++) {
				if (linkAbove[i].charAt(j) != ' ') {
					int size = output[i - 1].length();
					if (size < j + 1) {
						output[i - 1] += String.join("", Collections.nCopies(j + 1 - size, " "));
					}
					int jj = j;
					if (linkAbove[i].charAt(j) == 'L') {
						while (output[i - 1].charAt(jj) == ' ')
							jj++;
						for (int k = j + 1; k < jj - 1; k++) {
							StringBuilder tmp = new StringBuilder(output[i - 1]);
							tmp.setCharAt(k, '_');
							output[i - 1] = tmp.toString();
						}
					} else if (linkAbove[i].charAt(j) == 'R') {
						while (output[i - 1].charAt(jj) == ' ')
							jj--;
						for (int k = j - 1; k > jj + 1; k--) {
							StringBuilder tmp = new StringBuilder(output[i - 1]);
							tmp.setCharAt(k, '_');
							output[i - 1] = tmp.toString();
						}
					}
					StringBuilder tmpWhile = new StringBuilder(linkAbove[i]);
					tmpWhile.setCharAt(j, '|');
					linkAbove[i] = tmpWhile.toString();
				}
			}
		}

		// Output
		for (int i = 0; i < h; i++) {
			if (i != 0) {
				System.out.println("\t" + linkAbove[i]);
			}
			System.out.println("\t" + output[i]);
		}
	}
	
	
}
class BuildTree {
    public static TreeNode<Integer> buildTreeHelper(int[] pOrder, int[] iOrder, int left, int right, HashMap<Integer, Integer> mapping, int[] pIndex) {
        // If left > right, it means there are no more nodes left to construct
        if (left > right) {
            return null;
        }

        // Pick current node from preorder list
        // using pIndex and increment pIndex
        int curr = pOrder[pIndex[0]];
        pIndex[0]++;
        TreeNode<Integer> root = new TreeNode<Integer>(curr);

        // If this node has no children then return
        if (left == right) {
            return root;
        }

        // Else find the index of this node in inorder list
        int inIndex = mapping.get(curr);

        // Recursively build the left subtree by calling buildTreeHelper
        // on the elements in the inorder list from left to inIndex - 1
        root.left = buildTreeHelper(pOrder, iOrder, left, inIndex - 1, mapping, pIndex);

        // Recursively build the right subtree by calling buildTreeHelper
        // on the elements in the inorder list from inIndex + 1 to right
        root.right = buildTreeHelper(pOrder, iOrder, inIndex + 1, right, mapping, pIndex);

        return root;
    }

    public static TreeNode<Integer> buildTree(int[] pOrder, int[] iOrder) {
        // Using HashMap to store the inorder list to reduce time complexity
        // of search to O(1)
        HashMap<Integer, Integer> mapping = new HashMap<Integer, Integer>();

        // Iterate through the inorder list and map each value to its index
        for (int i = 0; i < iOrder.length; i++) {
            mapping.put(iOrder[i], i);
        }

        // Explicitly using an array to pass pIndex by reference because
        // in Java, primitive types are passed by value
        int[] pIndex = {0};

        return buildTreeHelper(pOrder, iOrder, 0, pOrder.length - 1, mapping, pIndex);
    }

     // Driver code
    public static void main(String[] args) {
        int[][] pOrder = {
            {3, 9, 20, 15, 7},
            {-1},
            {10, 20, 40, 50, 30, 60},
            {1, 2, 4, 5, 3, 6},
            {1, 2, 4, 7, 3},
            {1, 2, 4, 8, 9, 5, 3, 6, 7}
        };

        int[][] iOrder = {
            {9, 3, 15, 20, 7},
            {-1},
            {40, 20, 50, 10, 60, 30},
            {4, 2, 5, 1, 6, 3},
            {4, 2, 7, 1, 3},
            {8, 4, 9, 2, 5, 1, 6, 3, 7}
        };

        int index = 0;
        for (int i = 0; i < pOrder.length; i++) {
            System.out.println((index + 1) + ".\tPre order: " + Arrays.toString(pOrder[index]));
            System.out.println("\tIn order: " + Arrays.toString(iOrder[index]));
            TreeNode<Integer> tree = buildTree(pOrder[index], iOrder[index]);
            index++;
            System.out.println("\n\tBinary tree:");
            Print.displayTree(tree);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}

