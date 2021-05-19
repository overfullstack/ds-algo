package ds

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

val diGraph = DiGraph(
    mutableMapOf(
        0 to setOf(1, 2, 3, 4),
        1 to setOf(4),
        2 to setOf(5),
    )
)

val diGraphWithCycle = DiGraph(
    mutableMapOf(
        0 to setOf(1),
        1 to setOf(2),
        2 to setOf(0),
    )
)

class DiGraphTest : StringSpec({
    "bfs" {
        diGraph.bfs(5) shouldBe true
        diGraph.bfs(2) shouldBe true
        diGraph.bfs(6) shouldBe false
    }

    "dfs" {
        diGraph.bfs(5) shouldBe true
        diGraph.bfs(2) shouldBe true
        diGraph.bfs(6) shouldBe false
    }

    "dft" {
        diGraph.dft() shouldContainExactly listOf(0, 1, 4, 2, 5, 3)
        diGraph.dft() shouldContainExactly listOf(0, 1, 4, 2, 5, 3)
    }

    "Digraph has Cycle" {
        diGraph.hasCycle() shouldBe false
        diGraphWithCycle.hasCycle() shouldBe true
    }

    "Topological sort" {
        diGraph.topologicalSort() shouldContainExactly listOf(4, 1, 5, 2, 3, 0)
        shouldThrow<IllegalArgumentException> {
            diGraphWithCycle.topologicalSort()
        }
    }
})
