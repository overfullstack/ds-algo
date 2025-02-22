package leetcode.greedy

private const val MAX_CHUNK_WEIGHT_PER_JOB_ITEM = 500 // TBD after perf-testing
typealias DB = MutableMap<Id, Pair<Int, Int?>>
fun greedyChunking2(db: DB, queryCount: Int): Int {
  var chunkIndex = 0
  var chunkWeight = 0
  val idsInChunk = mutableListOf<Id>()
  while (true) { // Coz you don't know when the query drys-up
    val (ids, weights) = db.queryNext(queryCount)
    // Query dried-up
    if (ids.isEmpty() || weights.isEmpty()) {
      // Stamp the final Chunk
      if (idsInChunk.isNotEmpty()) {
        chunkIndex++
        db.stampChunkNumber(idsInChunk, chunkIndex)
      }
      return chunkIndex
    }
    
    for (i in ids.indices) {
      val currentWeight = weights[i]
      chunkWeight += currentWeight

      // We allow more than `MAX_CHUNK_WEIGHT_PER_JOB_ITEM` to accomodate large Bundles
      if (chunkWeight > MAX_CHUNK_WEIGHT_PER_JOB_ITEM) { 
          // Full! Stamp the current Chunk
          if (idsInChunk.isNotEmpty()) {
            chunkIndex++
            db.stampChunkNumber(idsInChunk, chunkIndex)
          }
          // And start a New Chunk
          chunkWeight = currentWeight
      }
      idsInChunk.add(ids[i])
    }
    // Stamping with a temp chunkNumber to not qualify for `queryNext()` in the next iteration
    db.stampChunkNumber(idsInChunk, -1)
  }
}

private fun DB.queryNext(queryCount: Int): Pair<List<Id>, List<Int>> =
  entries
    .asSequence()
    .filter { it.value.first > 200 && it.value.second == null }
    .take(queryCount)
    .map { it.key to it.value.first }
    .sortedByDescending { (_, weight) -> weight }
    .unzip()

private fun DB.stampChunkNumber(ids: List<Id>, chunkNumber: Int) =
  ids.forEach { computeIfPresent(it) { _, value -> value.first to chunkNumber } }
