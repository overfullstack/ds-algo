fun racecar(target: Int): Int {
  // Edge case: if target is 0, no instructions needed
  if (target == 0) return 0

  // Use BFS to find shortest path to target
  // Each state consists of (position, speed)
  val queue = ArrayDeque<Triple<Int, Int, Int>>()
  val visited = mutableSetOf<Pair<Int, Int>>()

  // Start at position 0 with speed 1
  queue.add(Triple(0, 1, 0)) // (position, speed, instructions_count)
  visited.add(0 to 1)

  while (queue.isNotEmpty()) {
    val (position, speed, instructions) = queue.removeFirst()

    // Check if we've reached the target
    if (position == target) {
      return instructions
    }

    // Try 'A' instruction: accelerate
    val newPosition = position + speed
    val newSpeed = speed * 2

    // Only explore if this state hasn't been visited and position is reasonable
    // Optimization: don't explore positions too far beyond target
    if (
      newPosition to newSpeed !in visited && newPosition <= target * 2 && newPosition >= -target * 2
    ) {

      visited.add(newPosition to newSpeed)
      queue.add(Triple(newPosition, newSpeed, instructions + 1))
    }

    // Try 'R' instruction: reverse
    val reverseSpeed = if (speed > 0) -1 else 1

    // Only explore if this state hasn't been visited
    if (position to reverseSpeed !in visited) {
      visited.add(position to reverseSpeed)
      queue.add(Triple(position, reverseSpeed, instructions + 1))
    }
  }

  // This should never happen for valid inputs, but return -1 as fallback
  return -1
}
