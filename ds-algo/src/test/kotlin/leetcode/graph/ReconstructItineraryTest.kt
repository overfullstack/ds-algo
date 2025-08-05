package leetcode.graph

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

/* 05 Aug 2025 21:07 */

class ReconstructItineraryTest :
  StringSpec({
    "reconstruct itinerary" {
      forAll(
        row(
          listOf(
            listOf("JFK", "SFO"),
            listOf("JFK", "ATL"),
            listOf("SFO", "JFK"),
            listOf("ATL", "AAA"),
            listOf("AAA", "ATL"),
            listOf("ATL", "BBB"),
            listOf("BBB", "ATL"),
            listOf("ATL", "CCC"),
            listOf("CCC", "ATL"),
            listOf("ATL", "DDD"),
            listOf("DDD", "ATL"),
            listOf("ATL", "EEE"),
            listOf("EEE", "ATL"),
            listOf("ATL", "FFF"),
            listOf("FFF", "ATL"),
            listOf("ATL", "GGG"),
            listOf("GGG", "ATL"),
            listOf("ATL", "HHH"),
            listOf("HHH", "ATL"),
            listOf("ATL", "III"),
            listOf("III", "ATL"),
            listOf("ATL", "JJJ"),
            listOf("JJJ", "ATL"),
            listOf("ATL", "KKK"),
            listOf("KKK", "ATL"),
            listOf("ATL", "LLL"),
            listOf("LLL", "ATL"),
            listOf("ATL", "MMM"),
            listOf("MMM", "ATL"),
            listOf("ATL", "NNN"),
            listOf("NNN", "ATL"),
          ),
          listOf(
            "JFK",
            "SFO",
            "JFK",
            "ATL",
            "AAA",
            "ATL",
            "BBB",
            "ATL",
            "CCC",
            "ATL",
            "DDD",
            "ATL",
            "EEE",
            "ATL",
            "FFF",
            "ATL",
            "GGG",
            "ATL",
            "HHH",
            "ATL",
            "III",
            "ATL",
            "JJJ",
            "ATL",
            "KKK",
            "ATL",
            "LLL",
            "ATL",
            "MMM",
            "ATL",
            "NNN",
            "ATL",
          ),
        )
      ) { tickets, answer ->
        val result = findItinerary(tickets)
        result shouldBe answer
      }
    }
  })
