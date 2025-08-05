package leetcode.graph

import java.util.*

/* 05 Aug 2025 20:42 */

/** [332. Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/) */
fun findItinerary(tickets: List<List<String>>): List<String> {
  val graph =
    tickets.groupBy({ it[0] }, { it[1] }).mapValues { PriorityQueue(it.value) }.toMutableMap()
  return dfsPostOrder("JFK", graph).reversed()
}

fun dfsPostOrder(airport: String, graph: MutableMap<String, PriorityQueue<String>>): List<String> =
  generateSequence { graph[airport]?.poll() }
    .flatMap { nextAirport -> dfsPostOrder(nextAirport, graph) }
    .toList() + airport

fun main() {
  println(
    findItinerary(
      listOf(listOf("MUC", "LHR"), listOf("JFK", "MUC"), listOf("SFO", "SJC"), listOf("LHR", "SFO"))
    )
  ) // ["JFK", "MUC", "LHR", "SFO", "SJC"]
  println(
    findItinerary(
      listOf(
        listOf("JFK", "SFO"),
        listOf("JFK", "ATL"),
        listOf("SFO", "ATL"),
        listOf("ATL", "JFK"),
        listOf("ATL", "SFO"),
      )
    )
  ) // ["JFK", "ATL", "JFK", "SFO", "ATL", "SFO"]
  // ! These are invalid inputs as the problem specifies there is a valid itinerary
  println(findItinerary(listOf(listOf("JFK", "B"), listOf("B", "C"), listOf("JFK", "D"))))
  println(findItinerary(listOf(listOf("JFK", "A"), listOf("JFK", "D"))))
}
