package algoexpert.ll.lrucache

import ds.ll.DLLNode

/* 18 Aug 2024 21:21 */
fun lruCache(lruCacheArgs: LRUCacheArgs): Pair<List<String?>, List<Pair<String, Int>>> {
  val lruQueue = LRUQueue(lruCacheArgs.maxSize)
  val queryResults = mutableListOf<String?>()
  for ((args, method) in lruCacheArgs.classMethodsToCall) {
    when (method) {
      "insertKeyValuePair" -> {
        val key = args[0] as String
        val value = (args[1] as Double).toInt()
        lruQueue.insertKeyValuePair(key, value)
      }
      "getMostRecentKey" -> queryResults.add(lruQueue.getMostRecentKey())
      "getValueFromKey" -> {
        val key = args[0] as String
        queryResults.add(lruQueue.getValueFromKey(key)?.toString())
      }
    }
  }
  return queryResults to lruQueue.toList()
}

class LRUQueue(val maxSize: Int) {
  var head: DLLNode<Pair<String, Int>>? = null
  var tail: DLLNode<Pair<String, Int>>? = null
  var size: Int = 0
  val map: MutableMap<String, DLLNode<Pair<String, Int>>> = mutableMapOf()

  fun insertKeyValuePair(key: String, value: Int) {
    if (head == null || maxSize == 1) {
      head = DLLNode(key to value)
      tail = head
      if (maxSize == 1) {
        map.clear()
      }
      map[key] = head!!
      size++
    } else {
      val node =
        map[key]?.let {
          it.value = key to value
          unlink(it)
          it.next = head
          it.prev = null
          it
        }
          ?: run {
            if (size == maxSize) {
              removeLRU()
            }
            size++
            val nodeToInsert = DLLNode(key to value, null, head)
            map[key] = nodeToInsert
            nodeToInsert
          }
      head?.prev = node
      head = node
    }
  }

  private fun removeLRU() {
    map.remove(tail!!.value.first)
    tail = tail?.prev
    tail?.next = null
    size--
  }

  private fun unlink(node: DLLNode<Pair<String, Int>>) {
    node.prev?.next = node.next
    node.next?.prev = node.prev
  }

  fun getMostRecentKey(): String? = head?.value?.first

  fun getValueFromKey(key: String): Int? {
    val node = map[key]
    if (node != null && node != head) {
      if (node == tail) {
        tail = tail?.prev
        tail?.next = null
      }
      unlink(node)
      head?.prev = node
      node.next = head
      head = node
    }
    return node?.value?.second
  }

  fun toList(): List<Pair<String, Int>> = head?.toList() ?: emptyList()

  override fun toString(): String =
    "[${toList().map { "(${it.first}, ${it.second})" }.joinToString(", ")}]"
}
