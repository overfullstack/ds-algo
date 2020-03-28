/* gakshintala created on 2/2/20 */
package g4g

import ds.TreeNode

fun main() {
    val testCases = readLine()!!.toInt()
    repeat(testCases) {
        readLine()!!.toInt() // size
        val parentArr = readLine()!!.split(" ").map { it.toInt() }
        parentArr.indices.forEach {
            constructTree(it, parentArr)
        }

    }
}

fun constructTree(index: Int, parentArr: List<Int>, constructedNodes: Array<TreeNode?> = emptyArray()) {
    when {
        constructedNodes[index] != null || parentArr[index] == -1 -> return
        constructedNodes[parentArr[index]] == null -> constructTree(parentArr[index], parentArr, constructedNodes)
        else -> constructedNodes[parentArr[index]]?.run {
            if (left == null) {
                left = TreeNode(index)
            } else {
                right = TreeNode(index)
            }
        }
    }
}
