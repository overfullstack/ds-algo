/* gakshintala created on 9/24/19 */
package hackerearth

fun numOfWaysToPaint(n: Int, numOfColors: Int): Int {
    var same: Int
    var diff = numOfColors
    var total = numOfColors
    for (i in 2..n) {
        same = diff
        diff = total * (numOfColors - 1)
        total = same + diff
    }
    return total
}

fun main() {
    print(numOfWaysToPaint(3,2))
}