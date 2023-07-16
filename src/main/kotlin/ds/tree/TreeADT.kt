package ds.tree

sealed class TreeADT<T> {
  data class Node<T>(val `val`: T, val left: TreeADT<T>, val right: TreeADT<T>) : TreeADT<T>()

  data class Leaf<T>(val `val`: T) : TreeADT<T>()

  object Empty : TreeADT<Nothing>()

  fun inorderTraversal(): List<T> =
    when (this) {
      is Node -> {
        left.inorderTraversal() + `val` + right.inorderTraversal()
      }
      is Leaf -> listOf(`val`)
      else -> emptyList()
    }
}
