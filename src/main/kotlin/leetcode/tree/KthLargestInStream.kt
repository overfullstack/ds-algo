/* gakshintala created on 12/21/19 */
package leetcode.tree

import ds.tree.TreeNode
import ds.tree.TreeNode.Utils.arrToBST
import ds.tree.reverseInorderTraversalMorris

class KthLargestInStream(private val kth: Int, nums: IntArray) {
  private var root = arrToBST(nums)
  private var kthLargest: TreeNode? = null

  fun add(`val`: Int): Int {
    root?.insert(`val`) ?: run { root = TreeNode(`val`) }
    var k = 0
    root?.reverseInorderTraversalMorris {
      k++
      if (k == kth) {
        // Not returning right away, as in morris traversal - successor pointers needs to be reset,
        // which needs complete traversing.
        kthLargest = it
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
