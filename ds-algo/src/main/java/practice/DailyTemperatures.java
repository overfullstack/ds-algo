package practice;

import java.util.ArrayDeque;

/* 26 Aug 2025 15:11 */

/**
 * [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures)
 */
public class DailyTemperatures {
  public int[] dailyTemperatures(int[] temperatures) {
    var result = new int[temperatures.length];
    var stk = new ArrayDeque<Integer>();
    for (var i = 0; i < temperatures.length; i++) {
      while (!stk.isEmpty() && temperatures[stk.peek()] < temperatures[i]) {
        result[stk.peek()] = i - stk.pop();
      }
      stk.push(i);
    }
    return result;
  }
}
