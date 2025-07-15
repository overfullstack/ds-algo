package ds.graph

import ds.graph.EdgeWeightedDiGraph.WeightedEdge

/* 15 Jul 2025 15:36 */

class EdgeWeightedDiGraph<T>(
  private val adjacencyMap: MutableMap<T, Set<WeightedEdge<T>>> = mutableMapOf()
) : MutableMap<T, Set<WeightedEdge<T>>> by adjacencyMap {

  data class WeightedEdge<T>(val source: T, val destination: T, val weight: Int)

  fun addEdge(source: T, destination: T, weight: Int) {
    adjacencyMap.merge(
      source,
      setOf(WeightedEdge(source, destination, weight)),
      Set<WeightedEdge<T>>::plus,
    )
  }

  fun getNeighbours(node: T): Set<WeightedEdge<T>>? = adjacencyMap[node]

  constructor(data: List<Triple<T, T, Int>>) : this() {
    data.forEach { (source, destination, weight) -> addEdge(source, destination, weight) }
  }
}
