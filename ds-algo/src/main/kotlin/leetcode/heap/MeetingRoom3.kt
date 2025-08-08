package leetcode.heap

import java.util.PriorityQueue

/* 03 Aug 2025 11:44 */

/** [2402. Meeting Rooms III](https://leetcode.com/problems/meeting-rooms-iii) */
fun mostBooked(n: Int, meetings: Array<IntArray>): Int {
  // ! end to roomId
  val occupiedRooms = PriorityQueue<Pair<Long, Int>>(compareBy({ it.first }, { it.second }))
  val availableRoomIds = PriorityQueue((0 until n).toList())
  val roomIdToMeetingCount = IntArray(n)
  val sortedMeetings = meetings.map { it[0].toLong() to it[1].toLong() }.sortedBy { it.first }
  for ((start, end) in sortedMeetings) {
    while (occupiedRooms.isNotEmpty() && occupiedRooms.peek().first <= start) {
      availableRoomIds.add(occupiedRooms.poll().second)
    }
    val roomId =
      when {
        availableRoomIds.isNotEmpty() -> {
          val availableRoomId = availableRoomIds.poll()
          occupiedRooms.add(end to availableRoomId)
          availableRoomId
        }
        else -> {
          val (earliestEnd, earliestAvailableRoomId) = occupiedRooms.poll()
          occupiedRooms.add(earliestEnd + (end - start) to earliestAvailableRoomId)
          earliestAvailableRoomId
        }
      }
    roomIdToMeetingCount[roomId]++
  }
  return roomIdToMeetingCount.withIndex().maxBy { it.value }.index
}

fun main() {
  println(
    mostBooked(
      3,
      arrayOf(
        intArrayOf(1, 20),
        intArrayOf(2, 10),
        intArrayOf(3, 5),
        intArrayOf(4, 9),
        intArrayOf(6, 8),
      ),
    )
  ) // Expected: 1
}
