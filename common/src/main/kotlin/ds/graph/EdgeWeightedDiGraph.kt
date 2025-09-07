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
    val minDistanceNodeFromSource = mutableMapOf<T, Int>()
    val pq = PriorityQueue(compareBy<Pair<T, Int>> { it.second })
    pq.add(source to 0)
    minDistanceNodeFromSource[source] = 0 // ! Critical initialization
    while (pq.isNotEmpty()) {
      val (from, distanceFromSource) = pq.poll()
      // ! `pq` may contain the same node with farther distance
      // ! so this check prevents from processing and recording a wrong distance
      // ! The equals in `<=` is only needed for the first dequeue where `from == source`
      if (distanceFromSource <= minDistanceNodeFromSource.getOrDefault(from, Int.MAX_VALUE)) {
        adjacencyMap[from]?.forEach { (to, weight) ->
          val newDistance = distanceFromSource + weight
          // ! This is an Optimization check to prevent redundant entries in `pq`
          // ! `newDistance <` counters the above `distanceFromSource <=` in preventing redundancy
          if (newDistance < minDistanceNodeFromSource.getOrDefault(to, Int.MAX_VALUE)) {
            // ! Keeping the `minDistanceNodeFromSource` current leads to 
            // ! better pruning and lesser `pq` size compared to `dijkstraShortestPathClassic`
            minDistanceNodeFromSource[to] = newDistance
            pq.add(to to newDistance)
          }
        }
      }
    }
    return minDistanceNodeFromSource
  }

  fun dijkstraShortestPathClassic(source: T): Map<T, Int> {
    val minDistanceNodeFromSource = mutableMapOf<T, Int>()
    val pq = PriorityQueue(compareBy<Pair<T, Int>> { it.second })
    pq.add(source to 0)
    while (pq.isNotEmpty()) {
      val (from, distanceFromSource) = pq.poll()
      // ! `pq` may contain the same node with farther distance
      // ! so this check prevents from processing and recording a wrong distance
      if (distanceFromSource < minDistanceNodeFromSource.getOrDefault(from, Int.MAX_VALUE)) {
        minDistanceNodeFromSource[from] = distanceFromSource
        adjacencyMap[from]?.forEach { (to, weight) ->
          val newDistance = distanceFromSource + weight
          // ! This is an Optimization check to prevent redundant entries in `pq`
          if (newDistance < minDistanceNodeFromSource.getOrDefault(to, Int.MAX_VALUE)) {
            pq.add(to to newDistance)
          }
        }
      }
    }
    return minDistanceNodeFromSource
  }
}
