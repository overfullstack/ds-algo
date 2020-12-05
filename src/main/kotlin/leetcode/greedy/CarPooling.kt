package leetcode.greedy

/**
 * https://leetcode.com/problems/car-pooling/
 * [No.of Passengers, Start, End]
 * Is it possible to pick up and drop off all passengers for all the given trips
 */
fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
    val sortedByStartTrips = trips.map { Triple(it[0], it[1], it[2]) }.sortedBy { it.second }
    // * This stores the net onboarding at every point.
    val map = sortedMapOf<Int, Int>() // ! This is a SortedMap
    for ((passengersCount, start, end) in sortedByStartTrips) {
        map.merge(start, passengersCount) { old, _ -> old + passengersCount }
        map.merge(end, -passengersCount) { old, _ -> old - passengersCount }
    }
    return map.values.asSequence().runningReduce(Int::plus).all { it <= capacity }
}

fun carPooling2(trips: Array<IntArray>, capacity: Int): Boolean {
    val originAndDest = trips.map { Pair(it[0], it[1]) to Pair(it[0], it[2]) }.unzip()
    val sortedByOrgin = originAndDest.first.sortedBy { it.second }
    val sortedByDest = originAndDest.second.sortedBy { it.second }

    var i = 0
    var j = 0
    var pickUps = 0
    var maxPickUps = 0
    while (i <= sortedByOrgin.lastIndex && j <= sortedByDest.lastIndex) {
        if (sortedByOrgin[i].second < sortedByDest[j].second) {
            pickUps += sortedByOrgin[i].first
            maxPickUps = maxOf(maxPickUps, pickUps)
            if (maxPickUps > capacity) {
                return false
            }
            i++
        } else {
            pickUps -= sortedByDest[j].first
            j++
        }
    }
    return true
}
