package leetcode.dp

/** https://leetcode.com/problems/different-ways-to-add-parentheses/ */
val opSet = setOf('+', '-', '*')

fun diffWaysToCompute(input: String): List<Int> =
  when {
    input.isEmpty() -> emptyList()
    else ->
      input.indices
        .asSequence()
        .filter { input[it] in opSet }
        .flatMap {
          val leftList = diffWaysToCompute(input.substring(0 until it))
          val rightList = diffWaysToCompute(input.substring(it + 1..input.lastIndex))
          // Cartesian of left and right
          leftList.asSequence().flatMap { left ->
            rightList.asSequence().map { right ->
              when (input[it]) {
                '-' -> left - right
                '+' -> left + right
                else -> left * right
              }
            }
          }
        }
        .toList()
        .ifEmpty { listOf(input.toInt()) } // ! Base case with only single/multi-digit number
  }
