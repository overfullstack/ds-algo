This is called **"Implicit Graph Dijkstra"** or **"Dijkstra's on an Implicit Graph"**.

### What Makes It "Implicit"?

Unlike traditional Dijkstra's where you have a pre-built graph with explicit nodes and edges, this variant **constructs the graph dynamically** during the search:

```java
// Instead of having pre-defined edges, we compute them on-the-fly:
while (isValid(nextX + direction[0], nextY + direction[1], maze)
        && maze[nextX + direction[0]][nextY + direction[1]] != 1) {
    nextX += direction[0];
    nextY += direction[1];
    nextDistance++; // Dynamically calculating edge weight
}
```

### Key Characteristics:

1. **Nodes**: Grid positions where the ball can stop (against a wall)
2. **Edges**: Computed dynamically by "rolling" in each direction until hitting a wall
3. **Weights**: Distance the ball travels = number of cells it rolls through

### Alternative Names:

- **"On-the-fly Graph Construction"**
- **"Dynamic Edge Weight Dijkstra"**
- **"State Space Search with Dijkstra"** (where each stopping position is a "state")

### Why Use This Approach?

Building the full explicit graph would be memory-intensive since:
- You'd need to pre-compute all possible "roll" paths from every cell
- Many of those paths might never be explored
- The implicit approach only computes paths as needed

This pattern is common in grid-based problems where the "edges" represent complex movements (rolling, jumping, teleporting) rather than simple adjacent-cell moves.
