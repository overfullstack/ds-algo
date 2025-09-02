package practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/* 01 Sep 2025 17:39 */

/**
 * [1862 · Time to Flower Tree](https://www.lintcode.com/problem/1862/)
 */
public class TimeToFlowerTree {
  public int timeToFlowerTree(int[] father, int[] time) {
    var graph = new HashMap<Integer, List<int[]>>();
    for (var i = 1; i < father.length; i++) {
      graph.computeIfAbsent(father[i], _ -> new ArrayList<>()).add(new int[]{i, time[i]});
    }
    var pq = new PriorityQueue<>(Comparator.<int[]>comparingInt(f -> f[1]));
    pq.add(new int[]{0, 0});
    var visited = new boolean[father.length];
    var result = 0;
    while (!pq.isEmpty()) {
      var node = pq.poll();
      var flower = node[0];
      var timeFromSource = node[1];
      result = timeFromSource;
      if (!visited[flower]) {
        visited[flower] = true;
        var children = graph.get(flower);
        if (children != null && !children.isEmpty()) {
          for (var child : children) {
            pq.add(new int[]{child[0], child[1] + timeFromSource});
          }
        }
      }
    }
    return result;
  }

  static void main() {
    var solution = new TimeToFlowerTree();
    System.out.println(solution.timeToFlowerTree(new int[]{-1, 0, 0}, new int[]{-1, 3, 5}));
  }
}
