package practice;

/* 25 Oct 2025 21:20 */

import static java.lang.IO.println;

/// [860. Lemonade Change](https://leetcode.com/problems/lemonade-change/)
public class LemonadeChange {
  public boolean lemonadeChange(int[] bills) {
    var five = 0;
    var ten = 0;
    for (var bill : bills) {
      switch (bill) {
        case 5 -> five++;
        case 10 -> {
          five--;
          ten++;
        }
        default -> {
          if (ten > 0) {
            ten--;
            five--;
          } else {
            five -= 3;
          }
        }
      }
      if (five < 0) {
        return false;
      }
    }
    return true;
  }

  static void main() {
    var l = new LemonadeChange();
    println(l.lemonadeChange(new int[] {5, 5, 5, 10, 20})); // true
    println(l.lemonadeChange(new int[] {5, 5, 10, 10, 20})); // false
  }
}
