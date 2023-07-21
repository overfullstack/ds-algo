@file:JvmName("Utils")

package ga.overfullstack.utils

import java.io.File

fun readFileToString(fileRelativePath: String): String =
  File(fileRelativePath).readText(Charsets.UTF_8)

const val RESOURCES_PATH = "src/main/resources/"
const val TEST_RESOURCES_PATH = "src/test/resources/"

fun readFileFromTestResource(fileRelativePath: String): String =
  File("$TEST_RESOURCES_PATH$fileRelativePath").readText(Charsets.UTF_8)
