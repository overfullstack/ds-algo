package ds.ll

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

data class ListNode(var value: Int, var next: ListNode? = null) {

  fun insertNext(nodeToInsert: ListNode) {
    val next = next
    this.next = nodeToInsert
    nodeToInsert.next = next
  }

  fun middle(): ListNode {
    var ptr: ListNode? = this
    var fPtr: ListNode? = this
    while (fPtr?.next != null) {
      ptr = ptr?.next
      fPtr = fPtr.next?.next
    }
    return ptr!!
  }

  fun getNodeForValue(valToFind: Int): ListNode? =
    if (value == valToFind) this else next?.getNodeForValue(valToFind)

  fun reverse(prev: ListNode? = null): ListNode =
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
      else -> "$value -> ${next?.value}"
    }

  companion object {
    fun of(values: IntArray): ListNode? = if (values.isEmpty()) null else ofNonEmpty(values)

    private tailrec fun ofNonEmpty(
      values: IntArray,
      index: Int = values.lastIndex,
      next: ListNode = ListNode(values[index], null),
    ): ListNode =
      when (index) {
        0 -> next
        else -> ofNonEmpty(values, index - 1, ListNode(values[index - 1], next))
      }

    @Serializable
    data class JSLL(val linkedList: LinkedList) {
      @Serializable
      data class LinkedList(val head: String, val nodes: List<Node>) {
        @Serializable data class Node(val id: String, val next: String?, val value: Int)
      }
    }

    fun parseJsonFileToSLL(jsonFilePath: String): ListNode {
      val sllJson = readFileToString(jsonFilePath)
      val jSll = Json.decodeFromString<JSLL>(sllJson)
      val idToListNode =
        jSll.linkedList.nodes.associate { it.id to (ListNode(it.value) to it.next) }
      for ((sllNode, nextId) in idToListNode.values) {
        sllNode.next = idToListNode[nextId]?.first
      }
      return idToListNode[jSll.linkedList.head]!!.first
    }
  }
}

tailrec fun ListNode?.getNodeAtOrNull(pos: Int): ListNode? =
  if (pos == 1) this else this?.next?.getNodeAtOrNull(pos - 1)

tailrec fun ListNode?.length(len: Int = 1): Int =
  when (this?.next) {
    null -> len
    else -> next.length(len + 1)
  } ?: 0

tailrec fun ListNode?.last(): ListNode? =
  when (this?.next) {
    null -> this
    else -> this.next.last()
  }
