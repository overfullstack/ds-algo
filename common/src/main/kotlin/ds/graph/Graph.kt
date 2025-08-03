package ds.graph

/* 03 Aug 2025 22:40 */

/** Undirected Graph */
class Graph<T>(private val adjacencyMap: MutableMap<T, Set<T>> = mutableMapOf()) :
  MutableMap<T, Set<T>> by adjacencyMap {

  constructor(edges: List<Pair<T, T>>) : this() {
    edges.forEach { (node1, node2) -> addEdge(node1, node2) }
  }

  fun addEdge(node1: T, node2: T) {
    adjacencyMap.merge(node1, setOf(node2), Set<T>::plus)
    adjacencyMap.merge(node2, setOf(node1), Set<T>::plus)
  }
}
