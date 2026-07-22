package educative.twopointers

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 06 Aug 2025 13:06 */

private const val PKG_PATH = "educative/twopointers/FirstBadVersion"

// * The output pair's second element is an API-call count that is
// * implementation-specific; only the found bad-version index (first element) is
// * the actual contract, so we assert on that.
class FirstBadVersionTest :
  StringSpec({
    "first bad version" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case ->
          Triple(case.input<Int>(1), case.input<Int>(2), case.output<List<Int>>(1))
        }
        .forAll { (n, firstBadVersionIndex, output) ->
          val firstBadVersion = FirstBadVersion(firstBadVersionIndex)
          firstBadVersion.firstBadVersion(n).first shouldBe output.first()
        }
    }
  })
