package leetcode.array

fun isValidSudoku(board: Array<CharArray>): Boolean =
  board.isValidRows() && board.isValidColumns() && board.isValidSubBoxes()

// Validate each row contains digits 1-9 without repetition
private fun Array<CharArray>.isValidRows(): Boolean =
  all { row ->
    row.asSequence()
      .filter { it.isDigit() }
      .map { it - '0' }
      .hasNoDuplicates()
  }

// Validate each column contains digits 1-9 without repetition
private fun Array<CharArray>.isValidColumns(): Boolean =
  indices.all { col ->
    asSequence()
      .map { it[col] }
      .filter { it.isDigit() }
      .map { it - '0' }
      .hasNoDuplicates()
  }

// Validate each 3x3 sub-box contains digits 1-9 without repetition
private fun Array<CharArray>.isValidSubBoxes(): Boolean =
  (0..6 step 3).all { startRow ->
    (0..6 step 3).all { startCol ->
      getSubBoxDigits(startRow, startCol).hasNoDuplicates()
    }
  }

// Extract digits from a 3x3 sub-box starting at (startRow, startCol)
private fun Array<CharArray>.getSubBoxDigits(startRow: Int, startCol: Int): Sequence<Int> =
  (startRow until startRow + 3).asSequence()
    .flatMap { row ->
      (startCol until startCol + 3).asSequence()
        .map { col -> this[row][col] }
    }
    .filter { it.isDigit() }
    .map { it - '0' }

// Check if sequence contains only unique elements (no duplicates)
private fun Sequence<Int>.hasNoDuplicates(): Boolean {
  val seen = mutableSetOf<Int>()
  return all { digit ->
    digit in 1..9 && seen.add(digit)
  }
}
