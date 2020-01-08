package ds

sealed class Tree<T> {
    data class Node<T>(val `val`: T, val left: Tree<T>, val right: Tree<T>): Tree<T>()
    data class Leaf<T>(val `val`: T): Tree<T>()
    object Empty: Tree<Nothing>()

    fun inorderTraversal(): List<T> =
        when (this) {
            is Node -> {
                left.inorderTraversal() + `val` + right.inorderTraversal()
            }
            is Leaf -> listOf(`val`)
            else -> emptyList()
        }

}