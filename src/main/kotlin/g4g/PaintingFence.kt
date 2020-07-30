/* gakshintala created on 9/24/19 */
package g4g

fun numOfWaysToPaint(noOfSlots: Int, numOfColors: Int): Int {
    var paintSameColor: Int
    var paintDifferentColor = numOfColors // For Single slot
    var totalNoOfWaysToPaintTillNow = numOfColors // For Single slot
    repeat(noOfSlots - 1) {
        // Atmost 2 posts can have same color, so painting this with last one with same color is same as painting (n-1) with different colors.
        // = `paintDifferentColor` instead of `totalNoOfWaysToPaintTillNow`
        // If `n` and `n-1` hv same color than `n-1` and `n-2` must have different colors. So not considering paintsame from previous.
        paintSameColor = paintDifferentColor
        paintDifferentColor =
            totalNoOfWaysToPaintTillNow * (numOfColors - 1) // Except the previous slot color
        totalNoOfWaysToPaintTillNow = paintSameColor + paintDifferentColor
    }
    return totalNoOfWaysToPaintTillNow
}

fun main() {
    print(numOfWaysToPaint(3, 2))
}
