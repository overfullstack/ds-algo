package algoexpert.ll

import ga.overfullstack.ds.ll.SLLNode.Companion.parseJsonFileToSLL
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

private const val PKG_PATH = "algoexpert/ll/RemoveDuplicatesInSLL"

class RemoveDuplicatesInSLLTest :
  StringSpec({
    "remove duplicates in SLL" {
      forAll(
        row(parseJsonFileToSLL("$PKG_PATH/sll1.json"), intArrayOf(1, 3, 4, 5, 6)),
        row(parseJsonFileToSLL("$PKG_PATH/sll2.json"), intArrayOf(1, 4, 5, 6)),
        row(parseJsonFileToSLL("$PKG_PATH/sll3.json"), intArrayOf(1)),
      ) { head, result ->
        head.removeDuplicates().toArray() shouldBe result
      }
    }
  })
