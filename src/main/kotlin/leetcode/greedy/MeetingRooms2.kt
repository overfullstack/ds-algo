package leetcode.greedy

@ExperimentalStdlibApi
fun minMeetingRoomsRequired(meetings: Array<Pair<Int, Int>>): Int {
    meetings.sortBy { it.first }
    val map =
        sortedMapOf<Int, Int>() // * sortedMap to auto-sort entries as per keys. SortedMap is an interface and TreeMap is Impl
    for (meeting in meetings) {
        map.merge(meeting.first, 1) { old, _ -> old.inc() }
        map.merge(meeting.second, -1) { old, _ -> old.dec() }
    }
    return map.values.runningReduce { count, curCount -> count + curCount }.maxOrNull() ?: 0
}
