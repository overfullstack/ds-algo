package ds.ll

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

data class SLLNode(var value: Int, var next: SLLNode? = null) {

  fun insertNext(nodeToInsert: SLLNode) {
    val next = next
    this.next = nodeToInsert
    nodeToInsert.next = next
  }

  fun middle(): SLLNode {
    var ptr: SLLNode? = this
    var fPtr: SLLNode? = this
    while (fPtr?.next != null) {
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
      next: SLLNode = SLLNode(values[index], null),
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
      val sllJson = readFileToString(jsonFilePath)
      val jSll = Json.decodeFromString<JSLL>(sllJson)
      val idToSLLNode = jSll.linkedList.nodes.associate { it.id to (SLLNode(it.value) to it.next) }
      for ((sllNode, nextId) in idToSLLNode.values) {
        sllNode.next = idToSLLNode[nextId]?.first
      }
      return idToSLLNode[jSll.linkedList.head]!!.first
    }
  }
}

tailrec fun SLLNode?.getNodeAtOrNull(pos: Int): SLLNode? =
  if (pos == 1) this else this?.next?.getNodeAtOrNull(pos - 1)

tailrec fun SLLNode?.length(len: Int = 1): Int =
  when (this?.next) {
    null -> len
    else -> next.length(len + 1)
  } ?: 0

tailrec fun SLLNode?.last(): SLLNode? =
  when (this?.next) {
    null -> this
    else -> this.next.last()
  }
