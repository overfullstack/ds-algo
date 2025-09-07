package practice.graph.kahn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* 07 Sep 2025 19:09 */

/**
 * [851. Loud and Rich](https://leetcode.com/problems/loud-and-rich)
 */
public class LoudAndRich {
  public int[] loudAndRich(int[][] richer, int[] quiet) {
    var graph = new HashMap<Integer, List<Integer>>();
    var inDegree = new int[quiet.length];
    for (var edge : richer) { // ! rich -> poor
      graph.computeIfAbsent(edge[0], _ -> new ArrayList<>()).add(edge[1]);
      inDegree[edge[1]]++;
    }
    var minQuietIdx = new int[quiet.length];
    var minQuiet= new int[quiet.length];
    var queue = new ArrayDeque<Integer>();
    for (var i = 0; i < inDegree.length; i++) {
      minQuietIdx[i] = i;
      minQuiet[i] = quiet[i];
      if (inDegree[i] == 0) {
        queue.add(i);
      }
    }
    while (!queue.isEmpty()) {
      var richerPerson = queue.poll();
      for (var person : graph.getOrDefault(richerPerson, Collections.emptyList())) {
        // ! You arrive at a person from different richer person parents
        if (minQuiet[richerPerson] < minQuiet[person]) {
          minQuietIdx[person] = minQuietIdx[richerPerson]; // ! Set the corresponding Idx recorded
          minQuiet[person] = minQuiet[richerPerson];
        }
        inDegree[person]--;
        if (inDegree[person] == 0) {
          queue.add(person);
        }
      }
    }
    return minQuietIdx;
  }

  static void main() {
    var loudAndRich = new LoudAndRich();
    System.out.println(Arrays.toString(loudAndRich.loudAndRich(
        new int[][]{{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}},
        new int[]{3, 2, 5, 4, 6, 1, 7, 0}))); // [5,5,2,5,4,5,6,7]
  }
}
