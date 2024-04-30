package algoexpert.ll

import ga.overfullstack.ds.ll.SLLNode.Companion.parseJsonFileToSLL
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

private const val PKG_PATH = "algoexpert/ll/MiddleNode"

class MiddleNodeTest :
  StringSpec({
    "find middle node" {
      forAll(
        row(parseJsonFileToSLL("$PKG_PATH/sll1.json"), 1),
        row(parseJsonFileToSLL("$PKG_PATH/sll2.json"), 2),
        row(parseJsonFileToSLL("$PKG_PATH/sll3.json"), 7),
        row(parseJsonFileToSLL("$PKG_PATH/sll4.json"), 3),
      ) { headNode, resultNodeValue ->
        headNode.middleNode().value shouldBe resultNodeValue
      }
    }
  })
