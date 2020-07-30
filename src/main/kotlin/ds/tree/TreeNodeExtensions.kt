/* gakshintala created on 9/3/19 */
package ds.tree

import java.util.*

fun TreeNode?.inorderTraversalMorris() {
    var cur = this
    while (cur != null) {
        if (cur.left == null) { // No left side
            print(cur.`val`) // We finish visiting a node
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
                print(cur.`val`) // We finish visiting a node
                cur = cur.right // Now left visit is complete, safely go to right side
            }
        }
    }
}

fun TreeNode?.reverseInorderTraversalMorris(visitFn: (TreeNode?) -> Unit) {
    var cur = this
    while (cur != null) {
        if (cur.right == null) {
            visitFn(cur) // For kth Largest, a larger node is recorded.
            cur = cur.left // This at Leaf node takes you to inorderSuccessor
        } else {
            var inorderSuccessor = cur.right

            while (inorderSuccessor?.left != null && inorderSuccessor.left != cur) {
                inorderSuccessor = inorderSuccessor.left
            }
            // Two reasons why the above loop terminates.
            if (inorderSuccessor?.left == null) {
                inorderSuccessor?.left = cur
                cur = cur.right
            } else { // Right side is covered
                inorderSuccessor.left = null // break the link
                visitFn(cur) // For kth Largest, a larger node is recorded and move on to left
                cur = cur.left // By this time all larger nodes including the root for cur.left are recorded.
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
        print(cur.`val`)
        cur = cur.right
    }
}
