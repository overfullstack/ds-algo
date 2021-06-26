package hackerrank.stack

import java.util.Stack

val stk1 = Stack<Int>()
val stk2 = Stack<Int>()

fun main() {
  val noOfQueries = readLine()!!.toInt()
  repeat(noOfQueries) {
    val query = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    when (query[0]) {
      1 -> enqueue(query[1])
      2 -> dequeue()
      3 -> popPrint()
    }
  }
}

fun enqueue(value: Int) {
  stk1.push(value)
}

fun dequeue() {
  if (stk2.isEmpty()) {
    loadStk1To2()
  }
  if (!stk2.isEmpty()) {
    stk2.pop()
  }
}

fun popPrint() {
  if (stk2.isEmpty()) {
    loadStk1To2()
  }
  println(stk2.peek())
}

private fun loadStk1To2() {
  while (!stk1.isEmpty()) {
    stk2.push(stk1.pop())
  }
}
