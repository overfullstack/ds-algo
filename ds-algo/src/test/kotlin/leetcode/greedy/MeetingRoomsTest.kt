package leetcode.greedy

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MeetingRooms1Test :
  StringSpec({
    "Can attend all meetings" {
      forAll(
        row(arrayOf(0 to 30, 5 to 10, 15 to 20), false),
        row(arrayOf(0 to 10, 10 to 20, 20 to 30), true),
      ) { meetings, result ->
        canAttendAllMeetings(meetings) shouldBe result
      }
    }
  })
