package educative.twopointers

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases
import utils.toPair

/* 06 Aug 2025 13:06 */

private const val PKG_PATH = "educative/twopointers/FirstBadVersion"

// Failing due to approach difference, but's only + or -1 for API calls
class FirstBadVersionTest :
  StringSpec({
    "first bad version" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case ->
          Triple(case.input<Int>(1), case.input<Int>(2), case.output<List<Int>>(1))
        }
        .forAll { (n, firstBadVersionIndex, output) ->
          val firstBadVersion = FirstBadVersion(firstBadVersionIndex)
          firstBadVersion.firstBadVersion(n) shouldBe output.toPair()
        }
    }
  })
