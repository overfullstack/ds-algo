fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
    val n = position.size
    
    // Create pairs of (position, speed) and sort by position in descending order
    // We process cars from closest to target to farthest
    val cars = position.indices
        .map { position[it] to speed[it] }
        .sortedByDescending { it.first }
    
    val stack = mutableListOf<Double>()
    
    for ((pos, spd) in cars) {
        // Calculate time to reach target for current car
        // Time = (target - position) / speed
        val timeToTarget = (target - pos).toDouble() / spd
        
        // If current car takes LESS time than the car ahead (is FASTER),
        // it will catch up and form a fleet - don't add to stack
        // If current car takes MORE time than the car ahead (is SLOWER),
        // it will NEVER catch up and forms a separate fleet - add to stack
        if (stack.isEmpty() || timeToTarget > stack.last()) {
            stack.add(timeToTarget)
        }
        // If timeToTarget <= stack.last(), current car is faster and will catch up
        // No need to add it to stack as it becomes part of the existing fleet
    }
    
    // The size of the stack represents the number of car fleets
    return stack.size
}
