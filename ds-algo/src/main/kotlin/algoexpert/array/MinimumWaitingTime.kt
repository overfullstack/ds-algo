package algoexpert.array

fun minimumWaitingTime(timeArr: IntArray): Int {
  timeArr.sort()
  var totalWaitingTime = 0
  for ((index, time) in timeArr.withIndex()) {
    totalWaitingTime += time * (timeArr.lastIndex - index)
  }
  return totalWaitingTime
}
