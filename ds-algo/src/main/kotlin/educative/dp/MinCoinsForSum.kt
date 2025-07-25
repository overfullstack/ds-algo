package educative.dp

/* 24/7/25 17:38 */

fun minCoinsForSum(coins: IntArray, sum: Int): Int {
  if (sum == 0) return 0
  if (sum < 0 || coins.isEmpty()) return -1

  val table = IntArray(sum + 1) { Int.MAX_VALUE }
  table[0] = 0
  for (coin in coins) {
    for (i in coin..sum) {
      if (table[i - coin] != Int.MAX_VALUE) { // This means there is no coin to fill the gap
        table[i] = minOf(table[i], table[i - coin] + 1)
      }
    }
  }
  return if (table[sum] == Int.MAX_VALUE) -1 else table[sum]
}

fun minCoinsForSum2(coins: IntArray, sum: Int): Int {
  if (sum == 0) return 0
  if (sum < 0 || coins.isEmpty()) return -1

  val table = IntArray(sum + 1) { Int.MAX_VALUE }
  table[0] = 0
  for (i in 1..sum) {
    for (coin in coins) {
      if (coin <= i && table[i - coin] != Int.MAX_VALUE) {
        table[i] = minOf(table[i], table[i - coin] + 1)
      }
    }
  }
  return if (table[sum] == Int.MAX_VALUE) -1 else table[sum]
}

fun main() {
  println(minCoinsForSum(intArrayOf(2, 3, 4, 6, 8), 23))
}
