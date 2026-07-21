package educative.graph

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 15 Jul 2025 16:37 */

private const val PKG_PATH = "educative/graph/BusRoutes"

class BusRoutesTest :
  StringSpec({
    "minimum number of buses" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case ->
          Triple(case.input<List<List<Int>>>(1), case.input<Int>(2), case.input<Int>(3)) to
            case.output<Int>(1)
        }
        .forAll { (input, output) ->
          val (routes, source, destination) = input
          numBusesToDestination(
            routes.map { it.toIntArray() }.toTypedArray(),
            source,
            destination,
          ) shouldBe output
        }
    }
  })
