package educative.graph

import ds.graph.DiGraph

/* 15 Jul 2025 21:35 */

/** [815. Bus Routes](https://leetcode.com/problems/bus-routes/) */
fun numBusesToDestination(routes: Array<IntArray>, source: Int, destination: Int): Int { // * BFS
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
  val queue = ArrayDeque<Pair<Int, Int>>()
  queue.add(source to 0)
  val visitedRouteIds = mutableSetOf<Int>()
  while (queue.isNotEmpty()) {
    val (station, busCount) = queue.removeFirst()
    if (station == destination) {
      return busCount
    }
    graph[station]
      ?.filter { routeId -> routeId !in visitedRouteIds }
      ?.onEach { visitedRouteIds.add(it) } // ! Visit-on-Enqueue for BFS
      ?.flatMap { routeId -> routes[routeId].toList() } // ! Neighbors connected by a bus route
      ?.forEach { station -> queue.add(station to busCount + 1) }
  }
  return -1
}

fun main() {
  val routes =
    arrayOf(
      intArrayOf(0, 1, 6, 16, 22, 23),
      intArrayOf(14, 15, 24, 32),
      intArrayOf(4, 10, 12, 20, 24, 28, 33),
      intArrayOf(1, 10, 11, 19, 27, 33),
      intArrayOf(11, 23, 25, 28),
      intArrayOf(15, 20, 21, 23, 29),
      intArrayOf(29),
    )
  println(numBusesToDestination(routes, 4, 21)) // 2
}
