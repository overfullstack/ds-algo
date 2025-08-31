package practice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * [841. Keys and Rooms](https://leetcode.com/problems/keys-and-rooms/)
 */
public class KeysAndRooms {
  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    var visited = new HashSet<Integer>();
    dfs(0, rooms, visited); // all the rooms are locked except for room 0
    return visited.size() == rooms.size();
  }

  // ! We can't do similar to CourseSchedule as it has cases like `[[1],[1]]`
  private static void dfs(int node, List<List<Integer>> rooms, Set<Integer> visited) {
    visited.add(node);
    rooms.get(node).stream().filter(v -> !visited.contains(v)).forEach(v -> dfs(v, rooms, visited));
  }

  static void main() {
    List<List<Integer>> rooms = List.of(List.of(1), List.of(2), List.of(3), List.of());
    var keysAndRooms = new KeysAndRooms();
    System.out.println(keysAndRooms.canVisitAllRooms(rooms));
    rooms = List.of(List.of(1, 3), List.of(3, 0, 1), List.of(2), List.of(0));
    System.out.println(keysAndRooms.canVisitAllRooms(rooms));
    System.out.println(keysAndRooms.canVisitAllRooms(List.of(List.of(1), List.of(1))));
  }
}
