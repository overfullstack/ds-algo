package ds

import ga.overfullstack.ds.graph.DiGraph
import ga.overfullstack.ds.graph.DiGraphNode
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

val diGraph =
  DiGraph(
    mutableMapOf(
      DiGraphNode<Int>(0) to setOf(DiGraphNode<Int>(1), DiGraphNode<Int>(2), DiGraphNode<Int>(3), DiGraphNode<Int>(4)),
      DiGraphNode<Int>(1) to setOf(DiGraphNode<Int>(4)),
      DiGraphNode<Int>(2) to setOf(DiGraphNode<Int>(5)),
    )
  )

val diGraphWithCycle =
  DiGraph(
    mutableMapOf(
      DiGraphNode<Int>(0) to setOf(DiGraphNode<Int>(1)),
      DiGraphNode<Int>(1) to setOf(DiGraphNode<Int>(2)),
      DiGraphNode<Int>(2) to setOf(DiGraphNode<Int>(0)),
    )
  )

class DiGraphTest :
  StringSpec({
    "bfs" {
      diGraph.bfs(DiGraphNode<Int>(5)) shouldBe true
      diGraph.bfs(DiGraphNode<Int>(2)) shouldBe true
      diGraph.bfs(DiGraphNode<Int>(6)) shouldBe false
    }

    "dfs" {
      diGraph.bfs(DiGraphNode<Int>(5)) shouldBe true
      diGraph.bfs(DiGraphNode<Int>(2)) shouldBe true
      diGraph.bfs(DiGraphNode<Int>(6)) shouldBe false
    }

    "dft" {
      diGraph.dft() shouldContainExactly
        listOf(
          DiGraphNode<Int>(0),
          DiGraphNode<Int>(1),
          DiGraphNode<Int>(4),
          DiGraphNode<Int>(2),
          DiGraphNode<Int>(5),
          DiGraphNode<Int>(3)
        )
      diGraph.dft() shouldContainExactly
        listOf(
          DiGraphNode<Int>(0),
          DiGraphNode<Int>(1),
          DiGraphNode<Int>(4),
          DiGraphNode<Int>(2),
          DiGraphNode<Int>(5),
          DiGraphNode<Int>(3)
        )
    }

    "Digraph has Cycle" {
      diGraph.hasCycle() shouldBe false
      diGraphWithCycle.hasCycle() shouldBe true
    }

    "Topological sort" {
      diGraph.topologicalSort() shouldContainExactly
        listOf(
          DiGraphNode<Int>(4),
          DiGraphNode<Int>(1),
          DiGraphNode<Int>(5),
          DiGraphNode<Int>(2),
          DiGraphNode<Int>(3),
          DiGraphNode<Int>(0)
        )
      shouldThrow<IllegalArgumentException> { diGraphWithCycle.topologicalSort() }
    }

    "Topological sort 2" {
      val diGraph2 =
        DiGraph(
          mutableMapOf(
            DiGraphNode<Int>(6) to setOf(DiGraphNode<Int>(2), DiGraphNode<Int>(4)),
            DiGraphNode<Int>(4) to setOf(DiGraphNode<Int>(0)),
            DiGraphNode<Int>(5) to setOf(DiGraphNode<Int>(1), DiGraphNode<Int>(2)),
            DiGraphNode<Int>(3) to emptySet()
          )
        )
      diGraph2.topologicalSort() shouldContainExactly
        listOf(
          DiGraphNode<Int>(2),
          DiGraphNode<Int>(0),
          DiGraphNode<Int>(4),
          DiGraphNode<Int>(6),
          DiGraphNode<Int>(1),
          DiGraphNode<Int>(5),
          DiGraphNode<Int>(3)
        )
      shouldThrow<IllegalArgumentException> { diGraphWithCycle.topologicalSort() }
    }
  })
