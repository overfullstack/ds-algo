package hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/** Created by gakshintala on 12/21/15. */
public class JourneyToMoon {
  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var n = scn.nextInt();
    var quickFind = new QuickFind(n);

    var pairs = scn.nextInt();
    while (pairs-- > 0) quickFind.union(scn.nextInt(), scn.nextInt());

    var groupCount = quickFind.getAllGroupCounts();

    /*int combinations = 0;
    for(int i=0; i<groupCount.length-1;i++)
        for(int j=i+1;j<groupCount.length;j++)
            combinations+=groupCount[i]*groupCount[j];*/

    var inEligibleCombinations = 0;
    for (var count : groupCount) inEligibleCombinations += getCombinations(count);

    System.out.println(getCombinations(n) - inEligibleCombinations);
  }

  private static long getCombinations(long n) {
    if (n <= 1) return 0;
    return n * (n - 1) / 2;
  }
}

class QuickFind {
  int[] id;

  QuickFind(int n) {
    id = new int[n];
    for (var i = 0; i < n; i++) {
      id[i] = i;
    }
  }

  public void union(int p, int q) {
    var pid = id[p];
    var qid = id[q];
    for (var i = 0; i < this.id.length; i++) {
      if (id[i] == pid) id[i] = qid;
    }
  }

  public int[] getAllGroupCounts() {
    Map<Integer, Integer> idCountMap = new HashMap<>();
    for (var id : this.id) {
      var count = idCountMap.get(id);
      count = (count == null ? 0 : count);
      idCountMap.put(id, count + 1);
    }
    var countArray = new int[idCountMap.size()];
    var i = 0;
    for (int count : idCountMap.values()) countArray[i++] = count;
    return countArray;
  }
}
