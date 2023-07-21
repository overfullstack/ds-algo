@file:JvmName("Utils")

package ga.overfullstack.utils

import java.io.File

fun readFileToString(fileRelativePath: String): String {
  return File(fileRelativePath).readText(Charsets.UTF_8)
}

fun readFileFromTestResource(fileRelativePath: String): String =
  File("src/test/resources/$fileRelativePath").readText()
