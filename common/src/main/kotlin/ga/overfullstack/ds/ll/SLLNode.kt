package ga.overfullstack.ds.ll

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import ga.overfullstack.utils.readFileToString

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
      val sllJson = readFileToString(jsonFilePath)
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
