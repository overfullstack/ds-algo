package g4g.Practice.Trees.IsPreOrderBST;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by gakshintala on 6/12/16.
 */
public class IsPreOrderBST {
    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var tests = scn.nextInt();
        while (tests-- > 0) {
            var len = scn.nextInt();
            var preArr = new int[len];
            fillArray(preArr, scn);
            System.out.println(isPreOrderBST(preArr) ? 1 : 0);
        }
    }

    private static boolean isPreOrderBST(int[] preArr) {
        var stk = new Stack<Integer>();
        var root = Integer.MIN_VALUE;
        for (var ele : preArr) {
            // First left tree will be checked with MIN_VALUE, then once right tree is encountered rest of the nodes are
            // checked against the root.
            if (ele < root) { // root remains to be the previous root, until a greater element is found
                return false;
            }

            while (!stk.isEmpty() && ele > stk.peek()) { // if a greater element is found, stack is dug until the root
                root = stk.pop();
            }
            stk.push(ele);
        }
        return true;
    }

    private static void fillArray(int[] arr, Scanner scn) {
        for (var i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }
}

