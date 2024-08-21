package gfg.practice.arrays.RainWaterTrap;

import java.util.Scanner;

/** Created by Gopala Akshintala on 8/15/17. */
public class RainWaterTrap {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var len = scn.nextInt();
      var arr = fillArray(len, scn);
      System.out.println(maxRainWaterTrap(arr, len));
    }
  }

  private static int maxRainWaterTrap(int[] arr, int len) {
    var leftMax = new int[len];
    var rightMax = new int[len];

    leftMax[0] = arr[0];
    for (var i = 1; i < len; i++) {
      leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
    }

    rightMax[len - 1] = arr[len - 1];
    for (var i = len - 2; i >= 0; i--) {
      rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
    }

    var water = 0;
    for (var i = 0; i < len; i++) {
      water += Math.min(leftMax[i], rightMax[i]) - arr[i];
    }
    return water;
  }

  private static int[] fillArray(int len, Scanner scn) {
    var arr = new int[len];
    for (var i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
    return arr;
  }
}
