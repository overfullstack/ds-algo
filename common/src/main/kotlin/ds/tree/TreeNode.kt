package ds.tree

import com.salesforce.revoman.input.readFileInResourcesToString
import java.util.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

data class TreeNode
@JvmOverloads
constructor(
  @JvmField var value: Int,
  @JvmField var left: TreeNode? = null,
  @JvmField var right: TreeNode? = null,
  @JvmField var parent: TreeNode? = null,
  @JvmField var next: TreeNode? = null,
  @JvmField val id: String = ""
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
    return order(value, left, right)
  }

  fun inorderTraversal(): List<Int> =
    (left?.inorderTraversal() ?: emptyList()) +
      listOf(value) +
      (right?.inorderTraversal() ?: emptyList())

  fun isValidBST(min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): Boolean =
    when {
      value !in min + 1 until max -> false
      else -> left?.isValidBST(min, value) ?: true && right?.isValidBST(value, max) ?: true
    }

  fun insertForRank(valToInsert: Int): Int = // returns leftSize
  if (valToInsert <= value) {
      leftSize++
      left?.insertForRank(valToInsert)
        ?: run {
          left = TreeNode(valToInsert)
          leftSize
        }
    } else { // 1 - for current node, leftSize for it's rank (all the nodes less than the cur
      // node)
      1 +
        leftSize +
        (right?.insertForRank(valToInsert)
          ?: run {
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
      valForRank < this.value -> left?.getRank(valForRank) ?: -1 // -1 for val not found
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

  override fun toString(): String =
    "TreeNode(value=$value, left=${left?.value}, right=${right?.value}, parent=${parent?.value}, next=${next?.value}, id='$id', leftSize=$leftSize)"

  companion object {
    fun arrToBST(arr: IntArray): TreeNode? {
      if (arr.isEmpty()) {
        return null
      }
      return TreeNode(arr[0]).also { root -> arr.drop(1).forEach { root.insert(it) } }
    }

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
        buildCompleteTreeFromPreOrder(levelOrder, 2 * index + 2)
      )
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
          curRoot.left = TreeNode(leftValue).also { treeNodeQueue.addLast(it) }
        }
        valQueue.poll()?.let { rightValue ->
          curRoot.right = TreeNode(rightValue).also { treeNodeQueue.addLast(it) }
        }
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
      val treeJson = readFileInResourcesToString(jsonFilePath)
      val jTree = Json.decodeFromString<JTree>(treeJson)
      val idToTreeNode =
        jTree.tree.nodes.associate {
          it.id to Triple(TreeNode(id = it.id, value = it.value), it.left, it.right)
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
