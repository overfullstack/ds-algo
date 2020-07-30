/* gakshintala created on 1/26/20 */
package leetcode.tree

import ds.tree.TreeNode
import leetcode.tree.NodeAtDistanceK.*

sealed class NodeAtDistanceK {
    object NotFound : NodeAtDistanceK()
    object AllNodesFound : NodeAtDistanceK()
    class FoundAtDistance(val distance: Int) : NodeAtDistanceK() {
        operator fun plus(value: Int) = distance + value
    }
}

fun distanceK(root: TreeNode?, target: TreeNode?, K: Int) =
    if (target == null) emptyList() else root.nodesAtDistanceK(target.`val`, K).second

private fun TreeNode?.nodesAtDistanceK(target: Int, K: Int): Pair<NodeAtDistanceK, List<Int>> {
    if (this == null) {
        return NotFound to emptyList()
    }
    if (this.`val` == target) {
        // * When found, return along with all bottom-nodes, so bottom direction is covered.
        return FoundAtDistance(0) to this.bottomNodesAtDistanceK(K)
    }
    val (leftStatus, leftResult) = left.nodesAtDistanceK(target, K)

    return when (leftStatus) {
        is AllNodesFound -> AllNodesFound to leftResult
        is FoundAtDistance ->
            if (leftStatus + 1 == K) {
                AllNodesFound to leftResult + this.`val`
            } else { // At every ancestor, covering all the directions apart from the direction in which the recursion rolled-up (left in this case)
                FoundAtDistance(leftStatus + 1) to // As coming from left, search Top and right
                        leftResult + right.bottomNodesAtDistanceK(K - (leftStatus + 2))
            }
        else -> {
            val (rightStatus, rightResult) = right.nodesAtDistanceK(target, K)
            when (rightStatus) {
                is AllNodesFound -> AllNodesFound to rightResult
                is FoundAtDistance -> {
                    if (rightStatus + 1 == K) {
                        AllNodesFound to rightResult + this.`val`
                    } else { // At every ancestor, covering all the directions apart from the direction in which the recursion rolled-up (right in this case)
                        FoundAtDistance(rightStatus + 1) to // As coming from right, search Top and left
                                rightResult + left.bottomNodesAtDistanceK(K - (rightStatus + 2))
                    }
                }
                else -> NotFound to emptyList()
            }
        }
    }
}

private fun TreeNode?.bottomNodesAtDistanceK(K: Int): List<Int> = when {
    this == null -> emptyList()
    K == 0 -> listOf(this.`val`)
    else -> left.bottomNodesAtDistanceK(K - 1) + right.bottomNodesAtDistanceK(K - 1)
}
