package practice.hashmap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/* 21 Oct 2025 19:54 */

public class MostProfitAssigningWork {
  public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
    var minHeapForDifficulty = new PriorityQueue<>(Comparator.<Integer>comparingInt(i -> difficulty[i]));
    var maxHeapForProfit = new PriorityQueue<>(Comparator.<Integer>comparingInt(i -> profit[i]).reversed());
    for (var i = 0; i < difficulty.length; i++) {
      minHeapForDifficulty.add(i);
    }
    Arrays.sort(worker); // ! Sort
    var maxProfit = 0;
    for (var capacity : worker) {
      while (!minHeapForDifficulty.isEmpty() && capacity >= difficulty[minHeapForDifficulty.peek()]) {
        maxHeapForProfit.add(minHeapForDifficulty.poll());
      }
      if (!maxHeapForProfit.isEmpty()) {
        // ! `peek` as same job can be assigned to multiple workers
        maxProfit += profit[maxHeapForProfit.peek()]; 
      }
    }
    return maxProfit;
  }

  static void main() {
    var mostProfitAssigningWork = new MostProfitAssigningWork();
    System.out.println(mostProfitAssigningWork.maxProfitAssignment(new int[] {2, 4, 6, 8, 10}, new int[] {10, 20, 30, 40, 50}, new int[] {4, 5, 6, 7})); // 100
    System.out.println(mostProfitAssigningWork.maxProfitAssignment(new int[] {85, 47, 57}, new int[] {24, 66, 99}, new int[] {40, 25, 25})); // 0
    System.out.println(mostProfitAssigningWork.maxProfitAssignment(new int[] {64, 88, 97}, new int[] {53, 86, 89}, new int[] {98, 11, 6})); // 89
  }
}
