/* gakshintala created on 9/3/19 */
package tree

class TreeNode(var value: Int) {
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
    val root = readArrToTree(inputArr, 0, TreeNode(inputArr[0]!!))
    root!!.visit().forEach { print("$it ") }
}

fun readArrToTree(inputArr: List<Int?>, index: Int, root: TreeNode?): TreeNode? {
    if (index >= inputArr.size / 2) {
        return root
    }
    val leftIndex = 2 * index + 1
    val rightIndex = 2 * index + 2
    root?.left = readArrToTree(
            inputArr, 
            leftIndex, 
            if (inputArr[leftIndex] != null) TreeNode(inputArr[leftIndex]!!) else null)
    root?.right = readArrToTree(
        inputArr,
        rightIndex,
        if (inputArr[rightIndex] != null) TreeNode(inputArr[rightIndex]!!) else null
    )
    return root
}
