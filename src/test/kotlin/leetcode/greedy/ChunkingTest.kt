package leetcode.greedy

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import kotlin.random.Random

class ChunkingTest : StringSpec({
  "Chunking Test" {
    forAll(
      row(listOf(250, 150, 600, 200, 400, 100).toMap(), 1, 3),
      row(listOf(250, 150, 600, 200, 400, 100).toMap(), 7, 3),
      row(listOf(250, 150, 600, 200, 400, 100).toMap(), null, 3),
      row(listOf(250).toMap(), null, 1),
      row(listOf(250, 250, 250, 250).toMap(), 2, 2),
      row(listOf(250, 250, 250, 250, 250).toMap(), null, 3),
      row(listOf(250, 210).toMap(), null, 1),
      row(listOf(600).toMap(), null,1),
      row(listOf(250).toMap(), null,1),
      row(listOf(200, 100, 150, 100).toMap(), null, 0),
      row(emptyMap(), null, 0),
    ) { map, batchSize, result ->
      val until = if (map.size > 1) map.size else 2
      val batchSizeToDealWith = batchSize ?: Random.nextInt(1, until)
      chunking(map, batchSizeToDealWith) shouldBe result
    }
  }
})

fun List<Int>.toMap() = this.withIndex().associate { it.index to it.value }
