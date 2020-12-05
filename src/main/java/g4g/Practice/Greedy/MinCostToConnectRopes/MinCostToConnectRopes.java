package g4g.Practice.Greedy.MinCostToConnectRopes;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by gakshintala on 6/20/16.
 */
public class MinCostToConnectRopes {
    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var tests = scn.nextInt();
        while (tests-- > 0) {
            var len = scn.nextInt();
            var arr = new int[len];
            fillArray(arr, scn);
            System.out.println(minCostToConnectRopes(arr));
        }
    }

    private static int minCostToConnectRopes(int[] arr) {
        // The idea is to add up first two smaller numbers, but sorting doesn't work because when we put back the added
        // result back in the list, sorting order changes, so we use a heap
        var heap = new PriorityQueue<Integer>();
        for (var e : arr) {
            heap.add(e);
        }
        var cost = 0;

        while (heap.size() > 1) {
            int e1 = heap.poll();
            int e2 = heap.poll();
            var curCost = e1 + e2;
            cost += curCost;
            heap.add(curCost);
        }

        return cost;
    }

    private static void fillArray(int[] arr, Scanner scn) {
        for (var i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }
}
