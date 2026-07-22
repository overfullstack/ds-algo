package ds.ll

import ds.ll.ListNode.Companion.parseJsonFileToSLL
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

private const val PKG_PATH = "ll/sll"

class SLLNodeTest :
  StringSpec({
    "Create SLL from array" {
      forAll(row(intArrayOf(1, 2, 3)), row(intArrayOf(1))) { arr ->
        ListNode.of(arr)?.toArray() shouldBe arr
      }
    }

    "Create SLL from Empty array" { ListNode.of(intArrayOf())?.toArray() shouldBe null }

    "Check SLL equals" {
      forAll(row(intArrayOf(3, 2, 1, 1, 3)), row(intArrayOf(1))) { arr ->
        val node1 = ListNode.of(arr)!!
        val node2 = ListNode.of(arr)!!
        (node1 == node2) shouldBe true
      }
    }

    "Get Node for Value" {
      forAll(
        row(intArrayOf(1, 2, 3, 4), 3, ListNode(3, ListNode(4, null))),
        row(intArrayOf(1, 2, 3, 4), 4, ListNode(4, null)),
        row(intArrayOf(1), 1, ListNode(1, null)),
        row(intArrayOf(), 1, null),
      ) { arr, valToFind, resultNode ->
        ListNode.of(arr)?.getNodeForValue(valToFind) shouldBe resultNode
      }
    }

    "reverse" {
      TestCases.load(
          "$PKG_PATH/sll-reverse-test-cases-1.json",
          "$PKG_PATH/sll-reverse-test-cases-2.json",
        )
        .map { case -> case.input<IntArray>(1) to case.output<IntArray>(1) }
        .forAll { (input, output) ->
          val head = ListNode.of(input)!!
          head.reverse().toArray() shouldBe output
        }
    }

    "length" {
      forAll(row(intArrayOf(1), 1), row(intArrayOf(1, 2), 2), row(intArrayOf(1, 2, 3), 3)) {
        arr,
        result ->
        ListNode.of(arr)?.length() shouldBe result
      }
    }

    "Get Node at Pos" { // getNodeAtOrNull is 1-based (pos 1 == head), matching its callers
      forAll(
        row(intArrayOf(1, 2, 3, 4), 3, ListNode(3, ListNode(4, null))),
        row(intArrayOf(1, 2, 3, 4), 4, ListNode(4, null)),
        row(intArrayOf(1), 1, ListNode(1, null)),
        row(intArrayOf(), 1, null),
      ) { arr, pos, resultNode ->
        ListNode.of(arr)?.getNodeAtOrNull(pos) shouldBe resultNode
      }
    }

    "SLL from JSON file" {
      forAll(
        row(parseJsonFileToSLL("$PKG_PATH/sll1.json"), intArrayOf(1, 2, 3)),
        row(parseJsonFileToSLL("$PKG_PATH/sll2.json"), intArrayOf(1, 1, 1, 3, 4, 5, 5, 5, 5, 10)),
      ) { headSLLNode, result ->
        headSLLNode.toArray() shouldBe result
      }
    }
  })
