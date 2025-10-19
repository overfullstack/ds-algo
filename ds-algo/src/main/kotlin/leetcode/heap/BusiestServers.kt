package leetcode.heap

import java.util.PriorityQueue
import java.util.TreeSet

/* 03 Aug 2025 13:43 */

/**
 * [1606. Find Servers That Handled Most Number of
 * Requests](https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/)
 */
fun busiestServers(k: Int, arrival: IntArray, load: IntArray): List<Int> {
  val busyServers = PriorityQueue(Comparator.comparingLong<Pair<Long, Int>> { it.first })
  // ! Using TreeSet instead of minHeap, due to the constraint `preferredServer = requestIndex % k`
  val availableServers = TreeSet((0 until k).toList())
  val serverToRequestCount = IntArray(k)

  for ((requestIndex, request) in arrival.zip(load).withIndex()) {
    val (start, duration) = request
    val end = start + duration

    // ! Free up servers that are no longer busy
    while (busyServers.isNotEmpty() && busyServers.peek().first <= start) {
      availableServers.add(busyServers.poll().second)
    }

    if (availableServers.isNotEmpty()) {
      val preferredServer = requestIndex % k
      val availableServerId = availableServers.ceiling(preferredServer) ?: availableServers.first()

      availableServers.remove(availableServerId)
      busyServers.add(end.toLong() to availableServerId)
      serverToRequestCount[availableServerId]++
    }
  }

  val maxRequests = serverToRequestCount.maxOrNull() ?: 0
  return serverToRequestCount.indices.filter { serverToRequestCount[it] == maxRequests }
}

fun main() {
  // Test case 1: k=3, arrival=[1,2,3,4,5], load=[5,2,3,3,3], expected=[1]
  val result1 = busiestServers(3, intArrayOf(1, 2, 3, 4, 5), intArrayOf(5, 2, 3, 3, 3))
  println("Test 1 - Expected: [1], Got: $result1")

  // Test case 2: k=3, arrival=[1,2,3,4], load=[1,2,1,2], expected=[0]
  val result2 = busiestServers(3, intArrayOf(1, 2, 3, 4), intArrayOf(1, 2, 1, 2))
  println("Test 2 - Expected: [0], Got: $result2")

  // Test case 3: k=3, arrival=[1,2,3], load=[10,12,11], expected=[0,1,2]
  val result3 = busiestServers(3, intArrayOf(1, 2, 3), intArrayOf(10, 12, 11))
  println("Test 3 - Expected: [0, 1, 2], Got: $result3")
}
