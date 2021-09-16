package ds

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

val diGraph = DiGraph(
  mutableMapOf(
    DiGraphNode(0) to setOf(DiGraphNode(1), DiGraphNode(2), DiGraphNode(3), DiGraphNode(4)),
    DiGraphNode(1) to setOf(DiGraphNode(4)),
    DiGraphNode(2) to setOf(DiGraphNode(5)),
  )
)

val diGraphWithCycle = DiGraph(
  mutableMapOf(
    DiGraphNode(0) to setOf(DiGraphNode(1)),
    DiGraphNode(1) to setOf(DiGraphNode(2)),
    DiGraphNode(2) to setOf(DiGraphNode(0)),
  )
)

class DiGraphTest : StringSpec({
  "bfs" {
    diGraph.bfs(DiGraphNode(5)) shouldBe true
    diGraph.bfs(DiGraphNode(2)) shouldBe true
    diGraph.bfs(DiGraphNode(6)) shouldBe false
  }

  "dfs" {
    diGraph.bfs(DiGraphNode(5)) shouldBe true
    diGraph.bfs(DiGraphNode(2)) shouldBe true
    diGraph.bfs(DiGraphNode(6)) shouldBe false
  }

  "dft" {
    diGraph.dft() shouldContainExactly listOf(
      DiGraphNode(0),
      DiGraphNode(1),
      DiGraphNode(4),
      DiGraphNode(2),
      DiGraphNode(5),
      DiGraphNode(3)
    )
    diGraph.dft() shouldContainExactly listOf(
      DiGraphNode(0),
      DiGraphNode(1),
      DiGraphNode(4),
      DiGraphNode(2),
      DiGraphNode(5),
      DiGraphNode(3)
    )
  }

  "Digraph has Cycle" {
    diGraph.hasCycle() shouldBe false
    diGraphWithCycle.hasCycle() shouldBe true
  }

  "Topological sort" {
    diGraph.topologicalSort() shouldContainExactly listOf(
      DiGraphNode(4),
      DiGraphNode(1),
      DiGraphNode(5),
      DiGraphNode(2),
      DiGraphNode(3),
      DiGraphNode(0)
    )
    shouldThrow<IllegalArgumentException> {
      diGraphWithCycle.topologicalSort()
    }
  }

  "Topological sort 2" {
    val diGraph2 = DiGraph(
      mutableMapOf(
        DiGraphNode(6) to setOf(DiGraphNode(2), DiGraphNode(4)),
        DiGraphNode(4) to setOf(DiGraphNode(0)),
        DiGraphNode(5) to setOf(DiGraphNode(1), DiGraphNode(2)),
        DiGraphNode(3) to emptySet()
      )
    )
    diGraph2.topologicalSort() shouldContainExactly listOf(
      DiGraphNode(2),
      DiGraphNode(0),
      DiGraphNode(4),
      DiGraphNode(6),
      DiGraphNode(1),
      DiGraphNode(5),
      DiGraphNode(3)
    )
    shouldThrow<IllegalArgumentException> {
      diGraphWithCycle.topologicalSort()
    }
  }
})
