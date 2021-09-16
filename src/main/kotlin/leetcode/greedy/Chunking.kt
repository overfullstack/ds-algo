package leetcode.greedy

fun chunking(records: Map<Int, Int>, batchSize: Int): Int {
  val sameChunkIds = mutableListOf<Int>()
  val batchesItr = records.entries.filter { it.value > 200 }.chunked(batchSize).map { batch -> batch.sortedBy { it.value } }.iterator()
  var chunks = 0
  var accumulatedPendingTaxLineCount = 0
  while (true) {
    if (!batchesItr.hasNext()) {
      if (sameChunkIds.isNotEmpty()) {
        chunks++
      }
      return chunks
    }
    for (batch in batchesItr.next()) {
      val currentCount = batch.value
      accumulatedPendingTaxLineCount += currentCount
      if (accumulatedPendingTaxLineCount <= 500) {
        sameChunkIds.add(batch.key)
      } else {
        if (sameChunkIds.isNotEmpty()) {
          chunks++
        }
        accumulatedPendingTaxLineCount = currentCount
        sameChunkIds.clear()
        sameChunkIds.add(batch.key)
      }
    }
    if (accumulatedPendingTaxLineCount > 500) {
      chunks++
      sameChunkIds.clear()
      accumulatedPendingTaxLineCount = 0
    }
  }
}
