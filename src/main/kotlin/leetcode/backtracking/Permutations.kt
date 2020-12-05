package leetcode.backtracking

fun IntArray.permute(
    permutation: List<Int> = emptyList(),
    used: BooleanArray = BooleanArray(size)
): List<List<Int>> =
    if (permutation.size == size) {
        listOf(permutation)
    } else {
        indices.filterNot { used[it] }.flatMap { unusedIndex ->
            used[unusedIndex] = true
            permute(permutation + this[unusedIndex], used).also { used[unusedIndex] = false }
        }
    }
