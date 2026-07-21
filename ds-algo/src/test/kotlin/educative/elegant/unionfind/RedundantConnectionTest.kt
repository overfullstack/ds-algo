package educative.elegant.unionfind

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases
import utils.toPair

/* 16 Sep 2024 17:43 */
private const val PKG_PATH = "educative/graph/unionfind/RedundantConnection"

class RedundantConnectionTest :
  StringSpec({
    "redundant connection" {
      TestCases.load("$PKG_PATH/test-cases-1.json").map { case ->
        case.input<List<List<Int>>>(1).map { it.toPair() } to case.output<List<Int>>(1).toPair()
      }.forAll { (edges, result) ->
        redundantConnection(edges) shouldBe result
      }
    }
  })
