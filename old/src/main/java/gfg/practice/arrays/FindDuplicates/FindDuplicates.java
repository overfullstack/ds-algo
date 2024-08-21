package gfg.practice.arrays.FindDuplicates;

import java.util.Scanner;

/** Created by Gopala Akshintala on 20/02/17. */
public class FindDuplicates {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var len = scn.nextInt();
      var arr = fillArray(len, scn);
      // printInAscendingOrder(arr, len);
      // printInSameOrder(arr, len);
      printDuplicates(arr, len);
      System.out.println();
    }
  }

  private static void printDuplicates(int[] arr, int len) {
    var isNoDuplicatesFoundFlag = true;
    for (var i = 0; i < len; i++) {
      if (arr[Math.abs(arr[i])] >= 0) {
        arr[Math.abs(arr[i])] = -arr[Math.abs(arr[i])];
      } else {
        isNoDuplicatesFoundFlag = false;
        System.out.print(Math.abs(arr[i]) + " ");
      }
    }
    if (isNoDuplicatesFoundFlag) {
      System.out.print("-1 ");
    }
  }

  // These methods are useful when you need how many times each one is repeated
  private static void printInAscendingOrder(int[] arr, int len) {
    for (var i = 0; i < len; i++) {
      arr[arr[i] % len] += len;
    }
    var isNoDuplicatesFoundFlag = true;
    for (var i = 0; i < len; i++) {
      if (arr[i] / len > 1) {
        isNoDuplicatesFoundFlag = false;
        System.out.print(i + " ");
      }
    }
    if (isNoDuplicatesFoundFlag) {
      System.out.print("-1 ");
    }
  }

  private static void printInSameOrder(int[] arr, int len) {
    for (var i = 0; i < len; i++) {
      arr[arr[i] % len] += len;
    }
    var isNoDuplicatesFoundFlag = true;
    for (var i = 0; i < len; i++) {
      var index = arr[i] % len;
      if (index >= 0 && arr[index] != -1 && arr[index] / len > 1) {
        isNoDuplicatesFoundFlag = false;
        System.out.print(index + " ");
        arr[index] = -1;
      }
    }
    if (isNoDuplicatesFoundFlag) {
      System.out.print("-1 ");
    }
  }

  private static int[] fillArray(int len, Scanner scn) {
    var arr = new int[len];
    for (var i = 0; i < len; i++) {
      arr[i] = scn.nextInt();
    }
    return arr;
  }
}
