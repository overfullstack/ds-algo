package ds.tree

import java.util.*

data class TreeNode(var value: Int, var left: TreeNode? = null, var right: TreeNode? = null, var parent: TreeNode? = null) {
    private var leftSize: Int = 0

    fun traversalAnyOrder(
        order: (Int, Array<Int>, Array<Int>) -> Array<Int> = { value, left, right -> arrayOf(value) + left + right }
    ): Array<Int> {
        val left = left?.traversalAnyOrder(order) ?: emptyArray()
        val right = right?.traversalAnyOrder(order) ?: emptyArray()
        return order(value, left, right)
    }

    fun inorderTraversal() {
        left?.inorderTraversal()
        print(value)
        right?.inorderTraversal()
    }

    fun insertForRank(valToInsert: Int): Int =
        if (valToInsert <= value) {
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
            valForRank == this.value -> leftSize
            valForRank < this.value -> left?.getRank(valForRank) ?: -1
            else -> right?.let { 1 + leftSize + it.getRank(valForRank) } ?: -1
        }

    fun insert(valToInsert: Int): Unit =
        if (valToInsert <= value) {
            left?.insert(valToInsert) ?: run { left = TreeNode(valToInsert) }
        } else {
            right?.insert(valToInsert) ?: run { right = TreeNode(valToInsert) }
        }

    fun incompleteTreeToList(): List<Int?> {
        val valQueue = LinkedList<Int?>()
        val treeNodeQueue = ArrayDeque<TreeNode>()
        treeNodeQueue.add(this)
        valQueue.add(this.value)
        while (treeNodeQueue.isNotEmpty()) {
            if (left != null || right != null) {
                val curTreeNode = treeNodeQueue.poll()

                curTreeNode.left?.let { treeNodeQueue.addLast(it) }
                curTreeNode.right?.let { treeNodeQueue.addLast(it) }

                valQueue.addLast(curTreeNode.left?.value)
                valQueue.addLast(curTreeNode.right?.value)
            }
        }
        return valQueue
    }

    fun height(): Int = 1 + maxOf((left?.height() ?: 0), (right?.height() ?: 0))

    fun setParents(parent: TreeNode? = null) {
        this.parent = parent
        left?.setParents(this)
        right?.setParents(this)
    }

    fun getNodeWithValue(valToFind: Int): TreeNode? =
        if (value == valToFind) {
            this
        } else {
            left?.getNodeWithValue(valToFind) ?: right?.getNodeWithValue(valToFind)
        }

    companion object Utils {
        fun arrToBST(arr: IntArray): TreeNode? {
            if (arr.isEmpty()) {
                return null
            }
            return TreeNode(arr[0]).also { root -> arr.drop(1).forEach { root.insert(it) } }
        }

        fun levelOrderToCompleteTree(levelOrder: List<Int?>): TreeNode? {
            if (levelOrder.isEmpty() || levelOrder[0] == null) {
                return null
            }
            return buildCompleteTreeFromPreOrder(levelOrder)
        }

        private fun buildCompleteTreeFromPreOrder(levelOrder: List<Int?>, index: Int = 0): TreeNode? {
            if (index > levelOrder.lastIndex) {
                return null
            }
            return levelOrder[index]?.let {
                TreeNode(
                    it,
                    buildCompleteTreeFromPreOrder(levelOrder, 2 * index + 1),
                    buildCompleteTreeFromPreOrder(levelOrder, 2 * index + 2)
                )
            }
        }

        fun levelOrderToTree(levelOrder: List<Int?>): TreeNode? {
            val valQueue = LinkedList(levelOrder) // Can't use ArrayDeque as it won't allow nulls.
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
