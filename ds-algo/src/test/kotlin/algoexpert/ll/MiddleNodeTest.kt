package algoexpert.ll

import ga.overfullstack.ds.ll.SLLNode
import ga.overfullstack.utils.TEST_RESOURCES_PATH
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

const val PKG_PATH = "$TEST_RESOURCES_PATH/algoexpert/ll/MiddleNode"

class MiddleNodeTest :
  StringSpec({
    "find middle node" {
      forAll(
        row(SLLNode.parseJsonFileToSLL("$PKG_PATH/sll1.json"), 1),
        row(SLLNode.parseJsonFileToSLL("$PKG_PATH/sll2.json"), 2),
        row(SLLNode.parseJsonFileToSLL("$PKG_PATH/sll3.json"), 7),
        row(SLLNode.parseJsonFileToSLL("$PKG_PATH/sll4.json"), 3),
      ) { headNode, resultNodeValue ->
        headNode.middleNode().value shouldBe resultNodeValue
      }
    }
  })
