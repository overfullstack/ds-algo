package educative.array

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 28 Aug 2024 10:18 */

private const val PKG_PATH = "educative/array/VerifyAlienDictionary"

class VerifyAlienDictionaryTest :
  StringSpec({
    "verify alien dictionary" {
      TestCases.load("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case ->
          Triple(case.input<List<String>>(1), case.input<String>(2), case.output<Boolean>(1))
        }
        .forAll { (words, order, result) ->
          verifyAlienDictionary(words.toTypedArray(), order) shouldBe result
        }
    }
  })
