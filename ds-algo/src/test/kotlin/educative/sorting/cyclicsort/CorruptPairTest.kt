package educative.sorting.cyclicsort

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases
import utils.toPair

/* 27 Aug 2024 17:53 */

private const val PKG_PATH = "educative/sorting/cyclicsort/CorruptPair"

class CorruptPairTest :
  StringSpec({
    "corrupt pair" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case -> case.input<List<Int?>>(1) to case.output<List<Int>>(1) }
        .forAll { (input, output) ->
          corruptPair(input.filterNotNull().toIntArray()) shouldBe output.toPair()
        }
    }
  })
