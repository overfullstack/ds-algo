/* gakshintala created on 9/25/19 */
package leetcode


fun skylineView(buildings: List<Triple<Int, Int, Int>>): Array<Pair<Int, Int>> {
    return skylineView(buildings, 0, buildings.lastIndex)
}

fun skylineView(buildings: List<Triple<Int, Int, Int>>, left: Int, right: Int): Array<Pair<Int, Int>> {
    if(left == right) {
        return arrayOf(Pair(buildings[left].first, buildings[left].second),
                        Pair(buildings[left].third, 0))
    }
    val mid = (left + right) / 2
    val leftResult = skylineView(buildings, left, mid)
    val rightResult = skylineView(buildings, mid+1, right)
    return merge(leftResult, rightResult)
}

fun merge(leftSkyline: Array<Pair<Int, Int>>, rightSkyline: Array<Pair<Int, Int>>): Array<Pair<Int, Int>> {
    var i = 0
    var j = 0
    var h1 = 0
    var h2 = 0
    val mergeResult = mutableListOf<Pair<Int, Int>>()
    while(i< leftSkyline.size && j< rightSkyline.size) {
        if(leftSkyline[i].first < rightSkyline[j].first) {
            h1 = maxOf(leftSkyline[i].second, h2)
            mergeResult.append(Pair(leftSkyline[i].first, h1))
            i++
        } else {
            h2 = maxOf(h1, rightSkyline[j].second)
            mergeResult.append(Pair(rightSkyline[j].first, h2))
            j++
        }
    }
    while(i<leftSkyline.size) {
        mergeResult.append(leftSkyline[i])
        i++  
    }
    while(j<rightSkyline.size) {
        mergeResult.append(rightSkyline[j])
        j++
    }
    return mergeResult.toTypedArray()
}

fun MutableList<Pair<Int, Int>>.append(pair: Pair<Int, Int>) {
    if(isEmpty()) {
        add(pair)
        return
    }
    val peek = last()
    if(pair.second == peek.second) {
        return
    }
    if(pair.first == peek.first) {
        removeAt(lastIndex)
        add(Pair(peek.first, maxOf(pair.second, peek.second)))
        return
    }
    add(pair)
}

fun main() {
    val buildings = readLine()!!.split(",").map { it.trim().toInt() }.chunked(3).map { Triple(it[0], it[1], it[2]) }
    skylineView(buildings).forEach { print("$it ") }
}