package educative.heap

import java.util.PriorityQueue

fun minimumMachines(intervals: List<Pair<Int, Int>>): Int {
  // ! Earliest starting
  val minHeapForNextStartingJob = PriorityQueue(compareBy<Pair<Int, Int>> { it.first })
  // ! Earliest ending
  val minHeapForNextFreeMachine = PriorityQueue<Int>()

  minHeapForNextStartingJob.addAll(intervals)

  while (minHeapForNextStartingJob.isNotEmpty()) {
    val (nextJobStart, nextJobEnd) = minHeapForNextStartingJob.poll()
    if (
      minHeapForNextFreeMachine.isNotEmpty() && minHeapForNextFreeMachine.peek() <= nextJobStart
    ) {
      minHeapForNextFreeMachine.poll() // ! Remove tasks that ended before current task
    }
    minHeapForNextFreeMachine.add(nextJobEnd)
  }
  return minHeapForNextFreeMachine.size // ! All occupied machines
}

fun main() {
  println(minimumMachines(listOf(1 to 7, 8 to 13, 5 to 6, 10 to 14, 6 to 7)))
  println(minimumMachines(listOf(2 to 3, 4 to 7, 8 to 18, 19 to 25, 26 to 30)))
}
