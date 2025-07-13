package educative.greedy

fun boatsToSavePeople(people: IntArray, limit: Int): Int {
  var numOfBoats = 0
  var leftIndex = 0
  var rightIndex = people.lastIndex
  people.sort()
  while (leftIndex <= rightIndex) {
    if (people[leftIndex] + people[rightIndex] <= limit) {
      leftIndex++
    }
    rightIndex--
    numOfBoats++
  }
  return numOfBoats
}
