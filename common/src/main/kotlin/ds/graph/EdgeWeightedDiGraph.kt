package ds.graph

import ds.graph.EdgeWeightedDiGraph.WeightedEdge
import java.util.*

/* 15 Jul 2025 15:36 */

class EdgeWeightedDiGraph<T>(
  private val isNodeTypePrimitive: Boolean = true,
  private val adjacencyMap: MutableMap<T, Set<WeightedEdge<T>>> = mutableMapOf(),
) : MutableMap<T, Set<WeightedEdge<T>>> by adjacencyMap {

  data class WeightedEdge<T>(val destination: T, val weight: Int)

  constructor(
    data: List<Triple<T, T, Int>>,
    isNodeTypePrimitive: Boolean = true,
  ) : this(isNodeTypePrimitive) {
    data.forEach { (source, destination, weight) -> addEdge(source, destination, weight) }
  }

  constructor(
    data: Array<IntArray>,
    isNodeTypePrimitive: Boolean = true,
  ) : this(isNodeTypePrimitive) {
    data.forEach { (source, destination, weight) -> addEdge(source as T, destination as T, weight) }
  }

  // ! TODO 01 Sep 2025 gopala.akshintala: Improve perf for isNodeTypePrimitive = false
  val allNodes: Set<T>
    get() = adjacencyMap.keys + adjacencyMap.values.flatten().map { it.destination }

  fun addEdge(source: T, destination: T, weight: Int) {
    val actualDestination =
      if (isNodeTypePrimitive) destination
      else allNodes.firstOrNull { it == destination } ?: destination
    adjacencyMap.merge(
      source,
      setOf(WeightedEdge(actualDestination, weight)),
      Set<WeightedEdge<T>>::plus,
    )
  }

  fun removeNode(node: T) {
    adjacencyMap.remove(node)
  }

  fun addNode(vertex: T) {
    adjacencyMap[vertex] = emptySet()
  }

  fun getNeighbours(node: T): Set<WeightedEdge<T>>? = adjacencyMap[node]

  fun dijkstraShortestPath(source: T): Map<T, Int> {
    val nodeToDistanceFromSource = mutableMapOf<T, Int>()
    val pq = PriorityQueue(compareBy<Pair<T, Int>> { it.second })
    pq.add(source to 0)
    while (pq.isNotEmpty()) {
      val (from, distanceFromSource) = pq.poll()
      nodeToDistanceFromSource[from] = distanceFromSource
      // * Loop through Neighbour edges
      adjacencyMap[from]?.forEach { (to, distanceFromNodeToTo) ->
        val newDistance = distanceFromSource + distanceFromNodeToTo
        // ! Priority Queue make sure a from node gets best distance in the first visit.
        // ! This check prevents the same from being added to queue again for processing
        if (nodeToDistanceFromSource[to]?.let { newDistance < it } ?: true) {
          pq.add(to to newDistance)
        }
      }
    }
    return nodeToDistanceFromSource
  }
}
