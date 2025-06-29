package sorting

fun IntArray.heapSort() {
  // * To apply `heapify`, all the children must be heapified.
  // * Starting from `(size/2)-1` coz imagine the array like a binary tree. Parent for first set of
  // leaf nodes would be (size/2) - 1
  val mid = (size / 2) - 1
  for (i in mid downTo 0) { // ! Traverse in reverse - mid to 0
    heapify(size, i)
  }
  for (i in lastIndex downTo 0) { // ! Sorts from last to first
    this[i] = this[0].also { this[0] = this[i] } // swap last in the **window** with first
    heapify(i, 0)
  }
}

tailrec fun IntArray.heapify(
  heapSize: Int,
  rootIndex: Int,
  compare: Int.(Int) -> Boolean = fun Int.(b: Int) = this > b,
) {
  var heapIndex = rootIndex
  val leftIndex = 2 * rootIndex + 1
  val rightIndex = 2 * rootIndex + 2
  if (leftIndex < heapSize && this[leftIndex].compare(this[heapIndex])) {
    heapIndex = leftIndex
  }
  if (rightIndex < heapSize && this[rightIndex].compare(this[heapIndex])) {
    heapIndex = rightIndex
  }

  if (heapIndex != rootIndex) { // Observe we use index to swap.
    this[heapIndex] = this[rootIndex].also { this[rootIndex] = this[heapIndex] }
    heapify(heapSize, heapIndex)
  }
}

fun main() {
  val nums = intArrayOf(2, 12, 89, 23, 76, 43, 12)
  nums.heapSort()
  print(nums.joinToString())
}
