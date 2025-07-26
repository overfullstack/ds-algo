package educative.graph

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.LListIntIntToInt.Companion.parseJsonFileToTestCases

/* 15 Jul 2025 16:37 */

private const val PKG_PATH = "educative/graph/BusRoutes"

class BusRoutesTest :
  StringSpec({
    "minimum number of buses" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (input, output) ->
        val (routes, source, destination) = input
        minimumNumberOfBuses(routes, source, destination) shouldBe output
      }
    }
  })
