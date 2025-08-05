package leetcode.graph

import educative.graph.criticalConnections
import educative.graph.criticalConnectionsOptimized
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

/* 05 Aug 2025 15:02 */

class CriticalConnectionsInNetworkTest :
  StringSpec({
    "critical connections in network - basic cases (optimized)" {
      forAll(
        // Test case 1: Simple bridge
        row(
          4,
          listOf(listOf(0, 1), listOf(1, 2), listOf(2, 0), listOf(1, 3)),
          listOf(listOf(1, 3)),
        ),

        // Test case 2: Linear chain - all edges are bridges
        row(
          4,
          listOf(listOf(0, 1), listOf(1, 2), listOf(2, 3)),
          listOf(listOf(0, 1), listOf(1, 2), listOf(2, 3)),
        ),

        // Test case 3: Complete cycle - no bridges
        row(
          4,
          listOf(listOf(0, 1), listOf(1, 2), listOf(2, 3), listOf(3, 0)),
          emptyList<List<Int>>(),
        ),

        // Test case 4: Two nodes with single edge
        row(2, listOf(listOf(0, 1)), listOf(listOf(0, 1))),

        // Test case 5: Complex graph with multiple bridges
        row(
          6,
          listOf(
            listOf(0, 1),
            listOf(1, 2),
            listOf(2, 0), // Triangle
            listOf(1, 3), // Bridge
            listOf(3, 4),
            listOf(4, 5),
            listOf(5, 3), // Another triangle
          ),
          listOf(listOf(1, 3)),
        ),

        // Test case 6: Star graph - all edges are bridges
        row(
          5,
          listOf(listOf(0, 1), listOf(0, 2), listOf(0, 3), listOf(0, 4)),
          listOf(listOf(0, 1), listOf(0, 2), listOf(0, 3), listOf(0, 4)),
        ),

        // Test case 7: Two triangles connected by bridge
        row(
          6,
          listOf(
            listOf(0, 1),
            listOf(1, 2),
            listOf(2, 0), // First triangle
            listOf(2, 3), // Bridge
            listOf(3, 4),
            listOf(4, 5),
            listOf(5, 3), // Second triangle
          ),
          listOf(listOf(2, 3)),
        ),

        // Test case 8: Single node (edge case)
        row(1, emptyList<List<Int>>(), emptyList<List<Int>>()),

        // Test case 9: Disconnected components with bridges
        row(
          5,
          listOf(
            listOf(0, 1), // Bridge in first component
            listOf(2, 3),
            listOf(3, 4), // Bridges in second component
          ),
          listOf(listOf(0, 1), listOf(2, 3), listOf(3, 4)),
        ),

        // Test case 10: Complex case with nested cycles
        row(
          7,
          listOf(
            listOf(0, 1),
            listOf(1, 2),
            listOf(2, 0), // Outer triangle
            listOf(1, 3),
            listOf(3, 4),
            listOf(4, 1), // Inner triangle
            listOf(0, 5), // Bridge to isolated part
            listOf(5, 6), // Another bridge
          ),
          listOf(listOf(0, 5), listOf(5, 6)),
        ),
      ) { n: Int, connections: List<List<Int>>, expected: List<List<Int>> ->
        val result: List<List<Int>> = criticalConnectionsOptimized(n, connections)
        result shouldContainExactlyInAnyOrder expected
      }
    }

    "critical connections - edge cases" {
      forAll(
        // Empty graph
        row(0, emptyList<List<Int>>(), emptyList<List<Int>>()),

        // Large triangle (no bridges)
        row(100, (0 until 100).map { listOf(it, (it + 1) % 100) }, emptyList<List<Int>>()),
      ) { n: Int, connections: List<List<Int>>, expected: List<List<Int>> ->
        val result: List<List<Int>> = criticalConnectionsOptimized(n, connections)
        result shouldContainExactlyInAnyOrder expected
      }
    }

    "critical connections - performance test cases" {
      forAll(
        // Tree structure (all edges are bridges)
        row(
          10,
          listOf(
            listOf(0, 1),
            listOf(1, 2),
            listOf(1, 3),
            listOf(2, 4),
            listOf(2, 5),
            listOf(3, 6),
            listOf(3, 7),
            listOf(4, 8),
            listOf(4, 9),
          ),
          listOf(
            listOf(0, 1),
            listOf(1, 2),
            listOf(1, 3),
            listOf(2, 4),
            listOf(2, 5),
            listOf(3, 6),
            listOf(3, 7),
            listOf(4, 8),
            listOf(4, 9),
          ),
        )
      ) { n: Int, connections: List<List<Int>>, expected: List<List<Int>> ->
        val result: List<List<Int>> = criticalConnections(n, connections)
        result shouldContainExactlyInAnyOrder expected
      }
    }
  })
