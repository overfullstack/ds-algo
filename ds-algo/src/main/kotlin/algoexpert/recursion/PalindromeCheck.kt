package algoexpert.recursion

tailrec fun String.isPalindrome(left: Int = 0, right:Int = lastIndex): Boolean =
  when {
    left >= right -> true
    this[left] != this[right] -> false
    else -> isPalindrome(left + 1, right - 1)
  }
