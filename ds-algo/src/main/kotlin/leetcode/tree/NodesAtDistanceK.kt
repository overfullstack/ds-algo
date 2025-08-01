/* gakshintala created on 1/26/20 */
package leetcode.tree

import ds.tree.TreeNode
import leetcode.tree.Status.AllFoundAtK
import leetcode.tree.Status.NotFound
import leetcode.tree.Status.SearchAncestor

/**
 * [863. All Nodes Distance K in Binary
 * Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/)
 */
fun distanceK(root: TreeNode?, target: TreeNode?, k: Int) =
  if (target == null) emptyList() else root.nodesAtDistanceK(target.`val`, k).second

private fun TreeNode?.nodesAtDistanceK(target: Int, k: Int): Pair<Status, List<Int>> =
  when {
    this == null -> NotFound to emptyList()
    // * If target found, finding bottom nodes is a simple recursion
    this.`val` == target -> SearchAncestor(1) to this.bottomNodesAtDistanceK(k)
    else -> {
      val (leftStatus, leftResult) = left.nodesAtDistanceK(target, k)
      when (leftStatus) {
        is AllFoundAtK -> AllFoundAtK to leftResult // Just bubble-up the result
        is SearchAncestor ->
          when (leftStatus.distance) {
            // We found Kth node in ancestor, no need to search further
            k -> AllFoundAtK to (leftResult + this.`val`)
            // During the ancestor journey, search right subtree. `+1` as we go up and right
            else ->
              SearchAncestor(leftStatus + 1) to
                (leftResult + right.bottomNodesAtDistanceK(k - (leftStatus + 1)))
          }
        else -> { // Target not found in left subtree, backtrack and search right subtree
          val (rightStatus, rightResult) = right.nodesAtDistanceK(target, k)
          when (rightStatus) {
            is AllFoundAtK -> AllFoundAtK to rightResult
            is SearchAncestor -> {
              when (rightStatus.distance) {
                k -> AllFoundAtK to (rightResult + this.`val`)
                else ->
                  SearchAncestor(rightStatus + 1) to
                    (rightResult + left.bottomNodesAtDistanceK(k - (rightStatus + 1)))
              }
            }
            else -> NotFound to emptyList()
          }
        }
      }
    }
  }

private fun TreeNode?.bottomNodesAtDistanceK(k: Int): List<Int> =
  when {
    this == null -> emptyList()
    k == 0 -> listOf(this.`val`)
    else -> left.bottomNodesAtDistanceK(k - 1) + right.bottomNodesAtDistanceK(k - 1)
  }

sealed class Status {
  object NotFound : Status()

  object AllFoundAtK : Status()

  class SearchAncestor(val distance: Int) : Status() {
    operator fun plus(value: Int) = distance + value
  }
}
