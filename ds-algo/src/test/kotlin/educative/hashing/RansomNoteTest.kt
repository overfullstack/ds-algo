package educative.hashing

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.StrStrToBool.Companion.parseJsonFileToTestCases

/* 29 Jul 2025 18:39 */

private const val PKG_PATH = "educative/hashing/RansomNote"

class RansomNoteTest :
  StringSpec({
    "ransom note" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (ransomNote, magazine, output) ->
          canConstruct(ransomNote, magazine) shouldBe output
        }
    }
  })
