/* gakshintala created on 12/21/19 */
package leetcode.tree

import ds.TreeNode
import ds.TreeNode.Utils.arrToBST
import ds.getKthLargestReverseMorris

class KthLargestInStream(private val kth: Int, nums: IntArray) {
    private var root = arrToBST(nums)
    private var kthLargest: TreeNode? = null

    fun add(`val`: Int): Int {
        root?.insert(`val`) ?: run { root = TreeNode(`val`) }
        var k = 0
        root?.getKthLargestReverseMorris {
            k++
            if (k == kth) {
                kthLargest =
                    it // Not returning right away, as in morris traversal - successor pointers needs to be reset, which needs complete traversing.
            }
        }

        k = 0
        return kthLargest?.`val` ?: 0
    }
}

fun main() {
    val nums = readLine()!!.split(",").map { it.trim().toInt() }.toIntArray()
    val kthLargest = KthLargestInStream(readLine()!!.toInt(), nums)
    println(kthLargest.add(3))
    println(kthLargest.add(5))
    println(kthLargest.add(10))
    println(kthLargest.add(9))
    println(kthLargest.add(4))
}
