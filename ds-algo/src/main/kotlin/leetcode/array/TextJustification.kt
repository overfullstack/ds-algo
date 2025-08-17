package leetcode.array

fun fullJustify(words: Array<String>, maxWidth: Int): List<String> =
  words.toList().justifyLines(maxWidth)

// Process words into justified lines using greedy approach
// We need to group words first, then justify each group separately
private fun List<String>.justifyLines(maxWidth: Int): List<String> {
  val lines = mutableListOf<List<String>>()
  var currentLine = mutableListOf<String>()
  var currentLength = 0

  // Greedy word packing: add words to current line until we exceed maxWidth
  // This ensures we use maximum available space while respecting constraints
  forEach { word ->
    val wordLength = word.length
    // We need a space between words, but not before the first word
    val spacesNeeded = if (currentLine.isNotEmpty()) 1 else 0
    val totalLength = currentLength + spacesNeeded + wordLength

    if (totalLength <= maxWidth) {
      // Word fits in current line, add it
      currentLine.add(word)
      currentLength = totalLength
    } else {
      // Word doesn't fit, finalize current line and start new one
      // We create a copy of currentLine to avoid mutation issues
      lines.add(currentLine.toList())
      currentLine = mutableListOf(word)
      currentLength = wordLength
    }
  }

  // Don't forget the last line that was being built
  if (currentLine.isNotEmpty()) {
    lines.add(currentLine.toList())
  }

  // Now justify each line according to the rules:
  // - Last line: left-justified (no extra spaces between words)
  // - Single word lines: left-justified (no spaces to distribute)
  // - Other lines: fully justified with even space distribution
  return lines
    .asSequence()
    .mapIndexed { index, lineWords ->
      when {
        index == lines.size - 1 -> lineWords.leftJustify(maxWidth)
        lineWords.size == 1 -> lineWords.leftJustify(maxWidth)
        else -> lineWords.fullyJustify(maxWidth)
      }
    }
    .toList()
}

// Left-justify a line (for last line or single word)
// This is simpler than full justification - just add trailing spaces to reach maxWidth
private fun List<String>.leftJustify(maxWidth: Int): String {
  val result = joinToString(" ")
  // Calculate how many spaces we need to add to reach maxWidth
  return result + " ".repeat(maxWidth - result.length)
}

// Fully justify a line with even space distribution
// This is the core algorithm that distributes spaces evenly between words
private fun List<String>.fullyJustify(maxWidth: Int): String {
  val totalWordLength = sumOf { it.length }
  val totalSpaces = maxWidth - totalWordLength
  val gaps = size - 1

  // Edge case: single word line - just add trailing spaces
  if (gaps == 0) return first() + " ".repeat(totalSpaces)

  // Calculate space distribution:
  // - baseSpaces: minimum spaces between each word
  // - extraSpaces: remainder that needs to be distributed
  val baseSpaces = totalSpaces / gaps
  val extraSpaces = totalSpaces % gaps

  // Build the justified line by adding appropriate spaces before each word
  // First word gets no leading spaces, subsequent words get spaces based on position
  return asSequence()
    .mapIndexed { index, word ->
      when {
        index == 0 -> word // First word: no leading spaces
        index <= extraSpaces ->
          " ".repeat(baseSpaces + 1) + word // First 'extraSpaces' gaps get +1 space
        else -> " ".repeat(baseSpaces) + word // Remaining gaps get base spaces
      }
    }
    .joinToString("") // No separator since we're manually adding spaces
}
