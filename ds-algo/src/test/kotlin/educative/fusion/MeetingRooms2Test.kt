package educative.fusion

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import leetcode.greedy.minMeetingRoomsRequired
import testcase.ListPairToInt.Companion.parseJsonFileToTestCases

/* 29 Jul 2025 23:14 */

private const val PKG_PATH = "educative/fusion/MeetingRooms2"

class MeetingRooms2Test :
  StringSpec({
    "Min meeting rooms required" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (meetings, result) ->
        minMeetingRoomsRequired(meetings.toTypedArray()) shouldBe result
      }
    }

    "Min meetings rooms required 2" {
      forAll(
        row(arrayOf(0 to 30, 5 to 10, 15 to 20), 2),
        row(arrayOf(0 to 10, 10 to 20, 20 to 30), 1),
      ) { meetings, result ->
        minMeetingRoomsRequired(meetings) shouldBe result
      }
    }
  })
