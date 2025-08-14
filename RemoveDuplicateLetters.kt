package leetcode.string

/**
 * Solution: Remove Duplicate Letters
 * 
 * Algorithm Approach: Greedy Stack with Frequency Tracking
 * 
 * The key insight is to use a greedy approach with a stack to maintain lexicographical order.
 * We process characters from left to right, and for each character, we decide whether to
 * include it in our result based on:
 * 1. Whether we've already used this character
 * 2. Whether we can safely remove the previous character to get a better lexicographical order
 * 
 * Why this approach? We need to ensure the result is lexicographically smallest while
 * maintaining that each character appears exactly once. A stack allows us to "backtrack"
 * and remove characters when we find a better lexicographical option.
 */
fun removeDuplicateLetters(s: String): String {
    // Edge case: empty string
    if (s.isEmpty()) return ""
    
    // Count frequency of each character to know how many times each appears
    // This helps us decide if we can safely remove a character from our result
    val charCount = IntArray(26) { 0 }
    s.forEach { char ->
        charCount[char - 'a']++
    }
    
    // Track which characters we've already used in our result
    // This ensures each character appears only once
    val used = BooleanArray(26) { false }
    
    // Stack to build our result in lexicographical order
    val result = mutableListOf<Char>()
    
    s.forEach { char ->
        val charIndex = char - 'a'
        
        // Decrease the count for this character since we're processing it
        charCount[charIndex]--
        
        // Skip if we've already used this character
        if (used[charIndex]) return@forEach
        
        // While we can improve lexicographical order:
        // 1. Stack is not empty
        // 2. Current character is smaller than top of stack
        // 3. Top character will appear again later (count > 0)
        while (result.isNotEmpty() && 
               char < result.last() && 
               charCount[result.last() - 'a'] > 0) {
            // Remove the larger character from our result
            val removedChar = result.removeAt(result.lastIndex)
            used[removedChar - 'a'] = false
        }
        
        // Add current character to our result
        result.add(char)
        used[charIndex] = true
    }
    
    // Convert result list to string
    return result.joinToString("")
}

fun main() {
    // Test cases
    println(removeDuplicateLetters("bcabc"))      // Expected: "abc"
    println(removeDuplicateLetters("cbacdcbc"))   // Expected: "acdb"
    println(removeDuplicateLetters("abacb"))      // Expected: "abc"
    println(removeDuplicateLetters("cdadabcc"))   // Expected: "adbc"
    println(removeDuplicateLetters("ecbacba"))    // Expected: "eacb"
    println(removeDuplicateLetters("leetcode"))   // Expected: "letcod"
}
