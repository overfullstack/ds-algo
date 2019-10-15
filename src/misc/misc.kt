/* gakshintala created on 9/19/19 */
package misc

class MinHeap(val heap: MutableList<Node>) {
    init {
        heap[0] = heap[1].also { heap[1] = heap[0] }
        heap.mapIndexed { index, node -> node.minHeapIndex = index + 1 }
    }
}

class MaxHeap(val heap: MutableList<Node>) {
    init {
        heap[0] = heap[1].also { heap[1] = heap[0] }
        heap.mapIndexed { index, node -> node.maxHeapIndex = index + 1 }
    }
}

fun main() {
    val arrSeq = arrayOf(0, 1).map { Node(it) }
    val arr = arrSeq.toMutableList()
    val arrCopy1 = arrSeq.toMutableList()
    val minHeap = MinHeap(arrSeq.toMutableList())
    val maxHeap = MaxHeap(arrSeq.toMutableList())

/*    arrSeq.forEach { print(it.minHeapIndex) }
    println()
    arrCopy1.forEach { print(it.minHeapIndex) }
    println()*/
  
    minHeap.heap.forEach { print(it.minHeapIndex) }
    println()
    minHeap.heap.forEach { print(it.maxHeapIndex) }
    println()
}