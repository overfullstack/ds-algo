package educative.heap

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.StrStrToList

/* 29 Jul 2025 20:55 */

private val PKG_PATH = "educative/heap/AllAnagrams"

class AllAnagramsTest :
  StringSpec({
    "all anagrams" {
      StrStrToList.parseJsonFileToTestCases(
          "$PKG_PATH/test-cases-1.json",
          "$PKG_PATH/test-cases-2.json",
        )
        .forAll { (a, b, result) -> findAllAnagrams(a, b) shouldBe result }
    }
  })
