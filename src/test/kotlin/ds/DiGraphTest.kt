package ds

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

val diGraph = DiGraph(mutableMapOf(
    0 to mutableSetOf(1,2,3,4),
    1 to mutableSetOf(4),
    2 to mutableSetOf(5),
))

val diGraphWithCycle = DiGraph(mutableMapOf(
    0 to mutableSetOf(1),
    1 to mutableSetOf(2),
    2 to mutableSetOf(0),
))

class DiGraphTest: StringSpec({
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

    "Digraph has Cycle" {
        diGraph.hasCycle() shouldBe false
        diGraphWithCycle.hasCycle() shouldBe true
    }
})
