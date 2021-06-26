package cci.array

fun urlify(str: CharArray, trueLength: Int): String {
  var spaceCount = 0
  for (index in trueLength - 1 downTo 0) {
    if (str[index] == ' ') {
      spaceCount++
    }
  }
  var bufferIndex = trueLength + 3 * spaceCount - spaceCount - 1
  for (index in trueLength - 1 downTo 0) {
    if (str[index] == ' ') {
      str[bufferIndex] = '0'
      str[bufferIndex - 1] = '2'
      str[bufferIndex - 2] = '%'
      bufferIndex -= 3
    } else {
      str[bufferIndex] = str[index]
      bufferIndex--
    }
  }
  return str.joinToString("")
}
