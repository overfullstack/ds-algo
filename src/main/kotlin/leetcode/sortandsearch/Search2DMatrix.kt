package leetcode.sortandsearch

tailrec fun searchIn2dMatrix(matrix: Array<IntArray>, target: Int, row: Int = matrix.lastIndex, col: Int = 0): Boolean =
    when { // Imagine a zig-zag ladder like search from left-bottom to right-top
            row < 0 || col > matrix[0].lastIndex -> false
            matrix[row][col] == target -> true
            matrix[row][col] < target -> searchIn2dMatrix(matrix, target, row, col + 1)
            else -> searchIn2dMatrix(matrix, target, row - 1, col)
        }
