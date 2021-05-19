package challenges;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Excel {
  private Cell[][] form;
  private Stack<int[]> stack = new Stack<>();

  public Excel(int H, char W) {
    form = new Cell[H][W - 'A' + 1];
  }

  public void set(int r, char c, int v) {
    form[r - 1][c - 'A'] = new Cell(v, new HashMap<>());
    topologicalSort(r - 1, c - 'A');
    sumStack();
  }

  public int get(int r, char c) {
    if (form[r - 1][c - 'A'] == null) {
      return 0;
    }
    return form[r - 1][c - 'A'].value;
  }

  public int sum(int r, char c, String[] strs) {
    Map<String, Integer> range = convertToMap(strs);
    int sum = getSum(r - 1, c - 'A', range);
    set(r, c, sum);
    form[r - 1][c - 'A'] = new Cell(sum, range);

    return sum;
  }

  private void topologicalSort(int row, int col) {
    for (int i = 0; i < form.length; i++) {
      for (int j = 0; j < form[0].length; j++) {
        if (form[i][j] != null
            && form[i][j].children.containsKey("" + (char) ('A' + col) + (row + 1))) {
          topologicalSort(i, j);
        }
      }
    }
    stack.push(new int[] {row, col});
  }

  private void sumStack() {
    while (!stack.isEmpty()) {
      int[] c = stack.pop();
      Cell cell = form[c[0]][c[1]];
      if (cell.children.size() > 0) {
        getSum(c[0], c[1], cell.children);
      }
    }
  }

  private int getSum(int row, int col, Map<String, Integer> children) {
    int sum = 0;
    for (String s : children.keySet()) {
      int i = Integer.parseInt(s.substring(1)) - 1;
      int j = s.charAt(0) - 'A';
      sum += (form[i][j] == null ? 0 : form[i][j].value) * children.get(s);
    }
    form[row][col] = new Cell(sum, children);

    return sum;
  }

  private Map<String, Integer> convertToMap(String[] strs) {
    Map<String, Integer> map = new HashMap<>();
    for (String s : strs) {
      if (s.indexOf(":") < 0) {
        map.put(s, map.getOrDefault(s, 0) + 1);
      } else {
        String[] range = s.split(":");
        int i1 = Integer.parseInt(range[0].substring(1));
        int i2 = Integer.parseInt(range[1].substring(1));
        char j1 = range[0].charAt(0);
        char j2 = range[1].charAt(0);

        for (int i = i1; i <= i2; i++) {
          for (char j = j1; j <= j2; j++) {
            String key = "" + j + i;
            map.put(key, map.getOrDefault(key, 0) + 1);
          }
        }
      }
    }
    return map;
  }

  class Cell {
    int value;
    Map<String, Integer> children;

    Cell(int value, Map<String, Integer> children) {
      this.value = value;
      this.children = children;
    }
  }
}

/**
 * Your Excel object will be instantiated and called as such: Excel obj = new Excel(H, W);
 * obj.set(r,c,v); int param_2 = obj.get(r,c); int param_3 = obj.sum(r,c,strs);
 */
