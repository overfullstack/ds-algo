package educative.heap

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.ListPairIntToInt.Companion.parseJsonFileToTestCases

/* 03 Aug 2025 11:52 */

private const val PKG_PATH = "educative/heap/KClosestPointsToOrigin"

class KClosestPointsToOriginTest :
  StringSpec({
    "k closest points to origin" {
      forAll(
        row(listOf(Pair(1, 3), Pair(3, 4), Pair(2, -1)), 2, setOf(Pair(1, 3), Pair(2, -1))),
        row(
          listOf(Pair(1, 3), Pair(2, 4), Pair(2, -1), Pair(-2, 2), Pair(5, 3), Pair(3, -2)),
          3,
          setOf(Pair(1, 3), Pair(2, -1), Pair(-2, 2)),
        ),
        row(listOf(Pair(1, 3), Pair(5, 3), Pair(3, -2), Pair(-2, 2)), 1, setOf(Pair(-2, 2))),
        row(
          listOf(Pair(2, -1), Pair(-2, 2), Pair(1, 3), Pair(2, 4)),
          4,
          setOf(Pair(2, 4), Pair(1, 3), Pair(-2, 2), Pair(2, -1)),
        ),
        row(
          listOf(
            Pair(1, 3),
            Pair(2, 4),
            Pair(2, -1),
            Pair(-2, 2),
            Pair(5, 3),
            Pair(3, -2),
            Pair(5, 3),
            Pair(3, -2),
          ),
          5,
          setOf(Pair(3, -2), Pair(3, -2), Pair(2, -1), Pair(-2, 2), Pair(1, 3)),
        ),
      ) { coordinates, k, result ->
        kClosestPointsToOrigin(coordinates, k) shouldBe result
      }
    }
    
    "k closest points to origin 2".config(enabled = false) {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (coordinates, k, result) ->
          kClosestPointsToOrigin(coordinates, k) shouldBe result
        }
    }

  })
