package leetcode.greedy

fun chunking(records: MutableMap<Int, Pair<Int, Int?>>, batchSize: Int): Int {
  val sameChunkIds = mutableListOf<Int>()
  var chunk = 0
  var accumulationCount = 0
  while (true) {
    val (ids, pendingCounts) = records.queryNextNonStamped(batchSize)

    if (ids.isEmpty() || pendingCounts.isEmpty()) {
      if (sameChunkIds.isNotEmpty()) {
        chunk++
        records.stampWithChunk(sameChunkIds, chunk)
      }
      return chunk
    }

    for (i in ids.indices) {
      val currentCount = pendingCounts[i]
      accumulationCount += currentCount

      if (accumulationCount <= 500) {
        sameChunkIds.add(ids[i])
      } else {
        if (sameChunkIds.isNotEmpty()) {
          chunk++
          records.stampWithChunk(sameChunkIds, chunk)
          sameChunkIds.clear()
        }
        // New accumulation
        accumulationCount = currentCount
        sameChunkIds.add(ids[i])
      }
    }
    records.stampWithChunk(sameChunkIds, -1)
  }
}

fun Map<Int, Pair<Int, Int?>>.queryNextNonStamped(batchSize: Int): Pair<List<Int>, List<Int>> =
  entries.asSequence()
    .filter { it.value.first > 200 && it.value.second == null }
    .take(batchSize)
    .map { it.key to it.value.first }
    .sortedBy { it.second }.unzip()

fun MutableMap<Int, Pair<Int, Int?>>.stampWithChunk(ids: List<Int>, chunkNumber: Int) =
  ids.forEach { stampWithChunk(it, chunkNumber) }

fun MutableMap<Int, Pair<Int, Int?>>.stampWithChunk(id: Int, chunkNumber: Int) =
  this.computeIfPresent(id) { _, value -> value.first to chunkNumber }

fun List<Int>.toMutableMap(): MutableMap<Int, Pair<Int, Int?>> =
  this.withIndex().associate { it.index to (it.value to null) }.toMutableMap()

fun main() {
  val records = listOf(250, 600, 400).toMutableMap()
  println(chunking(records, 7))
  println(records)
}
