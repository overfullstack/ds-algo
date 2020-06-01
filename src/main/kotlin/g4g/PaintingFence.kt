/* gakshintala created on 9/24/19 */
package g4g

fun numOfWaysToPaint(noOfSlots: Int, numOfColors: Int): Int {
    var paintSameColorCount: Int
    var paintDifferentColorCount = numOfColors // For Single slot
    var totalNoOfWaysToPaintTillNow = numOfColors // For Single slot
    for (i in 2..noOfSlots) {
        // Atmost 2 posts can have same color, so painting this with last one with same color is same as painting (n-1) with different colors.
        paintSameColorCount = paintDifferentColorCount
        paintDifferentColorCount =
            totalNoOfWaysToPaintTillNow * (numOfColors - 1) // Except the previous slot color
        totalNoOfWaysToPaintTillNow = paintSameColorCount + paintDifferentColorCount
    }
    return totalNoOfWaysToPaintTillNow
}

fun main() {
    print(numOfWaysToPaint(3, 2))
}
