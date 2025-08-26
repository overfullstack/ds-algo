package practice;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/* 26 Aug 2025 18:49 */

/**
 * [721. Accounts Merge](https://leetcode.com/problems/accounts-merge)
 */
public class AccountsMerge {

  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    var emailToAccountId = new HashMap<String, Integer>();
    var uf = new UnionFind(accounts.size());
    for (var accountId = 0; accountId < accounts.size(); accountId++) {
      var account = accounts.get(accountId);
      for (var j = 1; j < account.size(); j++) {
        var email = account.get(j);
        if (emailToAccountId.containsKey(email)) {
          var prevAccountId = emailToAccountId.get(email);
          if (!accounts.get(prevAccountId).getFirst().equals(account.getFirst())) {
            return emptyList();
          }
          uf.union(prevAccountId, accountId);
        }
        emailToAccountId.put(email, accountId);
      }
    }
    
    var mergedAccounts = new HashMap<Integer, List<String>>();
    for (var entry: emailToAccountId.entrySet()) {
      mergedAccounts.computeIfAbsent(uf.find(entry.getValue()), (_ -> new ArrayList<>())).add(entry.getKey());
    }
    
    return mergedAccounts.entrySet().stream().map(entry ->
        Stream.concat(Stream.of(accounts.get(entry.getKey()).getFirst()), entry.getValue().stream().sorted()).toList()
    ).toList();
  }
  
  static class UnionFind {
    final int size;
    int[] roots;
    int[] ranks;
    
    UnionFind(int size) {
      this.size = size;
      roots = new int[size];
      for (int i = 0; i < size; i++) {
        roots[i] = i;
      }
      ranks = new int[size];
    }

    int find(int n) {
      while (roots[n] != n) {
        n = roots[n];
      }
      return n;
    }

    void union(int n1, int n2) {
      var root1 = find(n1);
      var root2 = find(n2);
      if (root1 != root2) {
        if (ranks[root1] > ranks[root2]) {
          roots[root2] = root1;
        } else if (ranks[root2] > ranks[root1]) {
          roots[root1] = root2;
        } else {
          roots[root1] = root2;
          ranks[root2]++;
        }
      }
    }
  }
}
