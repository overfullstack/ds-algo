package educative.heap

import java.util.PriorityQueue

fun maximizeCapital(initialCapital: Int, k: Int, capitalsToProfits: List<Pair<Int, Int>>): Int {
  // ! We need minHeap as the capitals is not guaranteed to be sorted
  val minHeapForCapitals = PriorityQueue(compareBy<Pair<Int, Int>> { it.first })
  minHeapForCapitals.addAll(capitalsToProfits)
  val maxHeapForProfits = PriorityQueue(Comparator.reverseOrder<Int>())
  var projectsPickedCount = 0
  var totalCapital = initialCapital
  while (projectsPickedCount < k) {
    while (minHeapForCapitals.isNotEmpty() && totalCapital >= minHeapForCapitals.peek().first) {
      maxHeapForProfits.add(minHeapForCapitals.poll().second)
    }
    if (maxHeapForProfits.isEmpty()) {
      break
    }
    // ! As per problem, pure profit from the project, along with the starting capital is returned
    totalCapital += maxHeapForProfits.poll()
    projectsPickedCount++
  }
  return totalCapital
}
