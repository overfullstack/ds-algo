package ga.overfullstack.utils.json

import com.squareup.moshi.JsonReader

fun <T> JsonReader.objr(mk: () -> T, fn: T.(String) -> T): T {
  beginObject()
  var item = mk()
  while (hasNext()) {
    item = item.fn(nextName())
  }
  endObject()
  return item
}

private fun <T> JsonReader.skipNullOr(fn: JsonReader.() -> T): T? =
  if (peek() == JsonReader.Token.NULL) skipValue().let { null } else fn()

fun <T> JsonReader.listr(mk: () -> T, item: T.(String) -> T): List<T> {
  val items = mutableListOf<T>()
  beginArray()
  while (hasNext()) {
    items += objr(mk, item)
  }
  endArray()
  return items
}

fun JsonReader.anyMap(): Map<String, Any?>? = skipNullOr {
  beginObject()
  val map = mutableMapOf<String, Any?>()
  while (hasNext()) map += nextName() to readJsonValue()
  endObject()
  map
}
