package educative.heap
import java.util.PriorityQueue

fun maximizeCapital(initialCapital: Int, k: Int, capitalsToProfits: List<Pair<Int, Int>>): Int {
  val minHeapForCapitals = PriorityQueue(Comparator.comparingInt<Pair<Int, Int>> { it.first })
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
    totalCapital += maxHeapForProfits.poll()
    projectsPickedCount++
  }
  return totalCapital
}
