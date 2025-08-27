package practice;

/* 27 Aug 2025 16:24 */

public class MaximizeDistanceToClosestPerson {
  public int maxDistToClosest(int[] seats) {
    var startIdx = -1;
    var res = Integer.MIN_VALUE;
    for (var i = 0; i < seats.length; i++) {
      if (seats[i] == 1) {
        res = startIdx < 0 ? i : Math.max(res, (i - startIdx)/2); // div 2, sit in the window middle 
        startIdx = i;
      }
    }
    return Math.max(res, seats.length - 1 - startIdx); // ! For the last window
  }
}
