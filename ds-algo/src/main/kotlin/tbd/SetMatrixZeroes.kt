fun setZeroes(matrix: Array<IntArray>): Unit {
  val m = matrix.size
  val n = matrix[0].size

  // Use first row and first column as markers to track which rows/columns should be zero
  // This allows us to solve the problem in-place without extra space
  var firstRowHasZero = false
  var firstColHasZero = false

  // Check if first row has any zeros
  for (j in 0 until n) {
    if (matrix[0][j] == 0) {
      firstRowHasZero = true
      break
    }
  }

  // Check if first column has any zeros
  for (i in 0 until m) {
    if (matrix[i][0] == 0) {
      firstColHasZero = true
      break
    }
  }

  // Use first row and first column as markers
  // For any element matrix[i][j] = 0, mark matrix[i][0] = 0 and matrix[0][j] = 0
  for (i in 1 until m) {
    for (j in 1 until n) {
      if (matrix[i][j] == 0) {
        matrix[i][0] = 0 // Mark row i should be zero
        matrix[0][j] = 0 // Mark column j should be zero
      }
    }
  }

  // Set rows to zero based on first column markers (excluding first row)
  for (i in 1 until m) {
    if (matrix[i][0] == 0) {
      for (j in 1 until n) {
        matrix[i][j] = 0
      }
    }
  }

  // Set columns to zero based on first row markers (excluding first column)
  for (j in 1 until n) {
    if (matrix[0][j] == 0) {
      for (i in 1 until m) {
        matrix[i][j] = 0
      }
    }
  }

  // Handle first row if it originally had zeros
  if (firstRowHasZero) {
    for (j in 0 until n) {
      matrix[0][j] = 0
    }
  }

  // Handle first column if it originally had zeros
  if (firstColHasZero) {
    for (i in 0 until m) {
      matrix[i][0] = 0
    }
  }
}
