package ci.BackTracking.StringPathInMatrix;

/** Created by gakshintala on 4/7/16. */
public class StringPathInMatrix {
  public static void main(String[] args) {
    char[][] matrix = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}};
    var stringToFind = "abeh".toCharArray();
    var visited = new boolean[3][3];
    var found = false;
    // Start at every index and search
    outerLoop:
    for (var i = 0; i < 3; i++) {
      for (var j = 0; j < 3; j++) {
        if (find(matrix, stringToFind, i, j, 0, visited)) {
          System.out.println("Found! :)");
          found = true;
          break outerLoop;
        }
      }
    }
    if (!found) System.out.println("Not Found :(");
  }

  private static boolean find(
      char[][] matrix, char[] stringToFind, int i, int j, int pathPos, boolean[][] visited) {
    var hasPath = false;
    if (pathPos == stringToFind.length) {
      return true;
    } else if (isValid(i, j, visited, matrix, stringToFind, pathPos)) {
      pathPos++;
      visited[i][j] = true;
      hasPath =
          find(matrix, stringToFind, i - 1, j, pathPos, visited)
              || find(matrix, stringToFind, i + 1, j, pathPos, visited)
              || find(matrix, stringToFind, i, j - 1, pathPos, visited)
              || find(matrix, stringToFind, i, j + 1, pathPos, visited);

      if (!hasPath) { // No need to decrement pathPos, as it goes back to recursion, it will have
        // the old decremented pathPos itself.
        visited[i][j] = false;
      }
    }

    return hasPath;
  }

  private static boolean isValid(
      int i, int j, boolean[][] visited, char[][] matrix, char[] stringToFind, int path) {
    return i >= 0
        && i < 3
        && j >= 0
        && j < 3
        && !visited[i][j]
        && matrix[i][j] == stringToFind[path];
  }
}
