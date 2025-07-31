package educative.graph

import ds.graph.DiGraph

/* 15 Jul 2025 21:35 */
/** [815. Bus Routes](https://leetcode.com/problems/bus-routes/) */
fun numBusesToDestination(routes: Array<IntArray>, source: Int, destination: Int): Int {
  // * Think of it like a DB, where you are maintaining 2 tables -
  // graph(station:routeId), routes(routeId:connectedStations)
  // routeId is representing a group of nodes connected at same level, so unlike node to node
  // connection, we mapped node to route (group of nodes)
  // Valid moves from a station:
  // - Board any bus route passing through this station
  // - Once on a route, you can go to ANY station on that route
  // - This costs exactly 1 bus ride
  val graph = DiGraph<Int>()
  for ((routeId, stations) in routes.withIndex()) {
    stations.map { graph.addEdge(it, routeId) }
  }
  // * Find minimum route transfers to reach destination
  val pq = ArrayDeque<Pair<Int, Int>>()
  pq.add(source to 0)
  val visitedRouteIds = mutableSetOf<Int>()
  while (pq.isNotEmpty()) {
    val (station, busCount) = pq.removeLast()
    if (station == destination) {
      return busCount
    }
    graph[station]
      ?.filter { routeId -> routeId !in visitedRouteIds }
      ?.onEach { visitedRouteIds.add(it) }
      ?.flatMap { routeId -> routes[routeId].toList() } // Neighbours connected by a bus route
      ?.forEach { station -> pq.add((station to busCount + 1)) }
  }
  return -1
}
