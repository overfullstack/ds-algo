package leetcode.greedy

/** 🔒 https://leetcode.com/problems/meeting-rooms/ */
fun canAttendAllMeetings(meetings: Array<Pair<Int, Int>>): Boolean =
  meetings
    .sortedBy { it.first }
    .zipWithNext()
    .all { (meeting1, meeting2) -> meeting1.second <= meeting2.first }
