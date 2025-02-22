package leetcode.greedy

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import kotlin.collections.indices
import kotlin.random.Random

class ChunkingTest :
  StringSpec({
    "Chunking Test" {
      val ctx1 = listOf(250, 600, 400).toContext()
      val result1 = ctx1.values.map { it.groupNumber }.distinct().count()
      
      val ctx2 = mutableMapOf(Id(1) to ContextRecord(250, 1, null), Id(2) to ContextRecord(250, 1, null), Id(3) to ContextRecord(250, 1, null), Id(4) to ContextRecord(250, 2, null))
      forAll(
        row(ctx1, result1),
        row(listOf(250, 210).toContext(), 1),
        row(ctx2, 2),
      ) { context, result ->
        greedyChunking(context) shouldBe result
      }
    }
  })

internal fun List<Int>.toContext(): Context =
  withIndex().associate { Id(it.index) to ContextRecord(it.value, indices.random(), null) }.toMutableMap()
