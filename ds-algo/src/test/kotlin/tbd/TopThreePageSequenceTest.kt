package tbd

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class TopThreePageSequenceTest :
  StringSpec({
    val solution = TopThreePageSequence()

    "find top 3-page sequence from problem example" {
      val logEntries =
        listOf(
          "T0,C1,A",
          "T0,C2,E",
          "T1,C1,B",
          "T1,C2,B",
          "T2,C1,C",
          "T2,C2,C",
          "T3,C1,D",
          "T3,C2,D",
          "T4,C1,E",
          "T5,C3,A",
          "T5,C3,E",
        )

      val result = solution.findTopThreePageSequence(logEntries)
      result shouldBe "B->C->D"
    }

    "test various scenarios" {
      forAll(
        row(
          "single customer with multiple sequences",
          listOf("T0,C1,A", "T1,C1,B", "T2,C1,C", "T3,C1,D", "T4,C1,E"),
          "A->B->C",
        ),
        row(
          "multiple customers with same sequence",
          listOf("T0,C1,A", "T1,C1,B", "T2,C1,C", "T0,C2,A", "T1,C2,B", "T2,C2,C"),
          "A->B->C",
        ),
        row("insufficient data for 3-page sequence", listOf("T0,C1,A", "T1,C1,B"), ""),
      ) { description, logEntries, expected ->
        val result = solution.findTopThreePageSequence(logEntries)
        result shouldBe expected
      }
    }

    "test streaming approach" {
      val logEntries =
        listOf(
          "T0,C1,A",
          "T0,C2,E",
          "T1,C1,B",
          "T1,C2,B",
          "T2,C1,C",
          "T2,C2,C",
          "T3,C1,D",
          "T3,C2,D",
          "T4,C1,E",
          "T5,C3,A",
          "T5,C3,E",
        )

      val result = solution.findTopThreePageSequenceStreaming(logEntries.iterator())
      result shouldBe "B->C->D"
    }

    "test external sort approach" {
      val logEntries =
        listOf(
          "T0,C1,A",
          "T0,C2,E",
          "T1,C1,B",
          "T1,C2,B",
          "T2,C1,C",
          "T2,C2,C",
          "T3,C1,D",
          "T3,C2,D",
          "T4,C1,E",
          "T5,C3,A",
          "T5,C3,E",
        )

      // Sort by customer ID and time for external sort approach
      val sortedEntries =
        logEntries.sortedWith(
          compareBy(
            { it.split(",")[1] }, // customerId
            { it.split(",")[0].substring(1).toInt() }, // time
          )
        )

      val result = solution.findTopThreePageSequenceExternalSort(sortedEntries.iterator())
      result shouldBe "B->C->D"
    }
  })
