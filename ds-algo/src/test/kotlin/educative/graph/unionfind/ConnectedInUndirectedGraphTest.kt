package educative.graph.unionfind

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.IntListPairIntInt.Companion.parseJsonFileToTestCases

/* 22 Jul 2025 19:57 */

private const val PKG_PATH = "educative/graph/unionfind/ConnectedInUndirectedGraph"

class ConnectedInUndirectedGraphTest :
  StringSpec({
    "Connected Components in an Undirected Graph" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (input, output) ->
          val (n, edges) = input
          countComponents(n, edges) shouldBe output
        }
    }
  })
