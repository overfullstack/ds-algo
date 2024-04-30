@file:JvmName("Utils")

package ga.overfullstack.utils

import com.google.common.io.Resources

internal fun readFileToString(fileRelativePath: String): String =
  Resources.getResource(fileRelativePath).readText()

const val TEST_RESOURCES_PATH = "src/test/resources/"

