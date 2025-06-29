package leetcode.greedy

private const val MAX_CHUNK_WEIGHT_PER_JOB_ITEM = 500 // TBD after perf-testing
private const val QUERY_GROUP_COUNT = 10 // TBD after perf-testing

data class ContextRecord(val weight: Int, val groupNumber: Int, val chunkNumber: Int?)

@JvmInline value class Id(val id: Int)

typealias Context = MutableMap<Id, ContextRecord>

fun greedyChunking(ctx: Context): Int {
  var chunkIndex = 0
  var chunkWeight = 0
  val idsInChunk = mutableListOf<Id>()
  var queryStartGroupNumber = 0
  while (true) { // Coz you don't know when the query drys-up
    val ctxGroups = ctx.queryNext(queryStartGroupNumber..queryStartGroupNumber + QUERY_GROUP_COUNT)
    // Query dried-up
    if (ctxGroups.isEmpty()) {
      // Stamp the final Chunk
      if (idsInChunk.isNotEmpty()) {
        chunkIndex++
        ctx.stampChunkNumber(idsInChunk, chunkIndex)
      }
      return chunkIndex
    }

    for (ctxGroup in ctxGroups) {
      val groupWeight = ctxGroup.values.sumOf { it.weight }
      chunkWeight += groupWeight

      // We allow more than `MAX_CHUNK_WEIGHT_PER_JOB_ITEM` to accomodate large Bundles
      if (chunkWeight > MAX_CHUNK_WEIGHT_PER_JOB_ITEM) {
        // Full! Stamp the current Chunk
        if (idsInChunk.isNotEmpty()) {
          chunkIndex++
          ctx.stampChunkNumber(idsInChunk, chunkIndex)
        }
        // And start a New Chunk
        chunkWeight = groupWeight
      }
      idsInChunk.addAll(ctxGroup.keys)
    }
    // Stamping with a temp chunkNumber to not qualify for `queryNext()` in the next iteration
    ctx.stampChunkNumber(idsInChunk, -1)
    queryStartGroupNumber += QUERY_GROUP_COUNT
  }
}

private fun Context.queryNext(groupRange: IntRange): List<Map<Id, ContextRecord>> =
  entries
    .asSequence()
    .filter { (_, ctxRec) -> ctxRec.chunkNumber == null && ctxRec.groupNumber in groupRange }
    .groupBy { (_, ctxRec) -> ctxRec.groupNumber }
    .values
    .map { it.associate { it.toPair() } }
    .sortedByDescending { it.values.sumOf { it.weight } }

private fun Context.stampChunkNumber(ids: List<Id>, chunkNumber: Int) =
  ids.forEach {
    computeIfPresent(it) { _, contextRecord -> contextRecord.copy(chunkNumber = chunkNumber) }
  }
