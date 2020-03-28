package ds

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainInOrder


class TreeNodeExtensionsKtTest : StringSpec() {

    init {
        "incompleteTreeToList" {
            val listForTree = listOf(8, 5, 10, 1, 7, null, 12)
            val treeNode = TreeNode.listToIncompleteTree(listForTree)
            treeNode!!.incompleteTreeToList() shouldContainInOrder listForTree
        }
    }

}
