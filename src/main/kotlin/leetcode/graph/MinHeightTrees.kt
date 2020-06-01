/* gakshintala created on 5/24/20 */
package leetcode.graph

import ds.DiGraph
import ds.DiGraphNode

fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
    val graph = DiGraph()
    edges.forEach {
        graph.addEdge(it[0], it[1])
        graph.addEdge(it[1], it[0])
    }
    val visited = mutableSetOf<Int>()
    return emptyList() // Incomplete
}



