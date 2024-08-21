package ds.ll

import com.salesforce.revoman.input.readFileInResourcesToString
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import kotlin.toString

data class SLLNode(var value: Int, var next: SLLNode? = null) {

  fun getNodeAt(pos: Int): SLLNode? = if (pos == 0) this else next?.getNodeAt(pos - 1)

  fun length(len: Int = 1): Int = if (next == null) len else next!!.length(len + 1)

  fun last(): SLLNode = if (next == null) this else next!!.last()

  fun getNodeForValue(valToFind: Int): SLLNode? =
    if (value == valToFind) this else next?.getNodeForValue(valToFind)

  fun reverse(prev: SLLNode? = null): SLLNode =
    if (next == null) {
      next = prev
      this
    } else {
      val curNext = next
      next = prev
      curNext!!.reverse(this)
    }

  fun toArray(): IntArray = intArrayOf(value, *(next?.toArray() ?: intArrayOf()))

  override fun toString(): String =
    when {
      next == null -> value.toString()
      else -> "$value -> $next"
    }

  companion object {
    fun of(values: IntArray): SLLNode? = if (values.isEmpty()) null else ofNonEmpty(values)

    private tailrec fun ofNonEmpty(
      values: IntArray,
      index: Int = values.lastIndex,
      next: SLLNode = SLLNode(values[index], null)
    ): SLLNode =
      when (index) {
        0 -> next
        else -> ofNonEmpty(values, index - 1, SLLNode(values[index - 1], next))
      }

    fun of2(values: IntArray, index: Int = 0): SLLNode =
      if (index == values.lastIndex) SLLNode(values[index])
      else SLLNode(values[index], of2(values, index + 1))

    @JsonClass(generateAdapter = true)
    data class JSLL(val linkedList: LinkedList) {
      @JsonClass(generateAdapter = true)
      data class LinkedList(val head: String, val nodes: List<Node>) {
        @JsonClass(generateAdapter = true)
        data class Node(val id: String, val next: String?, val value: Int)
      }
    }

    @SuppressWarnings("kotlin:S6611")
    @OptIn(ExperimentalStdlibApi::class)
    fun parseJsonFileToSLL(jsonFilePath: String): SLLNode {
      val sllJson = readFileInResourcesToString(jsonFilePath)
      val sllAdapter = Moshi.Builder().build().adapter<JSLL>()
      val jSll = sllAdapter.fromJson(sllJson)!!
      val idToSLLNode = jSll.linkedList.nodes.associate { it.id to (SLLNode(it.value) to it.next) }
      for ((sllNode, nextId) in idToSLLNode.values) {
        sllNode.next = idToSLLNode[nextId]?.first
      }
      return idToSLLNode[jSll.linkedList.head]!!.first
    }
  }
}
