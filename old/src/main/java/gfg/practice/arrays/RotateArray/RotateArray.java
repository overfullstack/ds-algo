package gfg.practice.arrays.RotateArray;

import java.util.Scanner;

/** Created by gakshintala on 6/22/16. */
public class RotateArray {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var len = scn.nextInt();
      var rotateCount = scn.nextInt();

      var arr = new int[len];
      fillArray(arr, scn);

      rotateArray(arr, rotateCount);
      printArr(arr);
    }
  }

  private static void rotateArray(int[] arr, int rotateCount) {
    var len = arr.length;
    reverseArr(arr, 0, rotateCount - 1);
    reverseArr(arr, rotateCount, len - 1);
    reverseArr(arr, 0, len - 1);
  }

  private static void reverseArr(int[] arr, int start, int end) {
    while (start < end) {
      swap(arr, start, end);
      start++;
      end--;
    }
  }

  private static void printArr(int[] arr) {
    var strb = new StringBuilder();
    for (var e : arr) {
      strb.append(e).append(" ");
    }
    System.out.println(strb);
  }

  private static void fillArray(int[] arr, Scanner scn) {
    for (var i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
  }

  public static void swap(int[] arr, int pos1, int pos2) {
    if (pos1 == pos2) return;
    var temp = arr[pos1];
    arr[pos1] = arr[pos2];
    arr[pos2] = temp;
  }
}
