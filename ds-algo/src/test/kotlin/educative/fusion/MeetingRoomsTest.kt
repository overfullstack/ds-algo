package educative.fusion

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import leetcode.greedy.canAttendAllMeetings
import testcase.ListPairToBoolean.Companion.parseJsonFileToTestCases

/* 29 Jul 2025 23:14 */

private const val PKG_PATH = "educative/fusion/MeetingRooms"

class MeetingRoomsTest :
  StringSpec({
    "Can attend all meetings" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (meetings, result) ->
          canAttendAllMeetings(meetings.toTypedArray()) shouldBe result
        }
    }

    "Can attend all meetings 2" {
      forAll(
        row(arrayOf(0 to 30, 5 to 10, 15 to 20), false),
        row(arrayOf(0 to 10, 10 to 20, 20 to 30), true),
      ) { meetings, result ->
        canAttendAllMeetings(meetings) shouldBe result
      }
    }
  })
