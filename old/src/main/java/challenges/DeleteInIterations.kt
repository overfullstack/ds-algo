/* gakshintala created on 2/1/20 */
package challenges

fun getLookup(minFrequency: Int, sortedFrequencies: List<Int>): List<Int> {
  val lookups = mutableListOf<List<Int>>()
  var lookUp = sortedFrequencies
  while (lookUp.isNotEmpty()) {
    lookups.add(lookUp)
    lookUp = lookUp.map { it - minFrequency }.filter { it > 0 }
  }
  return lookups.map { it.count() }
}

fun main() {
  val (minFrequency, noOfQueries) = readln().split(" ").map { it.toInt() }
  val arr = readln().split(" ").map { it.toInt() }
  val frequencies = arr.groupingBy { it }.eachCount().values
  val (removableFrequencies, nonRemovableFrequencies) =
    frequencies.partition { it % minFrequency == 0 }
  val lookup = getLookup(minFrequency, removableFrequencies)
  repeat(noOfQueries) {
    val iterations = readln().toInt()
    println(
      nonRemovableFrequencies.size + if (iterations > lookup.lastIndex) 0 else lookup[iterations]
    )
  }
}
