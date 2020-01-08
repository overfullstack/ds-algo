package ds

import java.util.*

data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null) {
    private var leftSize: Int = 0

    fun traversalInOrder(
        order: (Int, Array<Int>, Array<Int>) -> Array<Int> = { `val`, left, right -> arrayOf(`val`) + left + right }
    ): Array<Int> {
        val left = left?.traversalInOrder(order) ?: emptyArray()
        val right = right?.traversalInOrder(order) ?: emptyArray()
        return order(`val`, left, right)
    }

    fun inorderTraversal() {
        left?.inorderTraversal()
        print(`val`)
        right?.inorderTraversal()
    }

    fun insertForRank(valToInsert: Int): Int {
        return if (valToInsert <= `val`) {
            leftSize++
            left?.insertForRank(valToInsert) ?: run {
                left = TreeNode(valToInsert)
                leftSize
            }
        } else {
            1 + leftSize + (right?.insertForRank(valToInsert) ?: run {
                right = TreeNode(valToInsert)
                0
            })
        }
    }

    fun getNodeAtRank(rank: Int): TreeNode? {
        return when {
            rank == leftSize + 1 -> this
            rank <= leftSize + 1 -> left?.getNodeAtRank(rank)
            else -> right?.getNodeAtRank(rank - leftSize - 1)
        }
    }

    fun getRank(valForRank: Int): Int {
        return when {
            valForRank == this.`val` -> leftSize
            valForRank < this.`val` -> left?.getRank(valForRank) ?: -1
            else -> right?.let { 1 + leftSize + it.getRank(valForRank) } ?: -1
        }
    }

    fun insert(valToInsert: Int) {
        if (valToInsert <= `val`) {
            left?.insert(valToInsert) ?: run { left = TreeNode(valToInsert) }
        } else {
            right?.insert(valToInsert) ?: run { right = TreeNode(valToInsert) }
        }
    }
    
    companion object Utils {
        fun readArrToTree(arr: IntArray): TreeNode? {
            if (arr.isEmpty()) {
                return null
            }
            return TreeNode(arr[0]).also { root -> arr.drop(1).forEach { root.insert(it) } }
        }

        fun readPreOrderListToTree(inputArr: List<Int?>): TreeNode? {
            if (inputArr.isEmpty()) {
                return null
            }
            return inputArr[0]?.let { TreeNode(it).readPreOrderListToTree(inputArr) }
        }

        private fun TreeNode.readPreOrderListToTree(inputArr: List<Int?>, index: Int = 0): TreeNode? {
            if (index > inputArr.lastIndex) {
                return null
            }
            if (index >= inputArr.size / 2) {
                return inputArr[index]?.let { TreeNode(it) } // Leaf node
            }
            val leftIndex = 2 * index + 1
            val rightIndex = 2 * index + 2
            return inputArr[index]?.let {
                TreeNode(it, readPreOrderListToTree(inputArr, leftIndex), readPreOrderListToTree(inputArr, rightIndex))
            }
        }

        fun inorderTraversalWithStack() {
            val stk = ArrayDeque<TreeNode>()
            var cur = this as TreeNode?
            while (cur != null || stk.isNotEmpty()) {
                while (cur != null) {
                    stk.push(cur)
                    cur = cur.left
                }
                cur = stk.pop()
                print(cur.`val`)
                cur = cur.right
            }
        }

        fun inorderTraversalMorris(root: TreeNode?) {
            if (root == null) {
                return
            }
            var cur = root
            while (cur != null) {
                if (cur.left == null) {
                    print(cur.`val`) // We finish visiting a node
                    cur = cur.right // goto right or goto predecessor 
                } else {
                    var pre = cur.left
                    while (pre?.right != null && pre.right != cur) { // Find inorder predecessor.
                        pre = pre.right
                    }
                    if (pre?.right == null) {
                        pre?.right = cur
                        cur = cur.left // we can safely go to the left after establishing link to predecessor.
                    } else { // This happens when we revisit the node through predecessor link. This indicates we have finished traversing the left side.
                        pre.right = null // reset the pointer
                        print(cur.`val`) // We finish visiting a node
                        cur = cur.right // Now left visit is complete, safely go to right side
                    }
                }
            }
        }
    }
}

