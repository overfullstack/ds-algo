package ds.graph

/* 03 Aug 2025 22:40 */

/** Undirected Graph */
class BiDiGraph<T>(
  private val isNodeTypePrimitive: Boolean = true,
  private val adjacencyMap: MutableMap<T, Set<T>> = mutableMapOf(),
) : MutableMap<T, Set<T>> by adjacencyMap {

  val allNodes: Set<T>
    get() = adjacencyMap.keys + adjacencyMap.values.flatten()

  constructor(
    edges: List<Pair<T, T>>,
    isNodeTypePrimitive: Boolean = true,
  ) : this(isNodeTypePrimitive) {
    edges.forEach { (node1, node2) -> addEdge(node1, node2) }
  }

  fun addEdge(node1: T, node2: T) {
    val actualNode1 =
      if (isNodeTypePrimitive) node1 else allNodes.firstOrNull { it == node1 } ?: node1
    val actualNode2 =
      if (isNodeTypePrimitive) node2 else allNodes.firstOrNull { it == node2 } ?: node2
    adjacencyMap.merge(actualNode1, setOf(actualNode2), Set<T>::plus)
    adjacencyMap.merge(actualNode2, setOf(actualNode1), Set<T>::plus)
  }
}

data class TarjanNode(val value: Int) {
  var discovery = 0 // rank in order of discovery
  var low = 0 // lowest rank reachable from this node
}
