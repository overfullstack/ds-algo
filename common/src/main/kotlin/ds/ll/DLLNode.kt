package ds.ll

data class DLLNode<T>(var value: T, var prev: DLLNode<T>? = null, var next: DLLNode<T>? = null) {

  fun toList(): List<T> = mutableListOf(value).also { it.addAll<T>(next?.toList() ?: emptyList()) }

  override fun toString(): String =
    when {
      next == null -> value.toString()
      else -> "$value <=> $next"
    }

  companion object {
    fun <T> of(values: List<T>): DLLNode<T>? =
      if (values.isEmpty()) null else ofNonEmptyList(values)

    private tailrec fun <T> ofNonEmptyList(
      values: List<T>,
      index: Int = values.lastIndex,
      next: DLLNode<T> = DLLNode(values[index], null, null)
    ): DLLNode<T> =
      when (index) {
        0 -> next
        else -> {
          val node = DLLNode(values[index - 1], null, next)
          next.prev = node
          ofNonEmptyList(values, index - 1, node)
        }
      }
  }
}
