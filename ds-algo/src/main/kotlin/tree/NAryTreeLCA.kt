/* gakshintala created on 9/4/19 */
package tree

import kotlin.math.ceil
import kotlin.math.sqrt

var blockSize: Int = 0

data class NAryTreeNode(var value: Int) {
  private val children: MutableList<NAryTreeNode?> = mutableListOf()
  var parent: NAryTreeNode? = null
  var jumpParent: NAryTreeNode? = null
  var depth: Int = 0

  fun addEdge(childNode: NAryTreeNode?) {
    childNode?.parent = this
    childNode?.depth = this.depth + 1
    childNode?.jumpParent =
      if (childNode?.depth?.rem(blockSize) ?: 0 == 0) this else parent?.jumpParent
    children.add(childNode)
  }
}

fun main() {
  val delimiters = ","
  val treeGraph =
    readln().split(delimiters).map { NAryTreeNode(it.toInt()) }.associateBy { it.value }
  blockSize = ceil(sqrt(treeGraph.size.toFloat())).toInt()
  val noOfEdges = readln().toInt()
  repeat(noOfEdges) {
    val (parent, child) = readln().split(delimiters).map { it.toInt() }
    treeGraph[parent]?.addEdge(treeGraph[child])
  }
  val noOfTests = readln().toInt()
  repeat(noOfTests) {
    val (node1, node2) = readln().split(delimiters).map { treeGraph[it.toInt()] }
    print(lcaWithSqrtDecomposition(node1, node2))
  }
}

fun lcaWithSqrtDecomposition(node1: NAryTreeNode?, node2: NAryTreeNode?): NAryTreeNode {
  var nodeOne = node1
  var nodeTwo = node2
  while (nodeOne?.jumpParent != nodeTwo?.jumpParent) {
    if (nodeOne?.depth!! > nodeTwo?.depth!!) {
      nodeOne = nodeOne.jumpParent
    } else {
      nodeTwo = nodeTwo.jumpParent
    }
  }
  return lca(nodeOne, nodeTwo)
}

fun lca(node1: NAryTreeNode?, node2: NAryTreeNode?): NAryTreeNode {
  return when {
    node1!! == node2!! -> return node1
    node1.depth > node2.depth -> lca(node1.parent, node2)
    node1.depth < node2.depth -> lca(node1, node2.parent)
    else -> lca(node1.parent, node2.parent)
  }
}
