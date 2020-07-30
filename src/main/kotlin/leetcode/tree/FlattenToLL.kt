/* gakshintala created on 5/26/20 */
package leetcode.tree

import ds.tree.TreeNode

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
fun TreeNode?.flatten(next: TreeNode? = null): TreeNode? {
    if (this == null) {
        return next
    }
    // * Reverse PreOrder
    // Right is is next to left, left is next to root
    var nextForRoot = right.flatten(next)
    nextForRoot = left.flatten(nextForRoot)
    right = nextForRoot // using `right` as `next` in LL
    left = null
    return this
}
