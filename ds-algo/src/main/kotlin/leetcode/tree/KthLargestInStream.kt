/* gakshintala created on 12/21/19 */
package leetcode.tree

import ga.overfullstack.ds.tree.TreeNode
import ga.overfullstack.ds.tree.TreeNode.Utils.arrToBST
import ga.overfullstack.ds.tree.reverseInorderTraversalMorris

class KthLargestInStream(private val kth: Int, nums: IntArray) {
  private var root = arrToBST(nums)
  private var kthLargest: TreeNode? = null

  fun add(value: Int): Int {
    root?.insert(value) ?: run { root = TreeNode(value) }
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
    return kthLargest?.value ?: 0
  }
}

fun main() {
  val nums = readln().split(",").map { it.trim().toInt() }.toIntArray()
  val kthLargest = KthLargestInStream(readln().toInt(), nums)
  println(kthLargest.add(3))
  println(kthLargest.add(5))
  println(kthLargest.add(10))
  println(kthLargest.add(9))
  println(kthLargest.add(4))
}
