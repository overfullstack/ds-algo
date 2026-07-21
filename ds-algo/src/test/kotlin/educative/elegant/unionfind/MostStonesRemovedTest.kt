package educative.elegant.unionfind

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases
import utils.toPair

/* 08 Sep 2024 19:02 */

private const val PKG_PATH = "educative/graph/unionfind/MostStonesRemoved"

class MostStonesRemovedTest :
  StringSpec({
    "most stones removed with same row or column" {
      TestCases.load("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case ->
          Pair(case.input<List<List<Int>>>(1).map { it.toPair() }.toSet(), case.output<Int>(1))
        }
        .forAll { (stones, result) -> mostStonesRemoved(stones) shouldBe result }
    }
  })
