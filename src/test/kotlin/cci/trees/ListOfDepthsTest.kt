package cci.trees

import ds.tree.TreeNode.Utils.levelOrderToTree
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ListOfDepthsTest : StringSpec({
    "List of Depths" {
        forAll(
            row(listOf(1, 2, 3, 4, 5, 6, 7), listOf(listOf(1), listOf(2, 3), listOf(4, 5, 6, 7))),
            row(listOf(1), listOf(listOf(1))),
            row(listOf(1, 2, 3, null, null, 4, null), listOf(listOf(1), listOf(2, 3), listOf(4)))
        ) { levelOrder, result ->
            val root = levelOrderToTree(levelOrder)
            val listOfDepths = root!!.listOfDepths()
            listOfDepths.map { levelList -> levelList.map { it.value } } shouldBe result
        }
    }
})
