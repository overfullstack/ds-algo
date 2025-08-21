import java.util.PriorityQueue

fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
  // Build adjacency list representation of the flight graph
  // Each city maps to a list of (destination, price) pairs
  val graph = Array(n) { mutableListOf<Pair<Int, Int>>() }
  for ((from, to, price) in flights) {
    graph[from].add(to to price)
  }

  // Priority queue stores (total_cost, current_city, stops_used)
  // We prioritize by cost to ensure we explore cheapest paths first
  val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.first })

  // Track minimum cost to reach each city with given number of stops
  // visited[city][stops] = minimum cost to reach city with exactly 'stops' stops
  val visited = Array(n) { IntArray(k + 2) { Int.MAX_VALUE } }

  // Start from source city with 0 cost and 0 stops
  pq.add(Triple(0, src, 0))
  visited[src][0] = 0

  while (pq.isNotEmpty()) {
    val (totalCost, currentCity, stopsUsed) = pq.poll()

    // If we've reached the destination, return the cost
    if (currentCity == dst) {
      return totalCost
    }

    // If we've used all allowed stops, skip this path
    if (stopsUsed > k) continue

    // If we've already found a better path to this city with same or fewer stops, skip
    if (totalCost > visited[currentCity][stopsUsed]) continue

    // Explore all flights from current city
    for ((nextCity, flightPrice) in graph[currentCity]) {
      val newCost = totalCost + flightPrice
      val newStops = stopsUsed + 1

      // Only update if this path is better than what we've seen before
      // and we haven't exceeded the stop limit
      if (newStops <= k + 1 && newCost < visited[nextCity][newStops]) {
        visited[nextCity][newStops] = newCost
        pq.add(Triple(newCost, nextCity, newStops))
      }
    }
  }

  // If we reach here, no valid path exists
  return -1
}
