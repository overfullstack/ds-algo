package backtracking

fun printPermutations(str: String, permutation: String, used: BooleanArray) {
  val strLength = str.length
  if (permutation.length == strLength) {
    println(permutation)
    return
  }
  str.indices.asSequence()
  .filter { !used[it] }
  .onEach { used[it] = true }
  .forEach { 
    printPermutations(str, permutation + str[it], used)
    used[it] = false 
  }
}

fun main() {
  printPermutations("abc", "", BooleanArray(3))
}
