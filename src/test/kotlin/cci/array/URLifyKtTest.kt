package cci.array

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class URLifyKtTest : StringSpec({
  "replaceWhiteSpaces" {
    forAll(
      row("Mr Gopal S Akshintala      ".toCharArray(), 21, "Mr%20Gopal%20S%20Akshintala")
    ) { inputStr, trueLength, result ->
      urlify(inputStr, trueLength) shouldBe result
    }
  }
})
