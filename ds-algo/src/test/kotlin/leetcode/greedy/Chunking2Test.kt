package leetcode.greedy

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import kotlin.random.Random

class Chunking2Test :
  StringSpec({
    "Chunking 2 Test" {
      forAll(
        row(listOf(250, 600, 400).toDB(), 1, 3),
        row(listOf(250, 210).toDB(), null, 1),
        row(listOf(250, 250, 250, 250).toDB(), 2, 2),
        row(listOf(250, 150, 600, 200, 400, 100).toDB(), 1, 3),
        row(listOf(250, 150, 600, 200, 400, 100).toDB(), 7, 3),
        row(listOf(250, 150, 600, 200, 400, 100).toDB(), null, 3),
        row(listOf(250).toDB(), null, 1),
        row(listOf(250, 250, 250, 250, 250).toDB(), null, 3),
        row(listOf(600).toDB(), null, 1),
        row(listOf(250).toDB(), null, 1),
        row(listOf(200, 100, 150, 100).toDB(), null, 0),
        row(mutableMapOf(), null, 0),
      ) { db, batchSize, result ->
        val until = if (db.size > 1) db.size else 2
        val batchSizeToDealWith = batchSize ?: Random.nextInt(1, until)
        greedyChunking2(db, batchSizeToDealWith) shouldBe result
      }
    }
  })

internal fun List<Int>.toDB(): DB =
  this.withIndex().associate { Id(it.index) to (it.value to null) }.toMutableMap()
