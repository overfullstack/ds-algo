package educative.graph.unionfind

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCase13.Companion.parseJsonFileToTestCases

/* 16 Sep 2024 17:43 */
private const val PKG_PATH = "educative/graph/unionfind/RedundantConnection"

class RedundantConnectionTest :
  StringSpec({
    "redundant connection" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (edges, result) ->
        redundantConnection(edges) shouldBe result
      }
    }
  })
