package educative.graph.unionfind

/* 05 Sep 2024 16:22 */

fun noOfIslands(grid: Array<IntArray>): Int {
  val unionFind = UnionFind(grid)
  val cols = grid[0].size
  for(row in grid.indices) {
    for(col in grid[0].indices) {
      if (grid[row][col] == 1) {
        grid[row][col] = 0
        val curIndex = row * cols + col
        if (row - 1 >= 0 && grid[row - 1][col] == 1) unionFind.union(curIndex, curIndex - cols)
        if (col - 1 >= 0 && grid[row][col - 1] == 1) unionFind.union(curIndex, curIndex - 1)
        if (row + 1 <= grid.lastIndex && grid[row + 1][col] == 1) unionFind.union(curIndex, curIndex + cols)
        if (col + 1 <= grid[0].lastIndex && grid[row][col + 1] == 1) unionFind.union(curIndex, curIndex + 1)
      }
    }
  }
  return unionFind.countOf1s;
}

class UnionFind {
  var countOf1s = 0
  var roots: Array<Int>
  var ranks: Array<Int>
  constructor(grid: Array<IntArray>) {
    val cols = grid[0].size
    ranks = Array(grid.size * cols) { 1 }
    roots = Array(grid.size * cols) { -1 }
    for(row in grid.indices) {
      for(col in grid[0].indices) {
        if (grid[row][col] == 1) {
          val value = row * cols + col
          roots[value] = value
          countOf1s++
        }
      }
    }
  }
  
  fun find(n: Int): Int {
    var value = n
    while(roots[value] != value) {
      value = roots[value]
    }
    return value
  }
  
  fun union(n1: Int, n2: Int) {
    val root1 = find(n1)
    val root2 = find(n2)
    when {
      ranks[root1] < ranks[root2] -> roots[root1] = root2
      ranks[root1] > ranks[root2] -> roots[root2] = root1
      else -> {
        roots[root1] = root2
        ranks[root2]++
      }
    }
    countOf1s--
  }
}
