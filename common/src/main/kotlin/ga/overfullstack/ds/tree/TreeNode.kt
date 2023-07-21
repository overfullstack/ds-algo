package ga.overfullstack.ds.tree

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import ga.overfullstack.ds.tree.TreeNode.Utils.JTree.Tree.JNode
import ga.overfullstack.utils.readFileToString
import java.util.ArrayDeque
import java.util.LinkedList

@JsonClass(generateAdapter = true)
data class TreeNode(
  var value: Int,
  var left: TreeNode? = null,
  var right: TreeNode? = null,
  var parent: TreeNode? = null,
  var next: TreeNode? = null,
  val id: String = ""
) {

  @Json(ignore = true) private var leftSize: Int = 0

  fun traversalAnyOrder(
    order: (Int, Array<Int>, Array<Int>) -> Array<Int> = { value, left, right ->
      arrayOf(value) + left + right
    }
  ): Array<Int> {
    val left = left?.traversalAnyOrder(order) ?: emptyArray()
    val right = right?.traversalAnyOrder(order) ?: emptyArray()
    return order(value, left, right)
  }

  fun inorderTraversal(): List<Int> =
    (left?.inorderTraversal()
      ?: emptyList()) + listOf(value) + (right?.inorderTraversal() ?: emptyList())

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

  companion object Utils {
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

    @JsonClass(generateAdapter = true)
    data class JTree(val tree: Tree) {
      @JsonClass(generateAdapter = true)
      data class Tree(val nodes: List<TreeNode>, val root: String) {
        @JsonClass(generateAdapter = true)
        data class JNode(val id: String, val left: String?, val right: String?, val value: Int)
      }
    }

    class NodeAdapter(val treeGraph: MutableMap<String, TreeNode> = mutableMapOf()) {
      @FromJson
      fun fromJson(jNode: JNode): TreeNode {
        val node = treeGraph[jNode.id] ?: TreeNode(id = jNode.id, value = Int.MIN_VALUE)
        node.value = jNode.value
        node.left = getNode(jNode.left)
        node.right = getNode(jNode.right)
        return node.also { treeGraph[jNode.id] = node }
      }

      private fun getNode(id: String?): TreeNode? =
        id?.let { treeGraph.computeIfAbsent(it) { TreeNode(id = id, value = Int.MIN_VALUE) } }
    }

    @SuppressWarnings("kotlin:S6611")
    @OptIn(ExperimentalStdlibApi::class)
    fun parseJsonFileToTree(jsonFilePath: String): TreeNode {
      val treeJson = readFileToString(jsonFilePath)
      val nodeAdapter = NodeAdapter()
      val treeAdapter = Moshi.Builder().add(nodeAdapter).build().adapter<JTree>()
      val tree = treeAdapter.fromJson(treeJson)!!
      return nodeAdapter.treeGraph[tree.tree.root]!!
    }
  }
}
