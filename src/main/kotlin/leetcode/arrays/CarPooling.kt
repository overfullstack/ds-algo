package leetcode.arrays

fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
    val originAndDest = trips.map { Pair(it[0], it[1]) to Pair(it[0], it[2]) }.unzip()
    val sortedOrgin = originAndDest.first.sortedBy { it.second }
    val sortedDest = originAndDest.second.sortedBy { it.second }

    var i = 0
    var j = 0
    var pickUps = 0
    var maxPickUps = 0
    while(i <= sortedOrgin.lastIndex && j <= sortedDest.lastIndex) {
        if (sortedOrgin[i].second < sortedDest[j].second) {
            pickUps += sortedOrgin[i].first
            maxPickUps = maxOf(maxPickUps, pickUps)
            i++
        } else {
            pickUps -= sortedDest[j].first
            j++
        }
    }
    return maxPickUps <= capacity
}
