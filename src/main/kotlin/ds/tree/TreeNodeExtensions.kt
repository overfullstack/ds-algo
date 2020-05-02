/* gakshintala created on 9/3/19 */
package ds.tree

import java.util.*

fun TreeNode?.inorderTraversalMorris() {
    var cur = this
    while (cur != null) {
        if (cur.left == null) { // No left side
            print(cur.value) // We finish visiting a node
            cur = cur.right // goto right or goto predecessor (if this is a leaf node)
        } else {
            var pre = cur.left
            while (pre?.right != null && pre.right != cur) { // Find inorder predecessor (rightMost). `pre.right != cur` to avoid cycle if link already exists
                pre = pre.right
            }
            if (pre?.right == null) {
                pre?.right = cur // establishing link to predecessor.
                cur =
                    cur.left // we can safely go to the left after establishing link to predecessor, because as we finish traversing left side, we know where to return.
            } else { // This happens when we revisit the node through predecessor link. This indicates we have finished traversing the left side.
                pre.right = null // reset the pointer
                print(cur.value) // We finish visiting a node
                cur = cur.right // Now left visit is complete, safely go to right side
            }
        }
    }
}

fun TreeNode?.getKthLargestReverseMorris(visitFn: (TreeNode?) -> Unit) {
    var cur = this
    while (cur != null) {
        if (cur.right == null) {
            visitFn(cur)
            cur = cur.left
        } else {
            var successor = cur.right
            while (successor?.left != null && successor.left != cur) {
                successor = successor.left
            }
            if (successor?.left == null) {
                successor?.left = cur
                cur = cur.right
            } else {
                successor.left = null
                visitFn(cur)
                cur = cur.left
            }
        }
    }
}

fun TreeNode?.inorderTraversalWithStack() {
    val stk = ArrayDeque<TreeNode>()
    var cur = this
    while (cur != null || stk.isNotEmpty()) {
        while (cur != null) {
            stk.push(cur)
            cur = cur.left
        }
        cur = stk.pop()
        print(cur.value)
        cur = cur.right
    }
}
