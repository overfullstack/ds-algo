/* gakshintala created on 5/26/20 */
package leetcode.tree

import ds.tree.TreeNode

fun TreeNode?.flatten(pre: TreeNode? = null): TreeNode? {
    if (this == null) {
        return pre
    }
    var preNode = right.flatten(pre)
    preNode = left.flatten(preNode)
    right = preNode
    left = null
    return this
}
