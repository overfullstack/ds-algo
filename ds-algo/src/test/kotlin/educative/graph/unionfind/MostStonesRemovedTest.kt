package educative.graph.unionfind

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.LListIntToInt.Companion.parseJsonFileToTestCases

/* 08 Sep 2024 19:02 */

private const val PKG_PATH = "educative/graph/unionfind/MostStonesRemoved"

class MostStonesRemovedTest :
  StringSpec({
    "most stones removed with same row or column" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (stones, result) -> mostStonesRemoved(stones) shouldBe result }
    }
  })
