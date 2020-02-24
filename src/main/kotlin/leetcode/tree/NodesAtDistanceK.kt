/* gakshintala created on 1/26/20 */
package leetcode.tree

import ds.TreeNode
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
        return FoundAtDistance(0) to this.bottomNodesAtDistanceK(K)
    }
    val (leftDistance, leftResult) = left.nodesAtDistanceK(target, K)
    return when (leftDistance) {
        is AllNodesFound -> AllNodesFound to leftResult
        is FoundAtDistance ->
            if (leftDistance + 1 == K) {
                AllNodesFound to leftResult + this.`val`
            } else { // At every ancestor, covering all the directions apart from the direction in which the recursion rolled-up (left in this case)
                FoundAtDistance(leftDistance + 1) to // As coming from left, search Top and right
                        leftResult + right.bottomNodesAtDistanceK(K - (leftDistance + 2))
            }
        else -> {
            val (rightDistance, rightResult) = right.nodesAtDistanceK(target, K)
            return when (rightDistance) {
                is AllNodesFound -> AllNodesFound to rightResult
                is FoundAtDistance -> {
                    if (rightDistance + 1 == K) {
                        AllNodesFound to rightResult + this.`val`
                    } else { // At every ancestor, covering all the directions apart from the direction in which the recursion rolled-up (right in this case)
                        FoundAtDistance(rightDistance + 1) to // As coming from right, search Top and left
                                rightResult + left.bottomNodesAtDistanceK(K - (rightDistance + 2))
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


