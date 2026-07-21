package educative.array.slidingwindow

import educative.elegant.slidingwindow.repeatedDNASequences
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import testcase.TestCases

private const val PKG_PATH = "educative/array/slidingwindow/RepeatedDNASequences"

class RepeatedDNASequencesTest :
  StringSpec({
    "verify alien dictionary" {
      TestCases.load("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case ->
          Triple(case.input<String>(1), case.input<Int>(2), case.output<List<String>>(1))
        }
        .forAll { (str, k, result) ->
          repeatedDNASequences(str, k) shouldContainExactlyInAnyOrder result
        }
    }
  })
