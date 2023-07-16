package leetcode.greedy

/** 🔒 https://leetcode.com/problems/meeting-rooms-ii */
fun minMeetingRoomsRequired(meetings: Array<Pair<Int, Int>>): Int {
  meetings.sortBy { it.first } // In-place sorting
  // * `sortedMap` to auto-sort entries as per keys. SortedMap is an interface and TreeMap is Impl
  // * This stores net meetings started
  val map = sortedMapOf<Int, Int>() // ! This is a SortedMap
  for (meeting in meetings) {
    map.merge(meeting.first, 1) { old, _ -> old.inc() }
    map.merge(meeting.second, -1) { old, _ -> old.dec() }
  }
  return map.values.runningReduce(Int::plus).maxOrNull() ?: 0
}
