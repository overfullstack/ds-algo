package leetcode.graph

import kotlin.math.pow

/**
 * De Bruijn sequence generation using functional DFS
 * [753. Cracking the Safe](https://leetcode.com/problems/cracking-the-safe/)
 */
fun crackSafe(n: Int, k: Int): String =
  when (n) {
    1 -> (0 until k).joinToString("")
    else -> {
      val startNode = "0".repeat(n - 1)
      // Find path that visits all k^n edges (passwords)
      findDeBruijnPath(startNode, k, k.toDouble().pow(n.toDouble()).toInt())
    }
  }

private fun findDeBruijnPath(
  node: String,
  k: Int,
  targetEdgeCount: Int,
  // ! visitedEdges should track n-digit passwords (edges), not (n-1)-digit nodes.
  visitedEdges: Set<String> = emptySet(),
  path: String = node,
): String =
  when (visitedEdges.size) {
    targetEdgeCount -> path // All passwords visited
    else ->
      (k - 1 downTo 0)
        .asSequence()
        .map { digit -> node + digit }
        .firstOrNull { edge -> edge !in visitedEdges }
        ?.let { edge ->
          findDeBruijnPath(
            edge.substring(1),
            k,
            targetEdgeCount,
            visitedEdges + edge,
            path + edge.last(),
          )
        } ?: path // Backtrack if dead end
  }

fun main() {
  println(crackSafe(1, 2)) // "01" or "10"
  println(crackSafe(2, 2)) // "00110" or similar
  println(crackSafe(3, 2)) // "0011101000" or similar
}
