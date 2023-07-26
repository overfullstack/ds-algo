package algoexpert.array

fun speed(redShirtSpeeds: IntArray, blueShirtSpeeds: IntArray, fastest: Boolean): Int {
  val redShirtSpeedsSorted = redShirtSpeeds.sorted()
  val blueShirtSpeedsSorted = blueShirtSpeeds.sorted()
  var speed = 0
  var (i, op) = if (fastest) (redShirtSpeedsSorted.lastIndex to Int::dec) else (0 to Int::inc)
  for (j in blueShirtSpeedsSorted.indices) {
    speed += maxOf(redShirtSpeedsSorted[i], blueShirtSpeedsSorted[j])
    i = op(i)
  }
  return speed
}
