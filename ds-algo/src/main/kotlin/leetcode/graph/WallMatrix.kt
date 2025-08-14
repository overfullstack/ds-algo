package leetcode.graph

/**
 * Wall Matrix - Count lakes in an island
 *
 * Given a matrix where 1 represents land and 0 represents water, when clicking on a land pixel,
 * return the number of lakes in that island. A lake is a water region completely surrounded by land
 * (not touching matrix edges or island boundary).
 */
fun countLakesInIsland(matrix: Array<IntArray>, clickRow: Int, clickCol: Int): Int {
  // Edge case: clicked position must be valid land
  if (!isValidLand(matrix, clickRow, clickCol)) return 0

  // Step 1: Mark all land cells of the clicked island
  // Using a mutable copy to mark visited cells without affecting original
  val matrixCopy = matrix.map { it.clone() }.toTypedArray()
  markIslandCells(matrixCopy, clickRow, clickCol)

  // Step 2: Find all water regions in the entire matrix
  // Any water cell that's still 0 could be part of a lake
  val lakes = findAllLakes(matrixCopy)

  return lakes
}

// Mark all land cells of the island containing the clicked position
// We change land (1) to visited marker (2) to distinguish from water
private fun markIslandCells(matrix: Array<IntArray>, row: Int, col: Int) {
  // Base cases: out of bounds, not land, or already visited
  when {
    row !in matrix.indices -> return
    col !in matrix[0].indices -> return
    matrix[row][col] != 1 -> return
  }

  // Mark current cell as visited (part of our island)
  matrix[row][col] = 2

  // Recursively mark all 4-connected land neighbors
  directions.forEach { (dr, dc) -> markIslandCells(matrix, row + dr, col + dc) }
}

// Count all water regions that are completely surrounded by our island
private fun findAllLakes(matrix: Array<IntArray>): Int {
  var lakeCount = 0
  val visited = mutableSetOf<Pair<Int, Int>>()

  // Check each cell in the matrix
  for (row in matrix.indices) {
    for (col in matrix[0].indices) {
      val cell = row to col
      // If we find unvisited water, check if it's a lake
      if (matrix[row][col] == 0 && cell !in visited) {
        // Find all cells in this water region
        val waterRegion = findWaterRegion(matrix, row, col)
        visited.addAll(waterRegion)

        // Check if this water region is a lake (surrounded by our island)
        if (isLake(matrix, waterRegion)) {
          lakeCount++
        }
      }
    }
  }

  return lakeCount
}

// Find all connected water cells starting from given position
private fun findWaterRegion(
  matrix: Array<IntArray>,
  startRow: Int,
  startCol: Int,
  visited: Set<Pair<Int, Int>> = emptySet(),
): Set<Pair<Int, Int>> =
  when {
    // Base cases: out of bounds or already visited
    startRow !in matrix.indices -> visited
    startCol !in matrix[0].indices -> visited
    (startRow to startCol) in visited -> visited
    matrix[startRow][startCol] != 0 -> visited // Not water
    else -> {
      // Add current water cell to the region
      val newVisited = visited + (startRow to startCol)
      // Explore all 4 directions to find connected water
      directions.fold(newVisited) { acc, (dr, dc) ->
        findWaterRegion(matrix, startRow + dr, startCol + dc, acc)
      }
    }
  }

// Check if a water region is a lake (completely surrounded by our island)
private fun isLake(matrix: Array<IntArray>, waterRegion: Set<Pair<Int, Int>>): Boolean {
  // A lake must not touch the matrix boundary
  val touchesBoundary =
    waterRegion.any { (row, col) ->
      row == 0 || row == matrix.lastIndex || col == 0 || col == matrix[0].lastIndex
    }

  if (touchesBoundary) return false

  // A lake must be completely surrounded by our island (marked as 2)
  // Check if all neighboring land cells belong to our island
  return waterRegion.all { (row, col) ->
    directions.all { (dr, dc) ->
      val newRow = row + dr
      val newCol = col + dc
      // Neighbor must be either water (part of same region) or our island (2)
      when {
        newRow !in matrix.indices -> false // Out of bounds
        newCol !in matrix[0].indices -> false // Out of bounds
        matrix[newRow][newCol] == 0 ->
          (newRow to newCol) in waterRegion // Water must be in same region
        matrix[newRow][newCol] == 2 -> true // Our island
        else -> false // Other land (1) means not surrounded by our island
      }
    }
  }
}

// Helper function to check if a position contains valid land
private fun isValidLand(matrix: Array<IntArray>, row: Int, col: Int): Boolean =
  row in matrix.indices && col in matrix[0].indices && matrix[row][col] == 1

// Direction vectors for 4-connected neighbors (up, down, left, right)
private val directions =
  listOf(
    -1 to 0, // up
    1 to 0, // down
    0 to -1, // left
    0 to 1, // right
  )

/**
 * Find all shoreline cells of an island Returns a data class containing outer shoreline, inner
 * shorelines (around lakes), and the lakes
 */
fun findShorelines(matrix: Array<IntArray>, clickRow: Int, clickCol: Int): ShorelineResult {
  // Edge case: clicked position must be valid land
  if (!isValidLand(matrix, clickRow, clickCol)) {
    return ShorelineResult(emptySet(), emptyList(), emptyList())
  }

  // Step 1: Mark all land cells of the clicked island
  val matrixCopy = matrix.map { it.clone() }.toTypedArray()
  markIslandCells(matrixCopy, clickRow, clickCol)

  // Step 2: Find all water regions and identify lakes
  val allWaterRegions = findAllWaterRegions(matrixCopy)
  val lakes = allWaterRegions.filter { isLake(matrixCopy, it) }
  val oceanWater = allWaterRegions.filter { !isLake(matrixCopy, it) }.flatten().toSet()

  // Step 3: Find outer shoreline (island cells adjacent to ocean/outside water)
  val outerShoreline = findOuterShoreline(matrixCopy, oceanWater)

  // Step 4: Find inner shorelines (island cells adjacent to each lake)
  val innerShorelines = lakes.map { lake -> findInnerShoreline(matrixCopy, lake) }

  return ShorelineResult(outerShoreline, innerShorelines, lakes)
}

// Find all water regions in the matrix (both lakes and ocean)
private fun findAllWaterRegions(matrix: Array<IntArray>): List<Set<Pair<Int, Int>>> {
  val visited = mutableSetOf<Pair<Int, Int>>()
  val waterRegions = mutableListOf<Set<Pair<Int, Int>>>()

  for (row in matrix.indices) {
    for (col in matrix[0].indices) {
      val cell = row to col
      if (matrix[row][col] == 0 && cell !in visited) {
        val region = findWaterRegion(matrix, row, col)
        visited.addAll(region)
        waterRegions.add(region)
      }
    }
  }

  return waterRegions
}

// Find outer shoreline: island cells adjacent to non-lake water
private fun findOuterShoreline(
  matrix: Array<IntArray>,
  oceanWater: Set<Pair<Int, Int>>,
): Set<Pair<Int, Int>> =
  // Find all island cells (marked as 2) that have at least one ocean water neighbor
  matrix.indices
    .asSequence()
    .flatMap { row -> matrix[0].indices.map { col -> row to col } }
    .filter { (row, col) -> matrix[row][col] == 2 } // Island cells only
    .filter { (row, col) ->
      // Check if any neighbor is ocean water
      directions.any { (dr, dc) ->
        val neighbor = (row + dr) to (col + dc)
        neighbor in oceanWater
      }
    }
    .toSet()

// Find inner shoreline: island cells adjacent to a specific lake
private fun findInnerShoreline(
  matrix: Array<IntArray>,
  lake: Set<Pair<Int, Int>>,
): Set<Pair<Int, Int>> =
  // For each water cell in the lake, find adjacent island cells
  lake
    .asSequence()
    .flatMap { (row, col) ->
      // Get all neighbors of this water cell
      directions.map { (dr, dc) -> (row + dr) to (col + dc) }
    }
    .filter { (row, col) ->
      // Keep only valid island cells (marked as 2)
      row in matrix.indices && col in matrix[0].indices && matrix[row][col] == 2
    }
    .toSet() // Remove duplicates

// Data class to return shoreline analysis results
data class ShorelineResult(
  val outerShoreline: Set<Pair<Int, Int>>, // Cells bordering ocean/outside water
  val innerShorelines: List<Set<Pair<Int, Int>>>, // Cells bordering each lake
  val lakes: List<Set<Pair<Int, Int>>>, // The lake regions themselves
)

// Utility function to visualize the shorelines in a matrix
fun visualizeShorelines(matrix: Array<IntArray>, result: ShorelineResult): Array<CharArray> {
  // Create visualization matrix
  val visual =
    Array(matrix.size) { row ->
      CharArray(matrix[0].size) { col ->
        when (matrix[row][col]) {
          0 -> '~' // Water
          1 -> '#' // Land
          else -> '?'
        }
      }
    }

  // Mark outer shoreline with 'O'
  result.outerShoreline.forEach { (row, col) -> visual[row][col] = 'O' }

  // Mark inner shorelines with 'I'
  result.innerShorelines.flatten().forEach { (row, col) -> visual[row][col] = 'I' }

  // Mark lakes with 'L'
  result.lakes.flatten().forEach { (row, col) -> visual[row][col] = 'L' }

  return visual
}

fun main() {
  // Test case 1: Rectangle with one lake
  val matrix1 =
    arrayOf(
      intArrayOf(1, 1, 1, 1, 1),
      intArrayOf(1, 0, 0, 0, 1),
      intArrayOf(1, 0, 0, 0, 1),
      intArrayOf(1, 1, 1, 1, 1),
    )
  println("Lakes count: ${countLakesInIsland(matrix1, 0, 0)}") // Expected: 1

  val result1 = findShorelines(matrix1, 0, 0)
  println("Outer shoreline size: ${result1.outerShoreline.size}")
  println("Inner shorelines: ${result1.innerShorelines.map { it.size }}")

  // Visualize the shorelines
  val visual1 = visualizeShorelines(matrix1, result1)
  println("\nVisualization (O=Outer shoreline, I=Inner shoreline, L=Lake, #=Land):")
  visual1.forEach { println(it.joinToString(" ")) }

  // Test case 2: Island with ocean water
  val matrix2 =
    arrayOf(
      intArrayOf(0, 0, 0, 0, 0, 0, 0),
      intArrayOf(0, 1, 1, 1, 1, 1, 0),
      intArrayOf(0, 1, 0, 0, 0, 1, 0),
      intArrayOf(0, 1, 0, 0, 0, 1, 0),
      intArrayOf(0, 1, 1, 1, 1, 1, 0),
      intArrayOf(0, 0, 0, 0, 0, 0, 0),
    )

  println("\n\nTest case 2:")
  val result2 = findShorelines(matrix2, 1, 1)
  println("Outer shoreline size: ${result2.outerShoreline.size}")
  println("Inner shorelines: ${result2.innerShorelines.map { it.size }}")

  val visual2 = visualizeShorelines(matrix2, result2)
  println("\nVisualization:")
  visual2.forEach { println(it.joinToString(" ")) }
}
