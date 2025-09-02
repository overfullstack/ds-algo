package practice;

import java.util.Arrays;

/* 01 Sep 2025 16:05 */

/**
 * [1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance](
 * https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/)
 */
public class FindCityWithSmallestNumberOfNeighborsAtAThresholdDistance {
  // * Floyd-Warshall Algorithm
  public int findTheCity(int n, int[][] edges, int distanceThreshold) {
    var distances = new int[n][n];
    for (var i = 0; i < distances.length; i++) {
      Arrays.fill(distances[i], 10001); // ! large number to prevent int overflow
      distances[i][i] = 0; // ! Self distance as 0
    }
    for (var edge : edges) {
      distances[edge[0]][edge[1]] = distances[edge[1]][edge[0]] = edge[2];
    }
    for (var via = 0; via < n; via++) {
      for (var from = 0; from < n; from++) {
        for (var to = 0; to < n; to++) {
          distances[from][to] = Math.min(
              distances[from][to], distances[from][via] + distances[via][to]);
        }
      }
    }

    var minCount = Integer.MAX_VALUE;
    var resultCity = -1;
    for (var node = 0; node < n; node++) {
      var count = Math.toIntExact(
          Arrays.stream(distances[node]).filter(d -> d <= distanceThreshold).count());
      if (count <= minCount) {
        resultCity = node;
        minCount = count;
      }
    }
    return resultCity;
  }

  static void main() {
    var solution = new FindCityWithSmallestNumberOfNeighborsAtAThresholdDistance();
    int[][] edges = {{0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1}};
    int result = solution.findTheCity(5, edges, 2);
    System.out.println(result);
  }
}
