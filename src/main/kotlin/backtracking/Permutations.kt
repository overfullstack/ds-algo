package backtracking

fun printPermutations(str: String, permutation: String, used: BooleanArray) {
    val strLength = str.length
    if (permutation.length == strLength) {
        println(permutation)
        return
    }
    for (i in 0 until strLength) {
        if (!used[i]) {
            used[i] = true
            printPermutations(str, permutation + str[i], used)
            used[i] = false
        }
    }
}

fun main() {
    printPermutations("abc", "", BooleanArray(3))
}

fun permute(
    nums: IntArray,
    permutation: List<Int> = emptyList(),
    used: BooleanArray = BooleanArray(nums.size)
): List<List<Int>> =
    if (permutation.size == nums.size) {
        listOf(permutation)
    } else {
        nums.indices.filter { !used[it] }.flatMap { unusedIndex ->
            used[unusedIndex] = true
            permute(nums, permutation + nums[unusedIndex], used).also { used[unusedIndex] = false }
        }
    }

fun permuteWithDups(
    nums: IntArray,
    freqMap: MutableMap<Int, Int> = nums.groupBy { it }.mapValues { it.value.size }.toMutableMap(),
    permutation: List<Int> = emptyList(),
): List<List<Int>> =
    if (permutation.size == nums.size) {
        listOf(permutation)
    } else {
        freqMap.keys.toList().flatMap { num ->
            freqMap.compute(num) { _, freq -> if (freq == 1) null else freq?.dec() }
            permuteWithDups(nums, freqMap, permutation + num).also { freqMap.merge(num, 1) { old, _ -> old.inc() } }
        }
    }
