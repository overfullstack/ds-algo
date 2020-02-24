package ds

import io.kotlintest.matchers.collections.shouldContainInOrder
import io.kotlintest.specs.StringSpec

class TreeNodeExtensionsKtTest : StringSpec() {

    init {
        "incompleteTreeToList" {
            val listForTree = listOf(8, 5, 10, 1, 7, null, 12)
            val treeNode = TreeNode.listToIncompleteTree(listForTree)
            treeNode!!.incompleteTreeToList() shouldContainInOrder listForTree
        }
    }

}
