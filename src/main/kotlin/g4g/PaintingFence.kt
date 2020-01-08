/* gakshintala created on 9/24/19 */
package g4g

fun numOfWaysToPaint(noOfSlots: Int, numOfColors: Int): Int {
    var paintSameColorCount: Int
    var paintDifferentColorCount = numOfColors // For Single slot
    var totalNoOfWaysToPaintTillNow = numOfColors // For Single slot
    for (i in 2..noOfSlots) { // Starting from 2nd slot
        paintSameColorCount = paintDifferentColorCount // This is like merging the new slot with the last slot and treating them as one (having same color), so combinations would be same as previous different color comb 
        paintDifferentColorCount = totalNoOfWaysToPaintTillNow * (numOfColors - 1) // (numOfColors - 1) as one color would be taken by the previous slot.
        totalNoOfWaysToPaintTillNow = paintSameColorCount + paintDifferentColorCount
    }
    return totalNoOfWaysToPaintTillNow
}

fun main() {
    print(numOfWaysToPaint(3, 2))
}