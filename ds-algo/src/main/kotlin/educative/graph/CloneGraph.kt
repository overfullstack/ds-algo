package educative.graph

/* 11 Aug 2025 15:18 */

/** [133. Clone Graph](https://leetcode.com/problems/clone-graph/) */
fun cloneGraph(node: Node?): Node? {
  if (node == null) return null
  return cloneGraphInternal(node)
}

private fun cloneGraphInternal(
  node: Node,
  valToClonedNode: MutableMap<Int, Node> = mutableMapOf(),
): Node {
  val clonedNode = valToClonedNode.computeIfAbsent(node.`val`) { Node(it) }
  clonedNode.neighbors =
    node.neighbors
      .asSequence()
      .filterNotNull()
      .map { valToClonedNode[it.`val`] ?: cloneGraphInternal(it, valToClonedNode) }
      .toList()
  return clonedNode
}

class Node(var `val`: Int) {
  var neighbors: List<Node?> = ArrayList<Node?>()
}
