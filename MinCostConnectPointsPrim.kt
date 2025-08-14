import java.util.PriorityQueue
import kotlin.math.abs

fun minCostConnectPointsPrim(points: Array<IntArray>): Int {
    val n = points.size
    
    // Edge case: if only one point, no connections needed
    if (n <= 1) return 0
    
    // Priority queue to store edges (cost, to_vertex)
    // We'll use a min-heap to always process the minimum cost edge
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
    
    // Track which vertices are already in the MST
    val inMST = BooleanArray(n) { false }
    
    // Start with vertex 0
    inMST[0] = true
    
    // Add all edges from vertex 0 to the priority queue
    for (j in 1 until n) {
        val cost = manhattanDistance(points[0], points[j])
        pq.add(cost to j)
    }
    
    var totalCost = 0
    var verticesInMST = 1
    
    // Continue until all vertices are in the MST
    while (verticesInMST < n && pq.isNotEmpty()) {
        val (cost, toVertex) = pq.poll()
        
        // If this vertex is already in MST, skip it
        if (inMST[toVertex]) continue
        
        // Add this vertex to MST
        inMST[toVertex] = true
        totalCost += cost
        verticesInMST++
        
        // Add all edges from the newly added vertex to vertices not in MST
        for (j in 0 until n) {
            if (!inMST[j]) {
                val newCost = manhattanDistance(points[toVertex], points[j])
                pq.add(newCost to j)
            }
        }
    }
    
    return totalCost
}

// Calculate Manhattan distance between two points
// Manhattan distance = |x1 - x2| + |y1 - y2|
private fun manhattanDistance(point1: IntArray, point2: IntArray): Int {
    val (x1, y1) = point1
    val (x2, y2) = point2
    return abs(x1 - x2) + abs(y1 - y2)
}
