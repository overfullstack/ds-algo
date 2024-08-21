package gfg.practice.arrays.MinPlatforms;

import java.util.Arrays;
import java.util.Scanner;

/** Created by gakshintala on 6/22/16. */
public class MinPlatforms {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var tests = scn.nextInt();
    while (tests-- > 0) {
      var len = scn.nextInt();
      var arr = new int[len];
      fillArray(arr, scn);

      var dep = new int[len];
      fillArray(dep, scn);

      System.out.println(minPlatforms(arr, dep));
    }
  }

  private static int minPlatforms(int[] arr, int[] dep) {
    Arrays.sort(arr);
    Arrays.sort(dep);
    // Just like Merge sort
    var len = arr.length;
    int arrivals = 0, maxArrivals = 1, i = 0, j = 0; // maxArrivals is at-least 1
    while (i < len && j < len) {
      if (arr[i] < dep[j]) {
        i++;
        arrivals++;
        maxArrivals = Math.max(arrivals, maxArrivals);
      } else {
        j++;
        arrivals--; // Can go negative for departure times for nextRight day which are less than
        // arrival times.
        // It's ok, they get neutralized as we encounter arrival times.
      }
    }
    // No need of this code, cause here the departure time lesser than arrival time is depicted by
    // train leaving
    // next day. So we encounter their departures earlier than their arrival in the sorted array.
    /*if (j == len) {
        maxArrivals += (len - 1 - i);
    }*/
    return maxArrivals;
  }

  private static void fillArray(int[] arr, Scanner scn) {
    for (var i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
  }
}
