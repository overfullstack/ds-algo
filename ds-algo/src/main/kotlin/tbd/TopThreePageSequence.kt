package tbd

import java.io.File
import java.util.PriorityQueue

/**
 * Problem: Find the top 3-page sequence from website request logs
 *
 * Given log entries with format (time, customerId, pageVisited), find the most frequent 3-page
 * sequence across all customers.
 *
 * Example: T0,C1,A T0,C2,E T1,C1,B T1,C2,B T2,C1,C T2,C2,C T3,C1,D T3,C2,D T4,C1,E T5,C3,A T5,C3,E
 *
 * C1 sequence: A->B->C->D->E C2 sequence: E->B->C->D Top 3-page sequence: B->C->D (appears twice)
 */
class TopThreePageSequence {

  data class LogEntry(val time: Int, val customerId: String, val pageVisited: String)

  /**
   * Find the most frequent 3-page sequence from log entries
   *
   * @param logEntries List of log entries
   * @return The most frequent 3-page sequence as a string
   */
  fun findTopThreePageSequence(logEntries: List<String>): String {
    // Parse log entries
    val parsedEntries = logEntries.map { parseLogEntry(it) }

    // Group by customer and sort by time to get correct sequence
    val customerSequences =
      parsedEntries
        .groupBy { it.customerId }
        .mapValues { (_, entries) -> entries.sortedBy { it.time }.map { it.pageVisited } }

    // Count all 3-page sequences
    val sequenceCount = mutableMapOf<String, Int>()

    for (sequence in customerSequences.values) {
      // Generate all possible 3-page sequences for this customer
      for (i in 0..sequence.size - 3) {
        val threePageSeq = "${sequence[i]}->${sequence[i + 1]}->${sequence[i + 2]}"
        sequenceCount[threePageSeq] = sequenceCount.getOrDefault(threePageSeq, 0) + 1
      }
    }

    // Find the sequence with maximum count
    return sequenceCount.maxByOrNull { it.value }?.key ?: ""
  }

  /** Parse a single log entry Expected format: "T0,C1,A" -> LogEntry(0, "C1", "A") */
  private fun parseLogEntry(entry: String): LogEntry {
    val parts = entry.split(",")
    require(parts.size == 3) { "Invalid log entry format: $entry" }

    val time = parts[0].substring(1).toInt() // Remove 'T' prefix
    val customerId = parts[1]
    val pageVisited = parts[2]

    return LogEntry(time, customerId, pageVisited)
  }

  /**
   * Memory-efficient solution for large log files that don't fit in memory
   *
   * This approach:
   * 1. Processes log entries in chunks
   * 2. Sorts entries by customer ID and time
   * 3. Processes one customer at a time to build sequences
   * 4. Uses streaming to handle large datasets
   *
   * @param logEntries Iterator of log entries (can be from file stream)
   * @param chunkSize Size of chunks to process at a time
   * @return The most frequent 3-page sequence
   */
  fun findTopThreePageSequenceStreaming(
    logEntries: Iterator<String>,
    chunkSize: Int = 10000,
  ): String {
    val sequenceCount = mutableMapOf<String, Int>()

    // Process in chunks to manage memory
    val chunk = mutableListOf<LogEntry>()

    while (logEntries.hasNext()) {
      // Fill chunk
      while (chunk.size < chunkSize && logEntries.hasNext()) {
        try {
          chunk.add(parseLogEntry(logEntries.next()))
        } catch (e: Exception) {
          // Skip invalid entries
          continue
        }
      }

      // Process current chunk
      processChunk(chunk, sequenceCount)
      chunk.clear()
    }

    // Process any remaining entries
    if (chunk.isNotEmpty()) {
      processChunk(chunk, sequenceCount)
    }

    return sequenceCount.maxByOrNull { it.value }?.key ?: ""
  }

  /** Process a chunk of log entries */
  private fun processChunk(chunk: List<LogEntry>, sequenceCount: MutableMap<String, Int>) {
    // Group by customer and sort by time
    val customerSequences =
      chunk
        .groupBy { it.customerId }
        .mapValues { (_, entries) -> entries.sortedBy { it.time }.map { it.pageVisited } }

    // Generate 3-page sequences for each customer
    for (sequence in customerSequences.values) {
      for (i in 0..sequence.size - 3) {
        val threePageSeq = "${sequence[i]}->${sequence[i + 1]}->${sequence[i + 2]}"
        sequenceCount[threePageSeq] = sequenceCount.getOrDefault(threePageSeq, 0) + 1
      }
    }
  }

  /**
   * Alternative approach: External sort + streaming For extremely large files that need to be
   * sorted externally
   *
   * @param sortedLogEntries Iterator of log entries sorted by (customerId, time)
   * @return The most frequent 3-page sequence
   */
  fun findTopThreePageSequenceExternalSort(sortedLogEntries: Iterator<String>): String {
    val sequenceCount = mutableMapOf<String, Int>()
    var currentCustomer: String? = null
    val currentSequence = mutableListOf<String>()

    while (sortedLogEntries.hasNext()) {
      val entry = parseLogEntry(sortedLogEntries.next())

      // If we've moved to a new customer, process the previous one
      if (currentCustomer != null && currentCustomer != entry.customerId) {
        processCustomerSequence(currentSequence, sequenceCount)
        currentSequence.clear()
      }

      currentCustomer = entry.customerId
      currentSequence.add(entry.pageVisited)
    }

    // Process the last customer
    if (currentSequence.isNotEmpty()) {
      processCustomerSequence(currentSequence, sequenceCount)
    }

    return sequenceCount.maxByOrNull { it.value }?.key ?: ""
  }

  /** Process a single customer's sequence to extract 3-page sequences */
  private fun processCustomerSequence(
    sequence: List<String>,
    sequenceCount: MutableMap<String, Int>,
  ) {
    for (i in 0..sequence.size - 3) {
      val threePageSeq = "${sequence[i]}->${sequence[i + 1]}->${sequence[i + 2]}"
      sequenceCount[threePageSeq] = sequenceCount.getOrDefault(threePageSeq, 0) + 1
    }
  }
}

/** Utility class for processing large log files */
class LogFileProcessor {

  /**
   * Process a large log file using streaming approach
   *
   * @param filePath Path to the log file
   * @param chunkSize Size of chunks to process at a time
   * @return The most frequent 3-page sequence
   */
  fun processLargeLogFile(filePath: String, chunkSize: Int = 10000): String {
    val solution = TopThreePageSequence()

    // Create iterator from file lines
    val file = File(filePath)
    val logEntries = file.useLines { lines -> lines.filter { it.isNotBlank() }.iterator() }

    return solution.findTopThreePageSequenceStreaming(logEntries, chunkSize)
  }

  /**
   * Process a large log file with external sorting Assumes the file is already sorted by
   * (customerId, time)
   *
   * @param filePath Path to the sorted log file
   * @return The most frequent 3-page sequence
   */
  fun processSortedLogFile(filePath: String): String {
    val solution = TopThreePageSequence()

    val file = File(filePath)
    val logEntries = file.useLines { lines -> lines.filter { it.isNotBlank() }.iterator() }

    return solution.findTopThreePageSequenceExternalSort(logEntries)
  }

  /**
   * Sort a large log file externally by (customerId, time) This is useful for very large files that
   * need to be sorted
   *
   * @param inputPath Path to the unsorted log file
   * @param outputPath Path to write the sorted log file
   * @param chunkSize Size of chunks for external sorting
   */
  fun sortLogFileByCustomerAndTime(inputPath: String, outputPath: String, chunkSize: Int = 10000) {
    val inputFile = File(inputPath)
    val outputFile = File(outputPath)

    // Read and sort in chunks
    val chunks = mutableListOf<List<String>>()
    val currentChunk = mutableListOf<String>()

    inputFile.useLines { lines ->
      lines.forEach { line ->
        if (line.isNotBlank()) {
          currentChunk.add(line)
          if (currentChunk.size >= chunkSize) {
            chunks.add(
              currentChunk.sortedWith(
                compareBy(
                  { it.split(",")[1] }, // customerId
                  { it.split(",")[0].substring(1).toInt() }, // time
                )
              )
            )
            currentChunk.clear()
          }
        }
      }
    }

    // Add remaining entries
    if (currentChunk.isNotEmpty()) {
      chunks.add(
        currentChunk.sortedWith(
          compareBy(
            { it.split(",")[1] }, // customerId
            { it.split(",")[0].substring(1).toInt() }, // time
          )
        )
      )
    }

    // Merge sorted chunks and write to output
    outputFile.bufferedWriter().use { writer ->
      val iterators = chunks.map { it.iterator() }
      val heap =
        PriorityQueue<Pair<String, Int>> { a, b ->
          val aParts = a.first.split(",")
          val bParts = b.first.split(",")
          val customerCompare = aParts[1].compareTo(bParts[1])
          if (customerCompare != 0) customerCompare
          else aParts[0].substring(1).toInt().compareTo(bParts[0].substring(1).toInt())
        }

      // Initialize heap
      iterators.forEachIndexed { index, iterator ->
        if (iterator.hasNext()) {
          heap.offer(Pair(iterator.next(), index))
        }
      }

      // Merge and write
      while (heap.isNotEmpty()) {
        val (line, chunkIndex) = heap.poll()
        writer.write(line)
        writer.newLine()

        if (iterators[chunkIndex].hasNext()) {
          heap.offer(Pair(iterators[chunkIndex].next(), chunkIndex))
        }
      }
    }
  }
}

/** Test the solution with the provided example */
fun main() {
  val solution = TopThreePageSequence()

  val logEntries =
    listOf(
      "T0,C1,A",
      "T0,C2,E",
      "T1,C1,B",
      "T1,C2,B",
      "T2,C1,C",
      "T2,C2,C",
      "T3,C1,D",
      "T3,C2,D",
      "T4,C1,E",
      "T5,C3,A",
      "T5,C3,E",
    )

  println("=== Testing with in-memory approach ===")
  val result1 = solution.findTopThreePageSequence(logEntries)
  println("Top 3-page sequence: $result1")

  println("\n=== Testing with streaming approach ===")
  val result2 = solution.findTopThreePageSequenceStreaming(logEntries.iterator())
  println("Top 3-page sequence (streaming): $result2")

  println("\n=== Testing with external sort approach ===")
  // Create sorted version for external sort test
  val sortedEntries =
    logEntries.sortedWith(
      compareBy(
        { it.split(",")[1] }, // customerId
        { it.split(",")[0].substring(1).toInt() }, // time
      )
    )
  val result3 = solution.findTopThreePageSequenceExternalSort(sortedEntries.iterator())
  println("Top 3-page sequence (external sort): $result3")

  // Expected: B->C->D (appears twice - once in C1's sequence A->B->C->D->E,
  // once in C2's sequence E->B->C->D)
}
