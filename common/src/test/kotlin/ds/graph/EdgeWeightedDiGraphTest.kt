package ds.graph

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

/* 30 Jul 2025 17:19 */

class EdgeWeightedDiGraphTest :
  StringSpec({
    "dijkstraShortestPath should find shortest paths in simple connected graph" {
      val graph = EdgeWeightedDiGraph<Int>()
      graph.addEdge(1, 2, 4)
      graph.addEdge(1, 3, 2)
      graph.addEdge(2, 4, 3)
      graph.addEdge(3, 2, 1)
      graph.addEdge(3, 4, 5)

      val distances = graph.dijkstraShortestPath(1)

      distances[1] shouldBe 0
      distances[2] shouldBe 3 // 1 -> 3 -> 2 (2 + 1 = 3)
      distances[3] shouldBe 2 // 1 -> 3 (2)
      distances[4] shouldBe 6 // 1 -> 3 -> 2 -> 4 (2 + 1 + 3 = 6)
    }

    "dijkstraShortestPath should handle unreachable nodes" {
      val graph = EdgeWeightedDiGraph<Int>()
      graph.addEdge(1, 2, 5)
      graph.addEdge(3, 4, 2)

      val distances = graph.dijkstraShortestPath(1)

      distances[1] shouldBe 0
      distances[2] shouldBe 5
      distances.containsKey(3) shouldBe false // unreachable
      distances.containsKey(4) shouldBe false // unreachable
    }

    "dijkstraShortestPath should work with single node graph" {
      val graph = EdgeWeightedDiGraph<Int>()
      // Just add a node to the adjacency map by calling addEdge and then removing it
      graph.addEdge(1, 2, 1)
      graph.removeNode(1)
      graph.addNode(5)

      val distances = graph.dijkstraShortestPath(5)

      distances.containsKey(2) shouldBe false
      distances[5] shouldBe 0
    }

    "dijkstraShortestPath should handle cycles correctly" {
      val graph = EdgeWeightedDiGraph<Int>()
      graph.addEdge(1, 2, 1)
      graph.addEdge(2, 3, 1)
      graph.addEdge(3, 1, 1) // cycle
      graph.addEdge(2, 4, 5)
      graph.addEdge(3, 4, 2)

      val distances = graph.dijkstraShortestPath(1)

      distances[1] shouldBe 0
      distances[2] shouldBe 1 // 1 -> 2
      distances[3] shouldBe 2 // 1 -> 2 -> 3
      distances[4] shouldBe 4 // 1 -> 2 -> 3 -> 4 (shorter than 1 -> 2 -> 4)
    }

    "dijkstraShortestPath should work with string vertices" {
      val graph = EdgeWeightedDiGraph<String>()
      graph.addEdge("A", "B", 4)
      graph.addEdge("A", "C", 2)
      graph.addEdge("B", "D", 3)
      graph.addEdge("C", "B", 1)
      graph.addEdge("C", "D", 5)

      val distances = graph.dijkstraShortestPath("A")

      distances["A"] shouldBe 0
      distances["B"] shouldBe 3 // A -> C -> B
      distances["C"] shouldBe 2 // A -> C
      distances["D"] shouldBe 6 // A -> C -> B -> D
    }

    "dijkstraShortestPath should handle zero weight edges" {
      val graph = EdgeWeightedDiGraph<Int>()
      graph.addEdge(1, 2, 0)
      graph.addEdge(2, 3, 0)
      graph.addEdge(1, 3, 5)

      val distances = graph.dijkstraShortestPath(1)

      distances[1] shouldBe 0
      distances[2] shouldBe 0 // 1 -> 2 (weight 0)
      distances[3] shouldBe 0 // 1 -> 2 -> 3 (both weights 0)
    }

    "dijkstraShortestPath should work with constructor that takes triples" {
      val edges =
        listOf(Triple(1, 2, 4), Triple(1, 3, 2), Triple(2, 4, 3), Triple(3, 2, 1), Triple(3, 4, 5))
      val graph = EdgeWeightedDiGraph(edges)

      val distances = graph.dijkstraShortestPath(1)

      distances[1] shouldBe 0
      distances[2] shouldBe 3 // 1 -> 3 -> 2
      distances[3] shouldBe 2 // 1 -> 3
      distances[4] shouldBe 6 // 1 -> 3 -> 2 -> 4
    }
  })
