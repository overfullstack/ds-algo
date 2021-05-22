package g4g.practice.stack.LeadersInArray;

import java.util.Scanner;
import java.util.Stack;

/** Created by gakshintala on 7/3/16. */
public class LeadersInArray {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var len = scn.nextInt();
      var arr = new int[len];
      fillArray(arr, scn);
      printLeader(arr);
    }
  }

  private static void printLeader(int[] arr) {
    var len = arr.length;
    var leader = arr[len - 1];
    var stk = new Stack<Integer>();
    stk.push(leader);
    for (var i = len - 2; i >= 0; i--) {
      if (arr[i] > leader) {
        leader = arr[i];
        stk.push(leader);
      }
    }
    printStack(stk);
  }

  private static void printStack(Stack<Integer> stk) {
    while (!stk.isEmpty()) {
      System.out.print(stk.pop() + " ");
    }
    System.out.println();
  }

  private static void fillArray(int[] arr, Scanner scn) {
    for (var i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
  }
}
