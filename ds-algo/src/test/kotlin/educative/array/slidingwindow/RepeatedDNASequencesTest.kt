package educative.array.slidingwindow

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import testcase.TestCase7.Companion.parseJsonFileToTestCases

private const val PKG_PATH = "educative/array/slidingwindow/RepeatedDNASequences"

class RepeatedDNASequencesTest :
  StringSpec({
    "verify alien dictionary" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (str, k, result) ->
          repeatedDNASequences(str, k) shouldContainExactlyInAnyOrder result
        }
    }
  })
