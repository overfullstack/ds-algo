package hackerrank.graph

import hackerrank.graphs.minimumMoves
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

@ExperimentalStdlibApi
class CastleOnTheGridTest :
  StringSpec({
    "Castle on the Grid" {
      forAll(row(arrayOf(".X.", ".X.", "..."), 0, 0, 0, 2, 3)) {
        grid,
        startX,
        startY,
        goalX,
        goalY,
        result ->
        minimumMoves(grid, startX, startY, goalX, goalY) shouldBe result
      }
    }
  })
