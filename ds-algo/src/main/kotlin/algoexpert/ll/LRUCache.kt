package algoexpert.ll

import ds.ll.DLLNode
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonPrimitive
import testcase.LRUCacheArgs

/* 18 Aug 2024 21:21 */

/** [146. LRU Cache](https://leetcode.com/problems/lru-cache) */
fun lruCache(lruCacheArgs: LRUCacheArgs): Pair<List<String?>, List<Pair<String, Int>>> {
  val lruQueue = LRUQueue(lruCacheArgs.maxSize)
  val queryResults = mutableListOf<String?>()
  for ((args, method) in lruCacheArgs.classMethodsToCall) {
    when (method) {
      "insertKeyValuePair" -> {
        val key = args[0].jsonPrimitive.content
        val value = args[1].jsonPrimitive.int
        lruQueue.insertKeyValuePair(key, value)
      }

      "getMostRecentKey" -> queryResults.add(lruQueue.getMostRecentKey())
      "getValueFromKey" -> {
        val key = args[0].jsonPrimitive.content
        queryResults.add(lruQueue.getValueFromKey(key)?.toString())
      }
    }
  }
  return queryResults to lruQueue.toList()
}

class LRUQueue(val capacity: Int) {
  var head: DLLNode<Pair<String, Int>>? = null
  var tail: DLLNode<Pair<String, Int>>? = null
  val map: MutableMap<String, DLLNode<Pair<String, Int>>> = mutableMapOf()

  fun insertKeyValuePair(key: String, value: Int) {
    when {
      head == null || capacity == 1 -> {
        head = DLLNode(key to value)
        tail = head
        if (capacity == 1) {
          map.clear()
        }
        map[key] = head!!
      }
      else -> {
        if (!map.containsKey(key)) { // ! New Node
          if (map.size == capacity) {
            removeLRU()
          }
          val nodeToInsert = DLLNode(key to value, null, head)
          map[key] = nodeToInsert
          leftShiftHead(nodeToInsert)
        } else { // ! Existing Node
          map[key]?.let { curNode ->
            curNode.value = key to value
            if (curNode != head) {
              if (curNode == tail) {
                leftShiftTail()
              }
              unlink(curNode)
              curNode.next = head
              curNode.prev = null
              leftShiftHead(curNode)
            }
          }
        }
      }
    }
  }

  fun getValueFromKey(key: String): Int? {
    val node = map[key]
    if (node != null && node != head) {
      if (node == tail) {
        leftShiftTail()
      }
      unlink(node)
      node.next = head
      node.prev = null
      leftShiftHead(node)
    }
    return node?.value?.second
  }

  private fun removeLRU() {
    map.remove(tail!!.value.first)
    leftShiftTail()
  }

  fun getMostRecentKey(): String? = head?.value?.first

  private fun unlink(node: DLLNode<Pair<String, Int>>) {
    node.prev?.next = node.next
    node.next?.prev = node.prev
  }

  private fun leftShiftHead(node: DLLNode<Pair<String, Int>>) {
    head?.prev = node
    head = node
  }

  private fun leftShiftTail() {
    tail = tail?.prev
    tail?.next = null
  }

  fun toList(): List<Pair<String, Int>> = head?.toList() ?: emptyList()

  override fun toString(): String = "[${toList().joinToString { "(${it.first}, ${it.second})" }}]"
}

fun main() {
  val lruCache = LRUQueue(2)
  lruCache.insertKeyValuePair("2", 1)
  lruCache.insertKeyValuePair("1", 1)
  lruCache.insertKeyValuePair("2", 3)
  lruCache.insertKeyValuePair("4", 1)
  println(lruCache.getValueFromKey("1")) // null
  println(lruCache.getValueFromKey("2")) // 3
}
