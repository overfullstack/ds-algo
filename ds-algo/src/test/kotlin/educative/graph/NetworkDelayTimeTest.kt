package educative.graph

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 15 Jul 2025 16:37 */

private const val PKG_PATH = "educative/graph/NetworkDelayTime"

class NetworkDelayTimeTest :
  StringSpec({
    "network delay time" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case ->
          Triple(case.input<List<List<Int>>>(1), case.input<Int>(2), case.input<Int>(3)) to
            case.output<Int>(1)
        }
        .forAll { (input, output) ->
          val (times, n, origin) = input
          networkDelayTime(times.map { it.toIntArray() }.toTypedArray(), n, origin) shouldBe output
        }
    }
  })
