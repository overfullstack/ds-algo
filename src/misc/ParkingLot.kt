/* gakshintala created on 9/18/19 */
package misc

class MyDs(inputList: List<Node>) {
    private val minHeap = Heap(inputList.toMutableList(), { a, b -> a.value < b.value }, { index, node -> node.minHeapIndex = index })
    private val maxHeap = Heap(inputList.toMutableList(), { a, b -> a.value > b.value }, { index, node -> node.maxHeapIndex = index })

    fun deleteMin() {
        val deleteRoot = minHeap.deleteRoot()
        maxHeap.deleteAt(deleteRoot.maxHeapIndex)
        minHeap.updateIndex()
        maxHeap.updateIndex()
    }
    
    fun isEmpty(): Boolean {
        return minHeap.isEmpty() && maxHeap.isEmpty()
    }
    
    fun delete(value: Int) {
        
    }
    
    fun display() {
        minHeap.display()
        maxHeap.display()
    }
}

class Node(val value: Int) {
    var minHeapIndex: Int = -1
    var maxHeapIndex: Int = -1
}

class Heap(val heap: MutableList<Node>, val compare: (Node, Node) -> Boolean, private val updateIndexFn: (Int, Node) -> Unit) {
    private var size: Int = heap.size

    init {
        for (i in (size / 2) - 1 downTo 0) {
            heapify(i)
        }
        updateIndex()
    }

    fun isEmpty(): Boolean {
        return size == 0
    }

    private fun heapify(rootIndex: Int) {
        val left = 2 * rootIndex + 1
        val right = 2 * rootIndex + 2
        var heapIndex = rootIndex
        if (left < size && compare(heap[left], heap[heapIndex])) {
            heapIndex = left
        }
        if (right < size && compare(heap[right], heap[heapIndex])) {
            heapIndex = right
        }

        if (heapIndex != rootIndex) {
            heap[rootIndex] = heap[heapIndex].also { heap[heapIndex] = heap[rootIndex] }
            heapify(heapIndex)
        }
    }

    fun updateIndex() {
        heap.forEachIndexed(updateIndexFn)
    }

    fun deleteRoot(): Node {
        val root = heap[0]
        deleteAt(0)
        return root
    }
    
    fun delete(value: Int) {
        
    }
    
    fun deleteAt(index: Int) {
        heap[index] = heap[size - 1]
        size--
        heapify(0)
    }

    fun insert(value: Int) {
        size++
        heap.add(Node(value))
        heapify(0)
    }

    fun display() {
        heap.take(size).forEach { print("${it.value} ") }
        println()
    }
}

fun main() {
    val inputList = readLine()!!.split(",").map { Node(it.toInt()) }
    val myDs = MyDs(inputList)
    
    myDs.display()
    while(!myDs.isEmpty()) {
        myDs.deleteMin()
        myDs.display()
    }
    /*val minHeap = Heap(inputSeq.toMutableList()) { a, b -> a.value < b.value }
    minHeap.insert(4)
    minHeap.display()
    while (!minHeap.isEmpty()) {
        println(minHeap.deleteRoot())
    }

    val maxHeap = Heap(inputSeq.toMutableList()) { a, b -> a.value > b.value }
    maxHeap.insert(4)
    maxHeap.display()
    while (!maxHeap.isEmpty()) {
        println(maxHeap.deleteRoot())
    }*/
}