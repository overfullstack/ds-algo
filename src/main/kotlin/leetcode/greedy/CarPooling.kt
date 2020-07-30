package leetcode.greedy

/**
 * https://leetcode.com/problems/car-pooling/
 */
@ExperimentalStdlibApi
fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
    val sortedTrips = trips.map { Triple(it[0], it[1], it[2]) }.sortedBy { it.second }
    val map = sortedMapOf<Int, Int>()
    for (trip in sortedTrips) {
        map.merge(trip.second, trip.first) { old, _ -> old + trip.first }
        map.merge(trip.third, -trip.first) { old, _ -> old - trip.first }
    }
    return map.values.asSequence().runningReduce { total, cur -> cur + total }.all { it <= capacity }
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
