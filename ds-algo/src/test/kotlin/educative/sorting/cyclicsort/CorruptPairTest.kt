package educative.sorting.cyclicsort

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.ListIntsToListInts.Companion.parseJsonFileToTestCases
import utils.toPair

/* 27 Aug 2024 17:53 */

private const val PKG_PATH = "educative/sorting/cyclicsort/CorruptPair"

class CorruptPairTest :
  StringSpec({
    "corrupt pair" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (input, output) ->
        corruptPair(input.toIntArray()) shouldBe output.toPair()
      }
    }
  })
