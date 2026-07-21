package educative.heap

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 29 Jul 2025 20:55 */

private val PKG_PATH = "educative/heap/AllAnagrams"

class AllAnagramsTest :
  StringSpec({
    "all anagrams" {
      TestCases.load("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case ->
          Triple(case.input<String>(1), case.input<String>(2), case.output<List<Int>>(1))
        }
        .forAll { (a, b, result) -> findAllAnagrams(a, b) shouldBe result }
    }
  })
