package practice.graph;

/* 05 Sep 2025 20:30 */

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RobotRoomCleaner {
   // This is the robot's control interface.
   // You should not implement it, or speculate about its implementation
   interface Robot {
       // Returns true if the cell in front is open and robot moves into the cell.
       // Returns false if the cell in front is blocked and robot stays in the current cell.
       public boolean move();
   
       // Robot will stay in the same cell after calling turnLeft/turnRight.
       // Each turn will be 90 degrees.
       public void turnLeft();public void turnRight();
   
       // Clean the current cell.
       public void clean();
   }

  private Robot robot;
  private final Set<List<Integer>> visited = new HashSet<>();
  private static final int[] directions = {0, 1, 0, -1, 0};
  
  public void cleanRoom(Robot robot) {
    this.robot = robot;
    dfs(0, 0, 0);
  }
  
  private void dfs(int row, int col, int direction) {
    robot.clean();
    visited.add(List.of(row, col));
    // ! It's difficult to cycle through all the directions, so derive the next direction
    for (var k = 0; k < 4; k++) {
      var nd = (direction + k) % 4;
      var nextRow = row + directions[nd];
      var nextCol = row + directions[nd + 1];
      if (!visited.contains(List.of(nextRow, nextCol)) && robot.move()) {
        dfs(nextRow, nextCol, nd);
        // ! Backtrack
        // ! rotate 180 deg 
        robot.turnLeft();
        robot.turnLeft();
        robot.move(); // ! Come back to previous cell from where you started this dfs
        // ! rotate 180 deg to face the same direction
        robot.turnLeft();
        robot.turnLeft();
      }
      // ! rotate 90 deg to face the next direction
      robot.turnLeft();
    }
  }
}
