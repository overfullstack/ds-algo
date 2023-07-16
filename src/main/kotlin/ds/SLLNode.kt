package ds

data class SLLNode(var value: Int, var next: SLLNode? = null) {

  companion object {
    fun of(values: IntArray): SLLNode? = if (values.isEmpty()) null else ofNonEmpty(values)

    private tailrec fun ofNonEmpty(
      values: IntArray,
      prevNode: SLLNode = SLLNode(values[values.lastIndex]),
      index: Int = values.lastIndex - 1
    ): SLLNode =
      when {
        index < 0 -> prevNode
        index == 0 -> SLLNode(values[index], prevNode)
        else -> ofNonEmpty(values, SLLNode(values[index], prevNode), index - 1)
      }

    fun of2(values: IntArray, index: Int = 0): SLLNode =
      if (index == values.lastIndex) SLLNode(values[index])
      else SLLNode(values[index], of2(values, index + 1))
  }
}

fun SLLNode.toArray(): IntArray = intArrayOf(value, *(next?.toArray() ?: intArrayOf()))

tailrec fun SLLNode.getNodeForValue(valToFind: Int): SLLNode? =
  if (value == valToFind) this else next?.getNodeForValue(valToFind)

tailrec fun SLLNode.reverse(prev: SLLNode? = null): SLLNode =
  if (next == null) {
    next = prev
    this
  } else {
    val curNext = next
    next = prev
    curNext!!.reverse(this)
  }

tailrec fun SLLNode.length(len: Int = 1): Int = if (next == null) len else next!!.length(len + 1)

tailrec fun SLLNode.last(): SLLNode = if (next == null) this else next!!.last()

tailrec fun SLLNode.getNodeAt(pos: Int): SLLNode? = if (pos == 0) this else next?.getNodeAt(pos - 1)
