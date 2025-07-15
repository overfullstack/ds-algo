package educative.graph

import ds.graph.DiGraph

/* 15 Jul 2025 21:35 */

fun minimumNumberOfBuses(routes: List<List<Int>>, source: Int, destination: Int): Int {
  // * Think of it like a DB, where you are maintaining 2 tables -
  // graph(station:routeId), routes(routeId:connectedStations)
  // routeId is representing a group of nodes connected at same level, so unlike node to node
  // connection, we mapped node to route (group of nodes)
  val graph = DiGraph<Int>()
  for ((busRouteId, stops) in routes.withIndex()) {
    stops.map { graph.addEdge(it, busRouteId) }
  }
  // We don't need greedy here, as there are no weights
  val pq = ArrayDeque<Pair<Int, Int>>()
  pq.add(source to 0)
  val visitedBuses = mutableSetOf<Int>()
  while (pq.isNotEmpty()) {
    val (station, busCount) = pq.removeLast()
    if (station == destination) {
      return busCount
    }
    graph[station]
      ?.filter { busRouteId -> busRouteId !in visitedBuses }
      ?.onEach { visitedBuses.add(it) }
      ?.flatMap { busRouteId -> routes[busRouteId] } // Neighbours connected by a bus route
      ?.forEach { station -> pq.add((station to busCount + 1)) }
  }
  return -1
}
