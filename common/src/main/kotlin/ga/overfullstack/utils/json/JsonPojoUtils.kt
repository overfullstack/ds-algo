@file:JvmName("JsonPojoUtils")

package ga.overfullstack.utils.json

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import ga.overfullstack.utils.json.factory.IgnoreUnknownFactory
import ga.overfullstack.utils.readFileToString
import java.lang.reflect.Type

@JvmOverloads
fun <PojoT : Any> jsonFileToPojo(
  pojoType: Type,
  jsonFilePath: String,
  customAdapters: List<Any> = emptyList(),
  typesToIgnore: Set<Type>? = emptySet()
): PojoT? {
  val jsonAdapter = initMoshiJsonAdapter<PojoT>(customAdapters, typesToIgnore, pojoType)
  return jsonAdapter.fromJson(readFileToString(jsonFilePath))
}

@JvmOverloads
fun <PojoT : Any> jsonToPojo(
  pojoType: Type,
  jsonStr: String,
  customAdapters: List<Any> = emptyList(),
  typesToIgnore: Set<Type>? = emptySet()
): PojoT? {
  val jsonAdapter = initMoshiJsonAdapter<PojoT>(customAdapters, typesToIgnore, pojoType)
  return jsonAdapter.fromJson(jsonStr)
}

inline fun <reified PojoT : Any> pojoToJson(
  pojo: PojoT,
  customAdapters: List<Any> = emptyList(),
  typesToIgnore: Set<Type>? = emptySet()
): String? {
  val jsonAdapter = initMoshiJsonAdapter<PojoT>(customAdapters, typesToIgnore)
  return runCatching { jsonAdapter.indent("  ").toJson(pojo) }.getOrNull()
}

@JvmOverloads
fun <PojoT : Any> pojoToJson(
  pojoType: Type,
  pojo: PojoT,
  customAdapters: List<Any> = emptyList(),
  typesToIgnore: Set<Type>? = emptySet(),
  indent: String? = "  "
): String? {
  val jsonAdapter = initMoshiJsonAdapter<PojoT>(customAdapters, typesToIgnore, pojoType)
  return (indent?.let { jsonAdapter.indent(indent) } ?: jsonAdapter).toJson(pojo)
}

private fun <PojoT : Any> initMoshiJsonAdapter(
  customAdapters: List<Any>,
  typesToIgnore: Set<Type>?,
  pojoType: Type
): JsonAdapter<PojoT> {
  val moshiBuilder = buildMoshi(customAdapters, typesToIgnore)
  return moshiBuilder.build().adapter(pojoType)
}

@SuppressWarnings("kotlin:S3923")
fun buildMoshi(
  customAdapters: List<Any>,
  typesToIgnore: Set<Type>?
): Moshi.Builder {
  val moshiBuilder = Moshi.Builder()
  for (adapter in customAdapters) {
    if (adapter is JsonAdapter.Factory) {
      moshiBuilder.add(adapter)
    } else {
      moshiBuilder.add(adapter)
    }
  }
  if (!typesToIgnore.isNullOrEmpty()) {
    moshiBuilder.add(IgnoreUnknownFactory(typesToIgnore))
  }
  return moshiBuilder
}

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified PojoT : Any> initMoshiJsonAdapter(
  customAdapters: List<Any>,
  typesToIgnore: Set<Type>?,
): JsonAdapter<PojoT> {
  val moshiBuilder = buildMoshi(customAdapters, typesToIgnore)
  moshiBuilder.addLast(KotlinJsonAdapterFactory())
  return moshiBuilder.build().adapter<PojoT>()
}
