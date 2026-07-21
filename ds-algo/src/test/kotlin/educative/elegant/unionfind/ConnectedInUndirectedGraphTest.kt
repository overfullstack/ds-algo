package educative.elegant.unionfind

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases
import utils.toPair

/* 22 Jul 2025 19:57 */

private const val PKG_PATH = "educative/graph/unionfind/ConnectedInUndirectedGraph"

class ConnectedInUndirectedGraphTest :
  StringSpec({
    "Connected Components in an Undirected Graph" {
      TestCases.load("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json").map { case ->
        (case.input<Int>(1) to case.input<List<List<Int>>>(2).map { it.toPair() }) to
          case.output<Int>(1)
      }.forAll { (input, output) ->
        val (n, edges) = input
        countComponents(n, edges) shouldBe output
      }
    }
  })
