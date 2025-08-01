package leetcode.greedy

import java.util.PriorityQueue

/** 🔒 https://leetcode.ca/2016-08-09-253-Meeting-Rooms-II/ */
fun minMeetingRoomsRequired(meetings: Array<Pair<Int, Int>>): Int {
  meetings.sortBy { it.first } // In-place sorting
  // * `sortedMap` to auto-sort entries as per keys. SortedMap is an interface and TreeMap is Impl
  // * This stores net meetings started
  val map = sortedMapOf<Int, Int>() // ! This is a SortedMap
  for (meeting in meetings) {
    map.merge(meeting.first, 1, Int::plus) // * A meeting started
    map.merge(meeting.second, -1, Int::plus) // * A meeting ended
  }
  return map.values.runningReduce(Int::plus).maxOrNull() ?: 0
}

fun minMeetingRoomsRequired2(meetings: Array<Pair<Int, Int>>): Int {
  val minEndTimeHeap = PriorityQueue<Int>()
  val sortedMeetings = meetings.sortedBy { it.first }
  minEndTimeHeap.add(sortedMeetings.first().second)
  for (meeting in sortedMeetings.drop(1)) {
    if (meeting.first >= minEndTimeHeap.peek()) { // Non-overlapping
      minEndTimeHeap.poll() // * Meeting room available, so pop it and add the new one
    }
    minEndTimeHeap.add(meeting.second)
  }
  return minEndTimeHeap.size
}
