/* gakshintala created on 9/3/19 */
package ds

data class TreeNode(var value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    fun visit(): Array<Int> {
        val a = left?.visit() ?: emptyArray()
        val b = right?.visit() ?: emptyArray()
        return arrayOf(value) + a + b
    }

}

fun main() {
    val inputArr = readLine()!!.split(",").map { if (it == "null") null else it.toInt() }
    val root = readArrToTree(inputArr)
    root!!.visit().forEach { print("$it ") }
}

fun readArrToTree(inputArr: List<Int?>): TreeNode? {
    return readArrToTree(inputArr, 0, TreeNode(inputArr[0]!!))
}

fun readArrToTree(inputArr: List<Int?>, index: Int, root: TreeNode?): TreeNode? {
    if (index >= inputArr.size / 2) {
        return root // Leaf node
    }
    val leftIndex = 2 * index + 1
    val rightIndex = 2 * index + 2
    if (leftIndex <= inputArr.lastIndex) {
        root?.left = readArrToTree(
            inputArr,
            leftIndex,
            if (inputArr[leftIndex] != null) TreeNode(inputArr[leftIndex]!!) else null
        )
    }
    if (rightIndex <= inputArr.lastIndex) {
        root?.right = readArrToTree(
            inputArr,
            rightIndex,
            if (inputArr[rightIndex] != null) TreeNode(inputArr[rightIndex]!!) else null
        )
    }
    return root
}
