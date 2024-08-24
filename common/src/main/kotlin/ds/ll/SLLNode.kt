package ds.ll

import com.salesforce.revoman.input.readFileInResourcesToString
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

data class SLLNode(var value: Int, var next: SLLNode? = null) {

  fun getNodeAt(pos: Int): SLLNode? = if (pos == 0) this else next?.getNodeAt(pos - 1)

  fun length(len: Int = 1): Int =
    when (next) {
      null -> len
      else -> next!!.length(len + 1)
    }

  fun last(): SLLNode =
    when (next) {
      null -> this
      else -> next!!.last()
    }

  fun insertNext(nodeToInsert: SLLNode) {
    val next = next
    this.next = nodeToInsert
    nodeToInsert.next = next
  }

  fun middle(): SLLNode {
    var ptr: SLLNode? = this
    var fPtr: SLLNode? = this
    while (fPtr != null || fPtr?.next != null) {
      ptr = ptr?.next
      fPtr = fPtr.next?.next
    }
    return ptr!!
  }

  fun getNodeForValue(valToFind: Int): SLLNode? =
    if (value == valToFind) this else next?.getNodeForValue(valToFind)

  fun reverse(prev: SLLNode? = null): SLLNode =
    when {
      next == null -> {
        next = prev
        this
      }
      else -> {
        val curNext = next
        next = prev
        curNext!!.reverse(this)
      }
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

    @Serializable
    data class JSLL(val linkedList: LinkedList) {
      @Serializable
      data class LinkedList(val head: String, val nodes: List<Node>) {
        @Serializable data class Node(val id: String, val next: String?, val value: Int)
      }
    }

    fun parseJsonFileToSLL(jsonFilePath: String): SLLNode {
      val sllJson = readFileInResourcesToString(jsonFilePath)
      val jSll = Json.decodeFromString<JSLL>(sllJson)
      val idToSLLNode = jSll.linkedList.nodes.associate { it.id to (SLLNode(it.value) to it.next) }
      for ((sllNode, nextId) in idToSLLNode.values) {
        sllNode.next = idToSLLNode[nextId]?.first
      }
      return idToSLLNode[jSll.linkedList.head]!!.first
    }
  }
}
