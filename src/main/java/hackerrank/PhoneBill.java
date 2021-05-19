package hackerrank; // you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class PhoneBill {
  private static int sumCallDuration(HashMap<String, Integer> phoneBillMap) {
    var sum = 0;
    for (int phoneBill : phoneBillMap.values()) {
      sum += phoneBill;
    }
    return sum;
  }

  private static int calculateBill(int duration) {
    if (duration < 5 * 60) return 3 * duration;
    else {
      if (duration / 60 == 0) return (duration / 60) * 150;
      return (duration / 60 + 1) * 150;
    }
  }

  private static String getHighestDurationNums(HashMap<String, Integer> callDurationMap) {
    var max = Long.MIN_VALUE;
    String highestPhoneNum = null;
    List<String> highestDurationNums = new ArrayList<>();
    for (var phoneNum : callDurationMap.keySet()) {
      long callDuration = callDurationMap.get(phoneNum);
      if (callDuration >= max) {
        max = callDuration;
        highestPhoneNum = phoneNum;
      }
    }
    return highestPhoneNum;
  }

  private static int convertToSeconds(String s) {
    var units = s.split(":");
    var hours = Integer.parseInt(units[0]);
    var minutes = Integer.parseInt(units[1]);
    var seconds = Integer.parseInt(units[2]);

    return hours * 60 * 60 + minutes * 60 + seconds;
  }

  public int solution(String S) {
    var inp = S.split("\\r?\\n");
    var callDurationMap = new HashMap<String, Integer>();
    for (var str : inp) {
      var durationNumPair = str.split(",");
      var currentDuration = callDurationMap.get(durationNumPair[1]);
      callDurationMap.put(
          durationNumPair[1],
          (currentDuration == null)
              ? convertToSeconds(durationNumPair[0])
              : currentDuration + convertToSeconds(durationNumPair[0]));
    }

    var phoneBillMap = new HashMap<String, Integer>();

    var highestDurationNum = getHighestDurationNums(callDurationMap);
    phoneBillMap.put(highestDurationNum, 0);

    for (var phoneNum : callDurationMap.keySet()) {
      if (phoneNum == highestDurationNum) continue;
      phoneBillMap.put(phoneNum, calculateBill(callDurationMap.get(phoneNum)));
    }

    return sumCallDuration(phoneBillMap);
  }
}
