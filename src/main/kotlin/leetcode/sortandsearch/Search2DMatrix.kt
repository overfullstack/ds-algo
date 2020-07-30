package leetcode.sortandsearch

tailrec fun searchIn2dMatrix(matrix: Array<IntArray>, target: Int, row: Int = matrix.lastIndex, col: Int = 0): Boolean =
    when { // * Imagine a zig-zag ladder like search from left-bottom to right-top
        row < 0 || col > matrix[0].lastIndex -> false
        matrix[row][col] < target -> searchIn2dMatrix(matrix, target, row, col + 1)
        matrix[row][col] > target -> searchIn2dMatrix(matrix, target, row - 1, col)
        else -> true
    }
// This can also be solved zig-zag from right-top to left-bottom.
