package ds.graph

import ds.graph.EdgeWeightedDiGraph.WeightedEdge
import java.util.*

/* 15 Jul 2025 15:36 */

class EdgeWeightedDiGraph<T>(
  private val adjacencyMap: MutableMap<T, Set<WeightedEdge<T>>> = mutableMapOf()
) : MutableMap<T, Set<WeightedEdge<T>>> by adjacencyMap {

  constructor(data: List<Triple<T, T, Int>>) : this() {
    data.forEach { (source, destination, weight) -> addEdge(source, destination, weight) }
  }

  fun addEdge(source: T, destination: T, weight: Int) {
    adjacencyMap.merge(source, setOf(WeightedEdge(destination, weight)), Set<WeightedEdge<T>>::plus)
  }

  fun removeVertex(vertex: T) {
    adjacencyMap.remove(vertex)
  }

  fun addVertex(vertex: T) {
    adjacencyMap[vertex] = emptySet()
  }

  fun getNeighbours(node: T): Set<WeightedEdge<T>>? = adjacencyMap[node]

  data class WeightedEdge<T>(val destination: T, val weight: Int)

  fun dijkstraShortestPath(source: T): Map<T, Int> {
    val nodeToDistanceFromSource = mutableMapOf<T, Int>()
    val pq = PriorityQueue(Comparator.comparingInt<Pair<T, Int>> { it.second })
    pq.add(source to 0)
    while (pq.isNotEmpty()) {
      val (node, distanceFromSource) = pq.poll()
      // ! This is only for optimization, but it works without this
      if (node in nodeToDistanceFromSource) continue
      nodeToDistanceFromSource[node] = distanceFromSource
      adjacencyMap[node]?.asSequence()?.forEach { (to, distanceFromNodeToTo) ->
        val newDistance = distanceFromSource + distanceFromNodeToTo
        // ! This takes care of cycle even without visited
        if (nodeToDistanceFromSource[to]?.let { newDistance < it } ?: true) {
          pq.add(to to newDistance)
        }
      }
    }
    return nodeToDistanceFromSource
  }
}
