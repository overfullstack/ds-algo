/* gakshintala created on 9/3/19 */
package ds

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




