package ds.graph

import ds.graph.DiGraph.Companion.parseJsonFileToDiGraph
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

private const val PKG_PATH = "graph"

val diGraph =
  DiGraph(mutableMapOf(0 to setOf(1, 2, 3, 4), 1 to setOf(4), 2 to setOf(5), 6 to setOf(7, 8, 9)))

val diGraphWithCycle = DiGraph(mutableMapOf(0 to setOf(1), 1 to setOf(2), 2 to setOf(0)))

class DiGraphTest :
  StringSpec({
    "bfs" {
      diGraph.bfs(5) shouldBe true
      diGraph.bfs(2) shouldBe true
      diGraph.bfs(6) shouldBe true
      diGraph.bfs(10) shouldBe false
    }

    "dfs" {
      diGraph.dfs(5) shouldBe true
      diGraph.dfs(2) shouldBe true
      diGraph.dfs(6) shouldBe true
      diGraph.dfs(10) shouldBe false
    }

    "diGraph has Cycle" {
      diGraph.hasCycle() shouldBe false
      diGraphWithCycle.hasCycle() shouldBe true
    }

    "topological sort" {
      diGraph.topologicalSort() shouldContainExactly listOf(4, 1, 5, 2, 3, 0, 7, 8, 9, 6)
      shouldThrow<IllegalArgumentException> { diGraphWithCycle.topologicalSort() }
    }

    "topological sort 2" {
      val diGraph2 =
        DiGraph(mutableMapOf(6 to setOf(2, 4), 4 to setOf(0), 5 to setOf(1, 2), 3 to emptySet()))
      diGraph2.topologicalSort() shouldContainExactly listOf(2, 0, 4, 6, 1, 5, 3)
      shouldThrow<IllegalArgumentException> { diGraphWithCycle.topologicalSort() }
    }

    "dft" { diGraph.dft() shouldContainExactly listOf(0, 1, 4, 2, 5, 3, 6, 7, 8, 9) }

    "dft with DiGraph JSON" {
      forAll(
        row(parseJsonFileToDiGraph("$PKG_PATH/graph2.json"), listOf("A", "B", "D", "C")),
        row(
          parseJsonFileToDiGraph("$PKG_PATH/graph1.json"),
          listOf("A", "B", "E", "F", "I", "J", "C", "D", "G", "K", "H"),
        ),
        row(
          parseJsonFileToDiGraph("$PKG_PATH/graph3.json"),
          listOf(
            "A",
            "B",
            "G",
            "H",
            "O",
            "P",
            "T",
            "U",
            "Q",
            "R",
            "V",
            "W",
            "X",
            "Z",
            "Y",
            "I",
            "C",
            "J",
            "D",
            "K",
            "S",
            "L",
            "E",
            "F",
            "M",
            "N",
          ),
        ),
      ) { graph, result ->
        graph.dft() shouldContainExactly result
      }
    }

    "dft group size with DiGraph JSON" {
      forAll(
        row(parseJsonFileToDiGraph("$PKG_PATH/graph2.json"), listOf("A", "B", "D", "C").size),
        row(
          parseJsonFileToDiGraph("$PKG_PATH/graph1.json"),
          listOf("A", "B", "E", "F", "I", "J", "C", "D", "G", "K", "H").size,
        ),
        row(
          parseJsonFileToDiGraph("$PKG_PATH/graph3.json"),
          listOf(
              "A",
              "B",
              "G",
              "H",
              "O",
              "P",
              "T",
              "U",
              "Q",
              "R",
              "V",
              "W",
              "X",
              "Z",
              "Y",
              "I",
              "C",
              "J",
              "D",
              "K",
              "S",
              "L",
              "E",
              "F",
              "M",
              "N",
            )
            .size,
        ),
      ) { graph, result ->
        graph.dftGroupsSize() shouldContainExactly listOf(result)
      }
    }
  })
