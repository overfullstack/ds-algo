package leetcode.greedy

fun canAttendAllMeetings(meetings: Array<Pair<Int, Int>>): Boolean =
    meetings.sortedBy { it.first }.indices.asSequence().drop(1).all { meetings[it].first >= meetings[it - 1].second }