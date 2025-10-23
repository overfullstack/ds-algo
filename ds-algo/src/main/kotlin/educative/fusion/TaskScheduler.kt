package educative.fusion

/* 10 Aug 2025 17:45 */

/** [621. Task Scheduler](https://leetcode.com/problems/task-scheduler) */
fun leastTime(tasks: CharArray, coolingPeriod: Int): Int {
  // ! Sorting is not required, but helps in early termination making the idleTime negative faster
  val sortedFreqs = tasks.groupBy { it }.mapValues { it.value.size }.values.sortedDescending()
  val maxFreq = sortedFreqs.max()
  var idleTime = (maxFreq - 1) * coolingPeriod
  // ! drop the most frequent for which idleTime is calculated above
  for (freq in sortedFreqs.drop(1)) {
    // ! We can only fill `maxFreq - 1` slots
    // ! so a same freq task can't utilize all and can only reduce `maxFreq - 1` idle slots
    idleTime -= minOf(maxFreq - 1, freq)
    if (idleTime <= 0) {
      break
    }
  }
  return tasks.size + maxOf(0, idleTime)
}
