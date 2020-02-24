package ds

import java.util.*

data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null) {
    private var leftSize: Int = 0

    fun traversalAnyOrder(
        order: (Int, Array<Int>, Array<Int>) -> Array<Int> = { `val`, left, right -> arrayOf(`val`) + left + right }
    ): Array<Int> {
        val left = left?.traversalAnyOrder(order) ?: emptyArray()
        val right = right?.traversalAnyOrder(order) ?: emptyArray()
        return order(`val`, left, right)
    }

    fun inorderTraversal() {
        left?.inorderTraversal()
        print(`val`)
        right?.inorderTraversal()
    }

    fun insertForRank(valToInsert: Int): Int =
        if (valToInsert <= `val`) {
            leftSize++
            left?.insertForRank(valToInsert) ?: run {
                left = TreeNode(valToInsert)
                leftSize
            }
        } else { // 1 - for current node, leftSize for it's rank (all the nodes less than the cur node)
            1 + leftSize + (right?.insertForRank(valToInsert) ?: run {
                right = TreeNode(valToInsert)
                0
            })
        }


    fun getNodeAtRank(rank: Int): TreeNode? =
        when {
            rank == leftSize + 1 -> this
            rank <= leftSize + 1 -> left?.getNodeAtRank(rank)
            else -> right?.getNodeAtRank(rank - leftSize - 1)
        }


    fun getRank(valForRank: Int): Int =
        when {
            valForRank == this.`val` -> leftSize
            valForRank < this.`val` -> left?.getRank(valForRank) ?: -1
            else -> right?.let { 1 + leftSize + it.getRank(valForRank) } ?: -1
        }

    fun insert(valToInsert: Int): Unit =
        if (valToInsert <= `val`) {
            left?.insert(valToInsert) ?: run { left = TreeNode(valToInsert) }
        } else {
            right?.insert(valToInsert) ?: run { right = TreeNode(valToInsert) }
        }


    companion object Utils {
        fun arrToBST(arr: IntArray): TreeNode? {
            if (arr.isEmpty()) {
                return null
            }
            return TreeNode(arr[0]).also { root -> arr.drop(1).forEach { root.insert(it) } }
        }

        fun listToCompleteTree(list: List<Int?>): TreeNode? {
            if (list.isEmpty() || list[0] == null) {
                return null
            }
            return buildCompleteTreeFromList(list)
        }

        private fun buildCompleteTreeFromList(list: List<Int?>, index: Int = 0): TreeNode? {
            if (index > list.lastIndex) {
                return null
            }
            return list[index]?.let {
                TreeNode(
                    it,
                    buildCompleteTreeFromList(list, 2 * index + 1),
                    buildCompleteTreeFromList(list, 2 * index + 2)
                )
            }
        }

        fun listToIncompleteTree(valList: List<Int?>): TreeNode? {
            val valQueue = LinkedList(valList) // Can't use ArrayDeque as it won't allow nulls.
            val rootVal = valQueue.poll() ?: return null
            val treeNodeQueue = ArrayDeque<TreeNode>()
            val root = TreeNode(rootVal)
            treeNodeQueue.push(root)
            while (valQueue.isNotEmpty()) {
                val curRoot = treeNodeQueue.poll()
                valQueue.poll()?.let { leftValue ->
                    treeNodeQueue.addLast(TreeNode(leftValue).also { curRoot.left = it })
                }
                valQueue.poll()?.let { rightValue ->
                    treeNodeQueue.addLast(TreeNode(rightValue).also { curRoot.right = it })
                }
            }
            return root
        }
    }
}
