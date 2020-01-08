/* gakshintala created on 12/21/19 */
package tree

import ds.TreeNode
import ds.TreeNode.Utils.readArrToTree
import ds.getKthLargestReverseMorris

class KthLargestInStream(private val k: Int, nums: IntArray) {
    private var rankTreeRoot = readArrToTree(nums)
    private var kthLargest: TreeNode? = null

    fun add(`val`: Int): Int {
        rankTreeRoot?.insert(`val`) ?: run { rankTreeRoot = TreeNode(`val`) }
        var kth = 0
        rankTreeRoot?.getKthLargestReverseMorris {
            kth++
            if (kth == k) {
                kthLargest = it // Not returning right away, as morris traversal successor pointers needs to be reset
            }
        }
        kth = 0
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