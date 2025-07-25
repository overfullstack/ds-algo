package educative.graph

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCase21.Companion.parseJsonFileToTestCases

/* 15 Jul 2025 16:37 */

private const val PKG_PATH = "educative/graph/NetworkDelayTime"

class NetworkDelayTimeTest :
  StringSpec({
    "network delay time" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (input, output) ->
        val (times, n, origin) = input
        networkDelayTime(times, n, origin) shouldBe output
      }
    }
  })
