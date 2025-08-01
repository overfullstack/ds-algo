package ds.tree

import com.salesforce.revoman.input.readFileToString
import java.util.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

data class TreeNode
@JvmOverloads
constructor(
  @JvmField var `val`: Int, // ! This field name is `val` as leetcode uses it
  @JvmField var left: TreeNode? = null,
  @JvmField var right: TreeNode? = null,
  @JvmField var parent: TreeNode? = null,
  @JvmField var next: TreeNode? = null,
  @JvmField val id: String = "",
) {

  private var leftSize: Int = 0

  val isLeafNode
    get() = left == null && right == null

  fun traversalAnyOrder(
    order: (Int, Array<Int>, Array<Int>) -> Array<Int> = { value, left, right ->
      arrayOf(value) + left + right
    } // Default is pre-order
  ): Array<Int> {
    val left = left?.traversalAnyOrder(order) ?: emptyArray()
    val right = right?.traversalAnyOrder(order) ?: emptyArray()
    return order(`val`, left, right)
  }

  fun inorderTraversal(): List<Int> =
    (left?.inorderTraversal() ?: emptyList()) +
      listOf(`val`) +
      (right?.inorderTraversal() ?: emptyList())

  fun isValidBST(min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): Boolean =
    when {
      `val` !in min + 1 until max -> false
      else -> left?.isValidBST(min, `val`) ?: true && right?.isValidBST(`val`, max) ?: true
    }

  fun insertForRank(valToInsert: Int): Int = // returns leftSize
  when {
      valToInsert <= `val` -> {
        leftSize++
        left?.insertForRank(valToInsert)
          ?: run {
            left = TreeNode(valToInsert)
            leftSize
          }
      }
      // 1 - for current node, leftSize for it's rank (all the nodes less than the cur node)
      else -> {
        1 +
          leftSize +
          (right?.insertForRank(valToInsert)
            ?: run {
              right = TreeNode(valToInsert)
              0
            })
      }
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
      valForRank < this.`val` -> left?.getRank(valForRank) ?: -1 // -1 for val not found
      else -> right?.let { 1 + leftSize + it.getRank(valForRank) } ?: -1
    }

  fun insertForBST(valToInsert: Int): Unit =
    when {
      valToInsert <= `val` ->
        left?.insertForBST(valToInsert) ?: run { left = TreeNode(valToInsert) }

      else -> right?.insertForBST(valToInsert) ?: run { right = TreeNode(valToInsert) }
    }

  fun height(): Int = 1 + maxOf((left?.height() ?: 0), (right?.height() ?: 0))

  fun setParents(parent: TreeNode? = null) {
    this.parent = parent
    left?.setParents(this)
    right?.setParents(this)
  }

  fun getNodeWithValue(valToFind: Int): TreeNode? =
    if (`val` == valToFind) {
      this
    } else {
      left?.getNodeWithValue(valToFind) ?: right?.getNodeWithValue(valToFind)
    }

  override fun toString(): String =
    "TreeNode(value=$`val`, left=${left?.`val`}, right=${right?.`val`}, parent=${parent?.`val`}, next=${next?.`val`}, id='$id', leftSize=$leftSize)"

  fun incompleteTreeToLevelOrderList(
    curLevel: List<TreeNode?> = listOf(this),
    result: List<Int?> = listOf(`val`),
  ): List<Int?> {
    val nextLevel: List<TreeNode?> = curLevel.flatMap { listOf(it?.left, it?.right) }
    val nextLevelValues: List<Int?> = nextLevel.map { it?.`val` }
    return when {
      nextLevelValues.all { it == null } -> result.dropLastWhile { it == null }
      else -> incompleteTreeToLevelOrderList(nextLevel, result + nextLevelValues)
    }
  }

  companion object {
    fun arrayToBST(arr: IntArray): TreeNode? {
      if (arr.isEmpty()) {
        return null
      }
      return TreeNode(arr[0]).also { root -> arr.drop(1).forEach { root.insertForBST(it) } }
    }

    /** level order is the same as pre-order */
    fun levelOrderToCompleteTree(levelOrder: List<Int>): TreeNode? {
      if (levelOrder.isEmpty()) {
        return null
      }
      return buildCompleteTreeFromPreOrder(levelOrder)
    }

    private fun buildCompleteTreeFromPreOrder(levelOrder: List<Int>, index: Int = 0): TreeNode? {
      if (index > levelOrder.lastIndex) {
        return null
      }
      return TreeNode(
        levelOrder[index],
        buildCompleteTreeFromPreOrder(levelOrder, 2 * index + 1),
        buildCompleteTreeFromPreOrder(levelOrder, 2 * index + 2),
      )
    }

    fun levelOrderToIncompleteTree(levelOrder: List<Int?>): TreeNode? {
      val valQueue = LinkedList(levelOrder) // Can't use ArrayDeque as it won't allow nulls.
      val rootVal = valQueue.poll() ?: return null
      val treeNodeQueue = ArrayDeque<TreeNode>()
      val root = TreeNode(rootVal)
      treeNodeQueue.push(root)
      while (valQueue.isNotEmpty()) {
        val curRoot = treeNodeQueue.poll()
        curRoot.left = valQueue.poll()?.let { TreeNode(it).also { treeNodeQueue.addLast(it) } }
        curRoot.right = valQueue.poll()?.let { TreeNode(it).also { treeNodeQueue.addLast(it) } }
      }
      return root
    }

    @Serializable
    data class JTree(val tree: Tree) {
      @Serializable
      data class Tree(val nodes: List<JNode>, val root: String) {
        @Serializable
        data class JNode(val id: String, val left: String?, val right: String?, val value: Int)
      }
    }

    @SuppressWarnings("kotlin:S6611")
    @OptIn(ExperimentalStdlibApi::class)
    fun parseJsonFileToTree(jsonFilePath: String): TreeNode {
      val treeJson = readFileToString(jsonFilePath)
      val jTree = Json.decodeFromString<JTree>(treeJson)
      val idToTreeNode =
        jTree.tree.nodes.associate {
          it.id to Triple(TreeNode(id = it.id, `val` = it.value), it.left, it.right)
        }
      val treeGraph: Map<String, TreeNode> =
        idToTreeNode.mapValues { (_, value) ->
          val (treeNode, leftId, rightId) = value
          leftId?.let { treeNode.left = idToTreeNode[leftId]?.first }
          rightId?.let { treeNode.right = idToTreeNode[rightId]?.first }
          treeNode
        }
      return treeGraph[jTree.tree.root]!!
    }
  }
}
