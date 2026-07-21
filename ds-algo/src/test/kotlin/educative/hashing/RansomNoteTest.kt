package educative.hashing

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 29 Jul 2025 18:39 */

private const val PKG_PATH = "educative/hashing/RansomNote"

class RansomNoteTest :
  StringSpec({
    "ransom note" {
      TestCases.load("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case ->
          Triple(case.input<String>(1), case.input<String>(2), case.output<Boolean>(1))
        }
        .forAll { (ransomNote, magazine, output) ->
          canConstruct(ransomNote, magazine) shouldBe output
        }
    }
  })
