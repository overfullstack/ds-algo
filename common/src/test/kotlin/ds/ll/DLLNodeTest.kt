package ds.ll

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class DLLNodeTest :
  StringSpec({
    "toList" {
      forAll(row(listOf(1, 2, 3)), row(listOf(1))) { list ->
        val of = DLLNode.of(list)
        println(of)
        of?.toList() shouldBe list
      }
    }
  })
