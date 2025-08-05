package leetcode.heap

import java.util.PriorityQueue

/* 04 Aug 2025 21:02 */

class StockPriceFluctuation {
  val minHeap = PriorityQueue(compareBy<Pair<Int, Int>> { it.second })
  val maxHeap = PriorityQueue(compareByDescending<Pair<Int, Int>> { it.second })
  val ledger = mutableMapOf<Int, Int>()
  var currentTimeStamp = 0

  fun update(timestamp: Int, price: Int) {
    ledger[timestamp] = price
    currentTimeStamp = maxOf(currentTimeStamp, timestamp)
    minHeap.add(timestamp to price)
    maxHeap.add(timestamp to price)
  }

  fun current(): Int = ledger[currentTimeStamp]!!

  fun maximum(): Int {
    while (true) { // * Prune wrong timestamps, comparing with the ledger
      val (maxTimestamp, maxPrice) = maxHeap.peek()
      if (ledger[maxTimestamp] == maxPrice) {
        return maxPrice
      }
      maxHeap.poll()
    }
  }

  fun minimum(): Int {
    while (true) {
      val (minTimestamp, minPrice) = minHeap.peek()
      if (ledger[minTimestamp] == minPrice) {
        return minPrice
      }
      minHeap.poll()
    }
  }
}
