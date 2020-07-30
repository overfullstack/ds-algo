package ds

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import mu.KLogging

class SLLNodeTest : StringSpec({
    "Create SLL from array" {
        forAll(
            row(intArrayOf(1, 2, 3)),
            row(intArrayOf(1))
        ) { arr ->
            SLLNode.of(arr)?.toArray() shouldBe arr
        }
    }

    "Create SLL from Empty array" {
        SLLNode.of(intArrayOf())?.toArray() shouldBe null
    }

    "Check SLL equals" {
        forAll(
            row(intArrayOf(3, 2, 1, 1, 3)),
            row(intArrayOf(1))
        ) { arr ->
            val node1 = SLLNode.of(arr)!!
            val node2 = SLLNode.of(arr)!!
            (node1 == node2) shouldBe true
        }
    }

    "Get Node for Value" {
        forAll(
            row(intArrayOf(1, 2, 3, 4), 3, SLLNode(3, SLLNode(4, null))),
            row(intArrayOf(1, 2, 3, 4), 4, SLLNode(4, null)),
            row(intArrayOf(1), 1, SLLNode(1, null)),
            row(intArrayOf(), 1, null),
        ) { arr, valToFind, resultNode ->
            SLLNode.of(arr)?.getNodeForValue(valToFind) shouldBe resultNode
        }
    }

    "Reverse" {
        forAll(
            row(intArrayOf(1, 2, 3, 4), intArrayOf(4, 3, 2, 1)),
            row(intArrayOf(1), intArrayOf(1)),
            row(intArrayOf(), null),
        ) { arr, result ->
            SLLNode.of(arr)?.reverse()?.toArray() shouldBe result
        }
    }

    "length" {
        forAll(
            row(intArrayOf(1), 1),
            row(intArrayOf(1, 2), 2),
            row(intArrayOf(1, 2, 3), 3),
        ) { arr, result ->
            SLLNode.of(arr)?.length() shouldBe result
        }
    }

    "Get Node at Pos" {
        forAll(
            row(intArrayOf(1, 2, 3, 4), 2, SLLNode(3, SLLNode(4, null))),
            row(intArrayOf(1, 2, 3, 4), 3, SLLNode(4, null)),
            row(intArrayOf(1), 0, SLLNode(1, null)),
            row(intArrayOf(), 1, null),
        ) { arr, pos, resultNode ->
            SLLNode.of(arr)?.getNodeAt(pos) shouldBe resultNode
        }
    }

}) {
    companion object : KLogging()
}
