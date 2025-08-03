package ds;

import java.util.Arrays;

/** Created by gakshintala on 6/10/16. */
public class Utils {

	private Utils() {}

	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	private static void quickSort(int[] arr, int low, int high) {
		var partitionPoint = partition(arr, low, high);

		if (partitionPoint - 1 > low) quickSort(arr, low, partitionPoint - 1);
		if (partitionPoint + 1 < high) quickSort(arr, partitionPoint + 1, high);
	}

	private static int partition(int[] arr, int low, int pivotPos) {
		var right = pivotPos - 1;
		var left = low;
		while (left < right) {
			// Note the <= in left<=right, this is because we are using left to swap at the end
			// Let's say if pivot is the largest element, then left should go till pivotPos so it swaps
			// itself
			while (left <= right && arr[left] < arr[pivotPos]) left++;
			while (right > low && arr[right] > arr[pivotPos]) right--;
			if (left < right) {
				swap(arr, left, right);
			}
		}
		swap(arr, left, pivotPos);
		return left;
	}

	public static void swap(int[] arr, int pos1, int pos2) {
		if (pos1 == pos2) {
			return;
		}
		if (isPosInvalid(pos1, arr) || isPosInvalid(pos2, arr)) {
			throw new IllegalArgumentException("Invalid Positions to Swap: " + pos1 + ", " + pos2);
		}
		var temp = arr[pos1];
		arr[pos1] = arr[pos2];
		arr[pos2] = temp;
	}

	private static boolean isPosInvalid(int pos, int[] arr) {
		return (pos <= 0 || pos >= arr.length);
	}

	public static void printTreeInorder(TreeNode root) {
		if (root == null) return;
		printTreeInorder(root.left);
		System.out.print(root + " ");
		printTreeInorder(root.right);
	}

	public static void printSLL(SLLNode head) {
		while (head != null) {
			System.out.print(head + " ");
			head = head.next;
		}
	}

	public static void printDLL(DLLNode head) {
		while (head != null) {
			System.out.print(head + " -> ");
			head = head.next;
		}
		System.out.println();
	}

	public static int findLLLength(SLLNode head) {
		var count = 0;
		while (head != null) {
			head = head.next;
			count++;
		}
		return count;
	}

	public static int treeDepth(TreeNode root) {
		if (root == null) return 0;
		return Math.max(treeDepth(root.left), treeDepth(root.right)) + 1;
	}

	public static void treeInorder(TreeNode root) {
		if (root == null) return;
		treeInorder(root.left);
		System.out.print(root + " ");
		treeInorder(root.right);
	}

	public static SLLNode constructLL(int... vals) {
		var len = vals.length;
		if (len == 0) {
			return null;
		}
		var sllNode = new SLLNode(vals[0]);
		for (var i = 1; i < len; i++) {
			sllNode.next = new SLLNode(vals[i]);
			sllNode = sllNode.next;
		}
		return sllNode;
	}

	public static int binarySearch(int[] arr, int searchNum) {
		return binarySearch(arr, searchNum, 0, arr.length - 1) + 1;
	}

	private static int binarySearch(int[] arr, int searchNum, int low, int high) {
		if (low <= high) {
			var mid = (low + high) / 2;
			if (arr[mid] == searchNum) {
				return mid;
			}
			if (arr[mid] < searchNum) {
				return binarySearch(arr, searchNum, mid + 1, high);
			}
			return binarySearch(arr, searchNum, low, mid - 1);
		}
		return -1;
	}

	public static void printArray(int[] arr) {
		Arrays.stream(arr).forEach(val -> System.out.print(val + " "));
	}

	public static void insertNodeIntoTree(TreeNode root, int key) {
		if (key < root.val) {
			if (root.left != null) {
				insertNodeIntoTree(root.left, key);
			} else {
				root.left = new TreeNode(key);
			}
		} else if (key > root.val) {
			if (root.right != null) {
				insertNodeIntoTree(root.right, key);
			} else {
				root.right = new TreeNode(key);
			}
		}
	}
}
